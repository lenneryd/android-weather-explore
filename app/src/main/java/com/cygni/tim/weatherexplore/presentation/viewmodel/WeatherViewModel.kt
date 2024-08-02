package com.cygni.tim.weatherexplore.presentation.viewmodel

import androidx.annotation.DrawableRes
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cygni.tim.weatherexplore.R
import com.cygni.tim.weatherexplore.data.models.Point
import com.cygni.tim.weatherexplore.data.models.TimeInstant
import com.cygni.tim.weatherexplore.data.models.TimeNextHours
import com.cygni.tim.weatherexplore.data.models.TimeSeriesModel
import com.cygni.tim.weatherexplore.data.models.Units
import com.cygni.tim.weatherexplore.data.models.WeatherModel
import com.cygni.tim.weatherexplore.data.models.toPoint
import com.cygni.tim.weatherexplore.domain.usecase.LocationUseCase
import com.cygni.tim.weatherexplore.domain.usecase.WeatherUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import java.lang.Exception
import java.text.DecimalFormat
import java.time.Duration
import java.time.LocalDateTime
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.UUID

@HiltViewModel(assistedFactory = WeatherViewModel.WeatherViewModelFactory::class)
/**
 * ViewModel responsible for preparing and managing the weather data for the UI.
 * It handles the communication of the application with the data layer and the UI.
 * @param displayType The initial display type for the UI.
 * @param useCase The use case for fetching weather data.
 * @param locationUseCase The use case for fetching location data.
 */
class WeatherViewModel @AssistedInject constructor(
    @Assisted displayType: DisplayType?,
    private val useCase: WeatherUseCase,
    private val locationUseCase: LocationUseCase
) : ViewModel() {

    /**
     * Factory for creating [WeatherViewModel] instances with assisted injection.
     */
    @AssistedFactory
    interface WeatherViewModelFactory {
        fun create(displayType: DisplayType?): WeatherViewModel
    }

    private val tempFormat = DecimalFormat("##.#")
    private val tempNoDecimals = DecimalFormat("##")
    private val windFormat = DecimalFormat("##.#")
    private val percentFormat = DecimalFormat("##")
    private val precipitationFormat = DecimalFormat("##.#")
    private val dayFormatter = DateTimeFormatter.ofPattern("cccc")
    private val hourFormat = DateTimeFormatter.ofPattern("H")

    private val messages = MutableStateFlow(listOf<Message>())

    /**
     * Enum class representing the display types for the UI.
     */
    enum class DisplayType(val value: String) {
        Blocks("blocks"), Timeline("timeline");

        fun toggle(): DisplayType = when (this) {
            Blocks -> Timeline
            Timeline -> Blocks
        }
    }

    private val _displayType = MutableStateFlow(displayType ?: DisplayType.Blocks)
    private val displayType get(): StateFlow<DisplayType> = _displayType

    private val weatherResponse = MutableStateFlow<Result<WeatherModel>?>(null)

    init {
        viewModelScope.launch {
            val result = locationUseCase.getLocation().first()
            when {
                result.isSuccess -> {
                    val response = useCase.getWeather(result.getOrThrow().toPoint()).first()
                    weatherResponse.value = response
                }

                else -> weatherResponse.value = Result.failure(FailedToGetLocationException())
            }
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    /**
     * The UI state representing the weather information.
     * It is a combination of weather data and messages to be displayed.
     */
    val uiState: Flow<WeatherUIState> = _displayType.flatMapLatest { type ->

        when (type) {
            DisplayType.Blocks -> weatherResponse.combine(messages) { response, messages ->
                when {
                    response == null -> WeatherUIState.PendingUIState
                    response.isSuccess -> {
                        val result = response.getOrThrow()
                        result.mapToUI(messages)
                    }

                    else -> WeatherUIState.FailureUIState("No weather response")
                }
            }

            DisplayType.Timeline -> weatherResponse.map { response ->
                when {
                    response == null -> WeatherUIState.PendingUIState
                    response.isSuccess -> {
                        val result = response.getOrThrow()
                        result.mapToTimeline()
                    }

                    else -> WeatherUIState.FailureUIState("No weather response")
                }
            }
        }
    }

    /**
     * Maps the [WeatherModel] to the UI state for the weather blocks display.
     * @param messages The list of messages to be displayed in the UI.
     * @return The UI state representing the weather blocks.
     */
    private fun WeatherModel.mapToUI(messages: List<Message> = listOf()): WeatherUIState {
        val now = LocalDateTime.now()
        return this.timeseries.filterOutdated(now).let { filtered ->
            if (filtered.isEmpty()) {
                WeatherUIState.FailureUIState("No up to date weather available.")
            } else {
                WeatherUIState.WeatherUI(
                    location = point,
                    updatedAtString = getUpdatedAtString(updatedAt),
                    selectedTimeFormat = "cccc HH:mm",
                    snackbarMessages = messages,
                    blocks = filtered.map { series ->
                        WeatherUIState.TimeSeriesBlock(
                            series.time,
                            listOfNotNull(
                                series.toTempWithSymbolOrNull(this.units),
                                series.toWindWithStrengthOrNull(this.units),
                                series.toCloudCoverOrNull(),
                                series.toPrecipitationPotentialOrNull(),
                                series.toPrecipitationAmountOrNull(units),
                                WeatherBlock.MapLink.GoToGoogleMaps(point),
                                WeatherBlock.MapLink.GoToMap(point),
                            )
                        )
                    }
                )
            }
        }
    }

    private fun WeatherModel.mapToTimeline() = WeatherUIState.WeatherTimelineUI(
        updatedAtString = getUpdatedAtString(updatedAt),
        list = timeseries.filterOutdated(LocalDateTime.now()).let { list ->
            // Construct the timeline UI by iterating over the filtered list of time series.
            // For each time series, check if a new day has started compared to the last item.
            // If so, insert a day divider. Then, add the hourly weather information followed by an hour divider.
            list.fold(mutableListOf()) { acc: MutableList<WeatherTimelineItem>, current ->
                val lastDay =
                    acc.lastOrNull { it is WeatherTimelineItem.WeatherHourlyTimelineItem }
                        .let { it as? WeatherTimelineItem.WeatherHourlyTimelineItem }
                        ?.let {
                            ZonedDateTime.parse(it.time).toLocalDateTime().format(dayFormatter)
                        }
                val currentDay = ZonedDateTime.parse(current.time).toLocalDateTime().format(dayFormatter)
                if (lastDay != currentDay) {
                    acc.add(WeatherTimelineItem.WeatherDayDivider(currentDay))
                }
                acc.add(current.mapToTimelineHour())
                acc.add(WeatherTimelineItem.HourDivider())
                acc
            }
        }.toList()
    )

    private fun TimeSeriesModel.mapToTimelineHour() = WeatherTimelineItem.WeatherHourlyTimelineItem(
        time = time,
        hourString = hourFormat.format(ZonedDateTime.parse(time).toLocalDateTime()),
        weatherIcon = this.data.next1Hours?.summary?.symbolCode,
        airTemp = tempNoDecimals.format(this.data.instant.details.airTemperature),
        windDirection = this.data.instant.details.windFromDirection,
        windDirectionStr = this.data.instant.details.windFromDirection.toDirection(),
        windStrength = windFormat.format(this.data.instant.details.windSpeed),
        precipitation = this.data.next1Hours?.details?.precipitationAmount?.let { precipitationFormat.format(it) }.orEmpty()
    )

    private fun getUpdatedAtString(updatedAt: String) = "Updated at: ${
        ZonedDateTime.parse(updatedAt).toLocalDateTime().format(DateTimeFormatter.ofPattern("HH:mm"))
    } (${
        // Calculate the duration since the last update and format it into a human-readable string.
        // This helps users understand how recent the weather information is.
        Duration.between(ZonedDateTime.parse(updatedAt).toLocalDateTime(), LocalDateTime.now()).let { duration ->
            val hours = duration.toHours()
            when {
                hours > 0 -> "$hours hours, ${duration.toMinutes() % 60} minutes"
                else -> "${duration.toMinutes()} minutes"
            }
        }
    } ago)"

    /**
     * Adds a message to the list of messages to be displayed in the UI.
     * @param message The message to be added.
     */
    fun addMessage(message: Message) {
        messages.value = messages.value.toMutableList().apply { this.add(message) }
    }

    /**
     * Clears a message from the list of messages to be displayed in the UI.
     * @param message The message to be removed.
     */
    fun clearMessage(message: Message) {
        messages.value = messages.value.toMutableList().apply { this.remove(message) }
    }

    private fun TimeSeriesModel.nextXHours(): TimeNextHours? = this.data.next1Hours ?: this.data.next6Hours
    private fun TimeSeriesModel.toTempWithSymbolOrNull(units: Units) =
        this.nextXHours()?.summary?.let { nextSummary ->
            WeatherBlock.TempWithSymbolIcon(
                weatherIcon = nextSummary.symbolCode,
                currentTemp = "${tempFormat.format(this.data.instant.details.airTemperature)} ${units.airTemperature.toTemperatureUnit()}"
            )
        }

    private fun TimeSeriesModel.toWindWithStrengthOrNull(units: Units) =
        data.instant.details.let { details ->
            WeatherBlock.WindWithStrength(
                degrees = details.windFromDirection.toFloat(),
                direction = details.windFromDirection.toDirection(),
                strength = "${windFormat.format(details.windSpeed)} ${units.windSpeed}"
            )
        }

    private fun TimeSeriesModel.toCloudCoverOrNull() = data.instant.details.let { details ->
        WeatherBlock.CloudCoverage(
            percent = details.cloudAreaFraction, percentText = "${percentFormat.format(details.cloudAreaFraction)} %"
        )
    }

    private fun TimeSeriesModel.toPrecipitationPotentialOrNull() =
        this.nextXHours()?.details?.precipitationProbability?.let { probability ->
            WeatherBlock.PrecipitationPotential(
                percent = probability,
                percentText = "${percentFormat.format(probability)}%"
            )
        }

    private fun TimeSeriesModel.toPrecipitationAmountOrNull(units: Units) = this.let { time ->
        val precipitationType = time.data.instant.toPrecipitationType()

        WeatherBlock.PrecipitationAmount(
            hours1 = time.data.next1Hours?.details.let { _ ->
                PrecipitationData(
                    precipitationType,
                    "1",
                    time.data.next1Hours?.details?.precipitationAmount?.let { "${precipitationFormat.format(it)}${units.precipitationAmount}" },
                    time.data.next1Hours?.details?.precipitationProbability,
                    time.data.next1Hours?.details?.precipitationProbability?.let { "${percentFormat.format(it)}%" }

                )
            },
            hours6 = time.data.next6Hours?.details.let { _ ->
                PrecipitationData(
                    precipitationType,
                    "6",
                    time.data.next6Hours?.details?.precipitationAmount?.let { "${precipitationFormat.format(it)}${units.precipitationAmount}" },
                    time.data.next6Hours?.details?.precipitationProbability,
                    time.data.next6Hours?.details?.precipitationProbability?.let { "${percentFormat.format(it)}%" }
                )
            },
            hours12 = time.data.next12Hours?.details.let { _ ->
                PrecipitationData(
                    precipitationType,
                    "12",
                    time.data.next12Hours?.details?.precipitationAmount?.let { "${precipitationFormat.format(it)}${units.precipitationAmount}" },
                    time.data.next12Hours?.details?.precipitationProbability,
                    time.data.next12Hours?.details?.precipitationProbability?.let { "${percentFormat.format(it)}%" }
                )
            }
        )
    }

    private fun TimeInstant.toPrecipitationType() = this.details.airTemperature.let { min ->
        when {
            min < 2.5 && min > 0 -> PrecipitationType.Sleet
            min > 2.5 -> PrecipitationType.Rain
            min < -2.5 -> PrecipitationType.Snow
            else -> PrecipitationType.Rain
        }
    }

    private fun Double.toDirection(): String = when {
        this < 22.5 -> "N"
        this < 67.5 -> "NE"
        this < 112.5 -> "E"
        this < 157.5 -> "SE"
        this < 202.5 -> "S"
        this < 247.5 -> "SW"
        this < 292.5 -> "W"
        this < 337.5 -> "NW"
        else -> "N"
    }

    private fun String.toTemperatureUnit() = when (this) {
        "celsius" -> "℃"
        else -> "℉"
    }

    private fun List<TimeSeriesModel>.filterOutdated(now: LocalDateTime): List<TimeSeriesModel> {
        val (history, future) = this.partition { item ->
            ZonedDateTime.parse(item.time).toLocalDateTime().isBefore(now)
        }
        return listOfNotNull(history.lastOrNull()) + future
    }

    private fun List<TimeSeriesModel>.filterSelected(position: Int): List<TimeSeriesModel> {
        return this.subList(position, this.size)
    }

    fun onUpdateSelectedTime(position: Float, finished: Boolean) {

    }

    fun onSwitchView(display: DisplayType) {
        _displayType.value = display
    }

    fun toggleView() {
        _displayType.value = if (DisplayType.Blocks == displayType.value) DisplayType.Timeline else DisplayType.Blocks
    }

    /**
     * Sealed class representing the UI state for the weather information.
     */
    sealed class WeatherUIState {
        data class WeatherUI(
            val location: Point,
            val updatedAtString: String,
            val selectedTimeFormat: String,
            val blocks: List<TimeSeriesBlock>,
            val snackbarMessages: List<Message> = listOf()
        ) : WeatherUIState()

        data class TimeSeriesBlock(
            val time: String,
            val blocks: List<WeatherBlock>
        )

        data class WeatherTimelineUI(
            val updatedAtString: String,
            val list: List<WeatherTimelineItem>
        ) : WeatherUIState()

        data class FailureUIState(val message: String) : WeatherUIState()
        data object PendingUIState : WeatherUIState()
    }

    /**
     * Sealed class representing items in the weather timeline.
     */
    sealed class WeatherTimelineItem(val key: String) {

        data class WeatherDayDivider(val text: String) : WeatherTimelineItem(key = UUID.randomUUID().toString())
        data class HourDivider(val listKey: String = UUID.randomUUID().toString()) : WeatherTimelineItem(key = listKey)
        data class WeatherHourlyTimelineItem(
            val time: String,
            val hourString: String,
            val weatherIcon: String?,
            val airTemp: String,
            val windDirectionStr: String,
            val windDirection: Double,
            val windStrength: String,
            val precipitation: String,
        ) : WeatherTimelineItem(key = UUID.randomUUID().toString())
    }


    /**
     * Sealed class representing the type of precipitation.
     */
    sealed class PrecipitationType(@DrawableRes val symbol: Int) {
        data object Rain : PrecipitationType(R.drawable.rain)
        data object Sleet : PrecipitationType(R.drawable.sleet)
        data object Snow : PrecipitationType(R.drawable.snow)
    }

    /**
     * Data class representing the data for precipitation.
     */
    data class PrecipitationData(
        val type: PrecipitationType,
        val hours: String,
        val amount: String?,
        val probability: Double?,
        val probabilityText: String?
    )

    /**
     * Data class representing the data for the slider UI component.
     */
    data class SliderData(
        val steps: Int,
        val currentStep: Int,
    ) {
        fun getRange() = 0f..(steps - 1).toFloat()
    }

    /**
     * Sealed class representing different types of weather blocks in the UI.
     */
    sealed class WeatherBlock(val tag: Type) {
        enum class Type(val type: String) {
            TempWithSymbol("tempWithSymbol"),
            WindWithStrength("windWithStrength"),
            CloudCoverage("cloudCoverage"),
            PrecipitationPotential("precipitationPotential"),
            PrecipitationAmount("precipitationAmount"),
            GoToMap("goToMap"),
            GoToGoogleMaps("goToGoogleMaps");
        }

        data class TempWithSymbolIcon(val weatherIcon: String, val currentTemp: String) : WeatherBlock(Type.TempWithSymbol)
        data class WindWithStrength(val degrees: Float, val direction: String, val strength: String) : WeatherBlock(Type.WindWithStrength)

        data class CloudCoverage(val percent: Double, val percentText: String) : WeatherBlock(Type.CloudCoverage)

        data class PrecipitationPotential(val percent: Double, val percentText: String) : WeatherBlock(Type.PrecipitationPotential)

        data class PrecipitationAmount(
            val hours1: PrecipitationData?,
            val hours6: PrecipitationData?,
            val hours12: PrecipitationData?,
        ) :
            WeatherBlock(Type.PrecipitationAmount)

        sealed class MapLink(tag: Type) : WeatherBlock(tag) {
            data class GoToMap(val point: Point) : MapLink(Type.GoToMap)
            data class GoToGoogleMaps(val point: Point) : MapLink(Type.GoToGoogleMaps)
        }
    }

    /**
     * Sealed class representing messages to be displayed in the UI.
     */
    sealed class Message(val text: String) {
        data object FailedToNavigateToMapMessage : Message(text = "Failed to show Map location")
    }

    /**
     * Exception class for handling failures in getting the location.
     */
    class FailedToGetLocationException : Exception("Failed to get location")
}

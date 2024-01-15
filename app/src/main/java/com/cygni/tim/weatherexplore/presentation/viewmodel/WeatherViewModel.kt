package com.cygni.tim.weatherexplore.presentation.viewmodel

import androidx.annotation.DrawableRes
import androidx.lifecycle.ViewModel
import com.cygni.tim.weatherexplore.R
import com.cygni.tim.weatherexplore.data.models.Point
import com.cygni.tim.weatherexplore.data.models.TimeInstant
import com.cygni.tim.weatherexplore.data.models.TimeNextHours
import com.cygni.tim.weatherexplore.data.models.TimeSeriesDetails
import com.cygni.tim.weatherexplore.data.models.TimeSeriesModel
import com.cygni.tim.weatherexplore.data.models.Units
import com.cygni.tim.weatherexplore.data.models.WeatherModel
import com.cygni.tim.weatherexplore.data.models.toPoint
import com.cygni.tim.weatherexplore.domain.usecase.LocationUseCase
import com.cygni.tim.weatherexplore.domain.usecase.WeatherUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import java.text.DecimalFormat
import java.time.Duration
import java.time.LocalDateTime
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val useCase: WeatherUseCase,
    private val locationUseCase: LocationUseCase
) : ViewModel() {

    private val tempFormat = DecimalFormat("##.#")
    private val windFormat = DecimalFormat("##.#")
    private val percentFormat = DecimalFormat("##")
    private val precipitationFormat = DecimalFormat("##.#")

    private val messages = MutableStateFlow(listOf<Message>())
    val uiState: Flow<WeatherUIState> = locationUseCase.getLocation().flatMapLatest { result ->
        when {
            result.isSuccess -> useCase.getWeather(result.getOrThrow().toPoint()).combine(messages) { weatherResult, messages ->
                when {
                    weatherResult.isSuccess -> weatherResult.getOrThrow().mapToUI(messages)
                    else -> WeatherUIState.FailureUIState("No weather response")
                }
            }

            else -> flowOf(WeatherUIState.FailureUIState("No location provided"))
        }
    }

    private fun WeatherModel.mapToUI(messages: List<Message> = listOf()) = WeatherUIState.WeatherUI(
        location = point,
        updatedAt = ZonedDateTime.parse(updatedAt).toLocalDateTime().format(DateTimeFormatter.ofPattern("HH:mm")),
        forecastAge = Duration.between(ZonedDateTime.parse(updatedAt).toLocalDateTime(), LocalDateTime.now()).let { duration ->
            val hours = duration.toHours()
            when {
                hours > 0 -> "$hours hours, ${duration.toMinutes() % 60} minutes"
                else -> "${duration.toMinutes()} minutes"
            }
        },
        snackbarMessages = messages,
        blocks = this.timeseries.future(LocalDateTime.now()).let { it.current ?: it.future.first() }.let { nextTimeModel ->
            listOfNotNull(
                nextTimeModel.toTempWithSymbolOrNull(this.units),
                nextTimeModel.toWindWithStrengthOrNull(this.units),
                nextTimeModel.toCloudCoverOrNull(),
                nextTimeModel.toPrecipitationPotentialOrNull(),
                nextTimeModel.toPrecipitationAmountOrNull(units)
            )
        }
    )

    fun addMessage(message: Message) {
        messages.value = messages.value.toMutableList().apply { this.add(message) }
    }

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
            hours1 = time.data.next1Hours?.details.let {
                PrecipitationData(
                    precipitationType,
                    "1",
                    time.data.next1Hours?.details?.precipitationAmount?.let { "${precipitationFormat.format(it)}${units.precipitationAmount}" },
                    time.data.next1Hours?.details?.precipitationProbability,
                    time.data.next1Hours?.details?.precipitationProbability?.let { "${percentFormat.format(it)}%" }

                )
            },
            hours6 = time.data.next6Hours?.details.let {
                PrecipitationData(
                    precipitationType,
                    "6",
                    time.data.next6Hours?.details?.precipitationAmount?.let { "${precipitationFormat.format(it)}${units.precipitationAmount}" },
                    time.data.next6Hours?.details?.precipitationProbability,
                    time.data.next6Hours?.details?.precipitationProbability?.let { "${percentFormat.format(it)}%" }
                )
            },
            hours12 = time.data.next12Hours?.details.let {
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

    private fun TimeInstant.toPrecipitationType() = this?.details?.airTemperature?.let { min ->
        when {
            min < 2.5 && min > 0 -> PrecipitationType.Sleet
            min > 2.5 -> PrecipitationType.Rain
            min < -2.5 -> PrecipitationType.Snow
            else -> PrecipitationType.Rain
        }
    } ?: PrecipitationType.Rain

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

    private fun List<TimeSeriesModel>.future(now: LocalDateTime): FutureWithCurrent {
        val (history, future) = this.partition { item ->
            ZonedDateTime.parse(item.time).toLocalDateTime().isBefore(now)
        }
        return FutureWithCurrent(history.last(), future)
    }

    data class FutureWithCurrent(
        val current: TimeSeriesModel?,
        val future: List<TimeSeriesModel>
    )

    sealed class WeatherUIState {
        data class WeatherUI(
            val location: Point,
            val updatedAt: String,
            val forecastAge: String,
            val blocks: List<WeatherBlock>,
            val snackbarMessages: List<Message> = listOf()
        ) : WeatherUIState()

        data class FailureUIState(val message: String) : WeatherUIState()
        data object PendingUIState : WeatherUIState()
    }

    sealed class PrecipitationType(@DrawableRes val symbol: Int) {
        data object Rain : PrecipitationType(R.drawable.rain)
        data object Sleet : PrecipitationType(R.drawable.sleet)
        data object Snow : PrecipitationType(R.drawable.snow)
    }

    data class PrecipitationData(val type: PrecipitationType, val hours: String, val amount: String?, val probability: Double?, val probabilityText: String?)

    sealed class WeatherBlock {
        data class TempWithSymbolIcon(val weatherIcon: String, val currentTemp: String) : WeatherBlock()
        data class WindWithStrength(val degrees: Float, val direction: String, val strength: String) : WeatherBlock()

        data class CloudCoverage(val percent: Double, val percentText: String) : WeatherBlock()

        data class PrecipitationPotential(val percent: Double, val percentText: String) : WeatherBlock()

        data class PrecipitationAmount(
            val hours1: PrecipitationData?,
            val hours6: PrecipitationData?,
            val hours12: PrecipitationData?,
        ) :
            WeatherBlock()
    }

    sealed class Message(val text: String) {
        data object FailedToNavigateToMapMessage : Message(text = "Failed to show Map location")
    }
}


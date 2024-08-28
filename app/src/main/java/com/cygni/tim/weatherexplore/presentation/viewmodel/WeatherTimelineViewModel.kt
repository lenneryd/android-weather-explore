package com.cygni.tim.weatherexplore.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cygni.tim.weatherexplore.data.models.TimeSeriesModel
import com.cygni.tim.weatherexplore.data.models.WeatherModel
import com.cygni.tim.weatherexplore.data.models.toPoint
import com.cygni.tim.weatherexplore.domain.usecase.LocationUseCase
import com.cygni.tim.weatherexplore.domain.usecase.WeatherUseCase
import com.cygni.tim.weatherexplore.presentation.viewmodel.WeatherTimelineViewModel.WeatherTimelineItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import java.text.DecimalFormat
import java.time.Duration
import java.time.LocalDateTime
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.UUID
import javax.inject.Inject

@HiltViewModel()
class WeatherTimelineViewModel @Inject constructor(
    private val useCase: WeatherUseCase,
    private val locationUseCase: LocationUseCase
) : ViewModel() {
    private val tempNoDecimals = DecimalFormat("##")
    private val windFormat = DecimalFormat("##.#")
    private val precipitationFormat = DecimalFormat("##.#")
    private val dayFormatter = DateTimeFormatter.ofPattern("cccc")
    private val hourFormat = DateTimeFormatter.ofPattern("H")

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


    val uiState: Flow<WeatherTimeline> = weatherResponse.map { response ->
        when {
            response == null -> WeatherTimeline.LoadingWeatherTimeline
            response.isSuccess -> {
                val result = response.getOrThrow()
                result.mapToTimeline()
            }

            else -> WeatherTimeline.LoadingWeatherTimeline
        }
    }

    private fun WeatherModel.mapToTimeline() = WeatherTimeline.WeatherTimelineUI(
        updatedAtString = getUpdatedAtString(updatedAt),
        list = timeseries.filterOutdated(LocalDateTime.now()).let { list ->
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
        Duration.between(ZonedDateTime.parse(updatedAt).toLocalDateTime(), LocalDateTime.now()).let { duration ->
            val hours = duration.toHours()
            when {
                hours > 0 -> "$hours hours, ${duration.toMinutes() % 60} minutes"
                else -> "${duration.toMinutes()} minutes"
            }
        }
    } ago)"

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

    sealed class WeatherTimeline {
        data class WeatherTimelineUI(
            val updatedAtString: String,
            val list: List<WeatherTimelineItem>
        ) : WeatherTimeline()

        data object LoadingWeatherTimeline: WeatherTimeline()
    }

    class FailedToGetLocationException : Exception("Failed to get location")
}


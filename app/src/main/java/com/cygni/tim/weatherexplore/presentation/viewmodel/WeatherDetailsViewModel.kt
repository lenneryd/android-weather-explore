package com.cygni.tim.weatherexplore.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cygni.tim.weatherexplore.data.models.TimeSeriesModel
import com.cygni.tim.weatherexplore.data.models.WeatherModel
import com.cygni.tim.weatherexplore.data.models.toPoint
import com.cygni.tim.weatherexplore.domain.usecase.LocationUseCase
import com.cygni.tim.weatherexplore.domain.usecase.WeatherUseCase
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
import javax.inject.Inject

@HiltViewModel()
class WeatherDetailsViewModel @Inject constructor(
    private val useCase: WeatherUseCase,
    private val locationUseCase: LocationUseCase
) : ViewModel() {
    private val tempNoDecimals = DecimalFormat("##")

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

    val uiState: Flow<WeatherDetails> = weatherResponse.map { response ->
        when {
            response == null -> WeatherDetails.LoadingWeatherDetails()
            response.isSuccess -> {
                val result = response.getOrThrow()
                result.mapToDetailsOrNull() ?: WeatherDetails.LoadingWeatherDetails()
            }

            else -> WeatherDetails.LoadingWeatherDetails()
        }

    }

    private fun WeatherModel.mapToDetailsOrNull(): WeatherDetails.WeatherDetailsUI? {
        val now = LocalDateTime.now()
        return this.timeseries.filterOutdated(now).let { filtered ->
            if (filtered.isEmpty()) {
                null
            } else {
                val first = filtered.first()
                WeatherDetails.WeatherDetailsUI(
                    updatedAtString = getUpdatedAtString(updatedAt),
                    selectedTimeFormat = "cccc HH:mm",
                    time = first.time,
                    icon = first.data.next1Hours?.summary?.symbolCode ?: "",
                    temperature = tempNoDecimals.format(first.data.instant.details.airTemperature),
                )
            }
        }
    }

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


    private fun List<TimeSeriesModel>.filterOutdated(now: LocalDateTime): List<TimeSeriesModel> {
        val (history, future) = this.partition { item ->
            ZonedDateTime.parse(item.time).toLocalDateTime().isBefore(now)
        }
        return listOfNotNull(history.lastOrNull()) + future
    }

    sealed class WeatherDetails {
        data class WeatherDetailsUI(
            val updatedAtString: String,
            val icon: String,
            val temperature: String,
            val selectedTimeFormat: String,
            val time: String,
        ): WeatherDetails()

        data class LoadingWeatherDetails(val dummyData: WeatherDetailsUI = WeatherDetailsUI(
            updatedAtString = "",
            icon = "partly_cloudy",
            temperature = "",
            selectedTimeFormat = "cccc HH:mm",
            time = "2024-01-10T13:00:00Z"
        )): WeatherDetails()
    }


    class FailedToGetLocationException : Exception("Failed to get location")
}

package com.cygni.tim.weatherexplore.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.cygni.tim.weatherexplore.data.models.Point
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
import kotlinx.coroutines.flow.map
import java.text.SimpleDateFormat
import java.time.Duration
import java.time.LocalDateTime
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val useCase: WeatherUseCase,
    private val locationUseCase: LocationUseCase
) : ViewModel() {

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
        snackbarMessages = messages
    )

    fun addMessage(message: Message) {
        messages.value = messages.value.toMutableList().apply { this.add(message) }
    }

    fun clearMessage(message: Message) {
        messages.value = messages.value.toMutableList().apply { this.remove(message) }
    }

    sealed class WeatherUIState {
        data class WeatherUI(
            val location: Point,
            val updatedAt: String,
            val forecastAge: String,
            val snackbarMessages: List<Message> = listOf()
        ) : WeatherUIState()

        data class FailureUIState(val message: String) : WeatherUIState()
        data object PendingUIState : WeatherUIState()
    }
    sealed class Message(val text: String) {
        data object FailedToNavigateToMapMessage: Message(text = "Failed to show Map location")
    }


}


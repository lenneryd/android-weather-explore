package com.cygni.tim.weatherexplore.presentation.viewmodel

import androidx.annotation.DrawableRes
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cygni.tim.weatherexplore.data.models.Point
import com.cygni.tim.weatherexplore.data.models.toLatLng
import com.cygni.tim.weatherexplore.data.models.toPoint
import com.cygni.tim.weatherexplore.domain.usecase.LocationUseCase
import com.cygni.tim.weatherexplore.domain.usecase.WeatherUseCase
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.SphericalUtil
import com.google.maps.android.clustering.ClusterItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random


@HiltViewModel
class MapWeatherScreenViewModel @Inject constructor(private val locationUseCase: LocationUseCase, private val weatherUseCase: WeatherUseCase) : ViewModel() {

    private val _uiState = MutableStateFlow<MapWeatherState>(MapWeatherState.None)
    val uiState: StateFlow<MapWeatherState> = _uiState

    init {
        viewModelScope.launch {
            val location = locationUseCase.getLocation().first().getOrThrow()
            update(location.toPoint())
        }

    }

    fun update(point: Point) {
        viewModelScope.launch {
            val res = weatherUseCase.getWeather(point).first()
            when {
                res.isSuccess -> _uiState.value = res.getOrThrow().let { weather ->
                    MapWeatherState.Weather(
                        point = point,
                        items = weather.timeseries.first().data.next1Hours?.let { next1Hours ->
                            (0..12).map { idx ->
                                WeatherItem(point.nudge((360.0 / 12.0) * idx), next1Hours.summary.symbolCode)
                            }
                        }.orEmpty()
                    )
                }

                else -> _uiState.value = MapWeatherState.None
            }
        }
    }

    sealed class MapWeatherState {
        data object None : MapWeatherState()
        data class Weather(
            val point: Point,
            val items: List<WeatherItem>
        ) : MapWeatherState()
    }

    data class WeatherItem(private val pos: LatLng, val symbol: String) : ClusterItem {
        override fun getPosition() = pos

        override fun getTitle() = null

        override fun getSnippet() = null

        override fun getZIndex() = null
    }

    private fun Point.nudge(direction: Double, distance: Double = 20_000.0): LatLng {
        val random = Random.nextDouble()
        return SphericalUtil.computeOffset(this.toLatLng(), distance * random, direction)
    }
}

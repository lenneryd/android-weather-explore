package com.cygni.tim.weatherexplore.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cygni.tim.weatherexplore.data.models.Point
import com.cygni.tim.weatherexplore.data.models.toPoint
import com.cygni.tim.weatherexplore.domain.usecase.LocationUseCase
import com.google.android.gms.maps.model.LatLng
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MapScreenViewModel @Inject constructor(private val locationUseCase: LocationUseCase) : ViewModel() {

    private val _uiState = MutableStateFlow(MapState())
    val uiState get(): StateFlow<MapState> = _uiState

    init {
        viewModelScope.launch {
            val location = locationUseCase.getLocation().firstOrNull()?.getOrNull()
            _uiState.value = MapState(location?.toPoint())
        }
    }

    fun onChangedPosition(pos: LatLng) {
        locationUseCase.setLocation(Point(pos.latitude, pos.longitude))
    }

    data class MapState(val pos: Point? = null)
}

package com.cygni.tim.weatherexplore.data.storage

import com.cygni.tim.weatherexplore.data.models.WeatherModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class WeatherStorage {
    private val storage = MutableStateFlow<WeatherModel?>(null)

    fun put(model: WeatherModel) {
        storage.value = model
    }

    fun get(): StateFlow<WeatherModel?> {
        return storage
    }
}

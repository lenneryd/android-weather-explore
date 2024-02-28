package com.cygni.tim.weatherexplore.domain.usecase

import android.location.Location
import com.cygni.tim.weatherexplore.data.models.Point
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocationUseCase @Inject constructor() {
    private val location = MutableStateFlow(
        Location("").apply {
            latitude = 59.326038
            longitude = 17.8172507
        }
    )

    fun getLocation(): Flow<Result<Location>> = flow {
        delay(500)
        emit(
            Result.success(location.value)
        )
    }

    fun setLocation(point: Point) {
        location.value = Location("").apply {
            latitude = point.lat
            longitude = point.lon
        }
    }
}

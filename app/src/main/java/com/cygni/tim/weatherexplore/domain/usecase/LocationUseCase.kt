package com.cygni.tim.weatherexplore.domain.usecase

import android.location.Location
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class LocationUseCase @Inject constructor() {
    fun getLocation(): Flow<Result<Location>> = flowOf(Result.success(Location("").apply {
        latitude = 17.8172507
        longitude = 59.326038
    }))
}
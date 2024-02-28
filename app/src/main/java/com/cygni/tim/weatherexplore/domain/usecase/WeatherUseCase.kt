package com.cygni.tim.weatherexplore.domain.usecase

import com.cygni.tim.weatherexplore.data.models.Point
import com.cygni.tim.weatherexplore.data.models.WeatherModel
import com.cygni.tim.weatherexplore.data.repo.WeatherRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class WeatherUseCase @Inject constructor(
    private val repo: WeatherRepository
) {
    fun getWeather(point: Point): Flow<Result<WeatherModel>> = repo.getWeather(point)
}

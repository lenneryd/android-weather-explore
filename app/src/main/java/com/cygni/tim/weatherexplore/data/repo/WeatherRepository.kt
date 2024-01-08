package com.cygni.tim.weatherexplore.data.repo

import com.cygni.tim.weatherexplore.data.api.models.WeatherEntity
import com.cygni.tim.weatherexplore.data.api.retrofit.WeatherApi
import com.cygni.tim.weatherexplore.data.api.retrofit.toResult
import com.cygni.tim.weatherexplore.data.models.Point
import com.cygni.tim.weatherexplore.data.models.WeatherModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.text.DecimalFormat
import javax.inject.Inject

class WeatherRepository @Inject constructor(private val api: WeatherApi) {
    fun getWeather(point: Point): Flow<Result<WeatherModel>> = flow {
        emit(api.getWeather(point.truncLatitude(), point.truncLongitude()).toResult { entity ->
            entity.toModel(point)
        })
    }
}

private val format = DecimalFormat("###.##")

fun WeatherEntity.toModel(point: Point) = WeatherModel(point, updatedAt = this.properties.meta.updatedAt)

fun Point.truncLatitude(): String = format.format(this.lat)
fun Point.truncLongitude(): String = format.format(this.lon)
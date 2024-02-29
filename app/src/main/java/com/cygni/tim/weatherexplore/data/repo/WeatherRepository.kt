package com.cygni.tim.weatherexplore.data.repo

import com.cygni.tim.weatherexplore.data.api.models.TimeNextHours
import com.cygni.tim.weatherexplore.data.api.models.TimeSeriesInstantDetails
import com.cygni.tim.weatherexplore.data.api.models.WeatherEntity
import com.cygni.tim.weatherexplore.data.api.retrofit.WeatherApi
import com.cygni.tim.weatherexplore.data.api.retrofit.toResult
import com.cygni.tim.weatherexplore.data.models.Point
import com.cygni.tim.weatherexplore.data.models.TimeInstant
import com.cygni.tim.weatherexplore.data.models.TimeSeriesData
import com.cygni.tim.weatherexplore.data.models.TimeSeriesDetails
import com.cygni.tim.weatherexplore.data.models.TimeSeriesModel
import com.cygni.tim.weatherexplore.data.models.TimeSeriesSummary
import com.cygni.tim.weatherexplore.data.models.Units
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

fun WeatherEntity.toModel(point: Point) = WeatherModel(
    point = point,
    updatedAt = this.properties.meta.updatedAt,
    units = this.properties.meta.units.let {
        Units(
            airPressureAtSeaLevel = it.airPressureAtSeaLevel,
            airTemperature = it.airTemperature,
            cloudAreaFraction = it.cloudAreaFraction,
            precipitationAmount = it.precipitationAmount,
            relativeHumidity = it.relativeHumidity,
            windFromDirection = it.windFromDirection,
            windSpeed = it.windSpeed
        )
    },
    timeseries = this.properties.timeseries.map {
        TimeSeriesModel(
            it.time,
            TimeSeriesData(
                it.data.instant.details.toModel(),
                it.data.next1Hours?.toModel(),
                it.data.next6Hours?.toModel(),
                it.data.next12Hours?.toModel(),
            )
        )
    }
)

private fun TimeSeriesInstantDetails.toModel() = TimeInstant(
    com.cygni.tim.weatherexplore.data.models.TimeSeriesInstantDetails(
        airPressureAtSeaLevel, airTemperature, cloudAreaFraction, relativeHumidity, windFromDirection, windSpeed
    )
)

private fun TimeNextHours.toModel() = com.cygni.tim.weatherexplore.data.models.TimeNextHours(
    TimeSeriesSummary(
        symbolCode = this.summary.symbolCode,
    ),
    TimeSeriesDetails(
        this.details.precipitationAmount,
        this.details.precipitationProbability
    )
)

fun Point.truncLatitude(): String = format.format(this.lat)
fun Point.truncLongitude(): String = format.format(this.lon)

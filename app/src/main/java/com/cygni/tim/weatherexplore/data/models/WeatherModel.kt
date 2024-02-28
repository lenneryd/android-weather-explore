package com.cygni.tim.weatherexplore.data.models

import android.location.Location
import com.google.android.gms.maps.model.LatLng

data class WeatherModel(val point: Point, val updatedAt: String, val units: Units, val timeseries: List<TimeSeriesModel>)

data class Point(val lat: Double, val lon: Double)

fun Location.toPoint(): Point = Point(latitude, longitude)
fun Point.toLatLng(): LatLng = LatLng(lat, lon)
fun LatLng.toPoint(): Point = Point(latitude, longitude)

data class TimeSeriesModel(
    val time: String,
    val data: TimeSeriesData
)

data class TimeSeriesData(
    val instant: TimeInstant,
    val next1Hours: TimeNextHours?,
    val next6Hours: TimeNextHours?,
    val next12Hours: TimeNextHours?
)

data class TimeInstant(
    val details: TimeSeriesInstantDetails
)

data class TimeNextHours(
    val summary: TimeSeriesSummary,
    val details: TimeSeriesDetails
)

data class TimeSeriesSummary(
    val symbolCode: String,
)

data class TimeSeriesInstantDetails(
    val airPressureAtSeaLevel: Double,
    val airTemperature: Double,
    val cloudAreaFraction: Double,
    val relativeHumidity: Double,
    val windFromDirection: Double,
    val windSpeed: Double,
)

data class TimeSeriesDetails(
    val precipitationAmount: Double?,
    val precipitationProbability: Double?,
)

data class Units(
    val airPressureAtSeaLevel: String,
    val airTemperature: String,
    val cloudAreaFraction: String,
    val precipitationAmount: String,
    val relativeHumidity: String,
    val windFromDirection: String,
    val windSpeed: String
)

package com.cygni.tim.weatherexplore.data.api.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherEntity(
    val geometry: Geometry,
    val properties: Properties
)

@Serializable
data class Geometry(
    val coordinates: List<Double>
)

@Serializable
data class Properties(
    val meta: Meta,
    val timeseries: List<TimeSeries>
)

@Serializable
data class TimeSeries(
    val time: String,
    val data: TimeSeriesData
)

@Serializable
data class TimeSeriesData(
    val instant: TimeInstant,
    @SerialName("next_1_hours")
    val next1Hours: TimeNextHours?,
    @SerialName("next_6_hours")
    val next6Hours: TimeNextHours?,
    @SerialName("next_12_hours")
    val next12Hours: TimeNextHours?
)

@Serializable
data class TimeInstant(
    val details: TimeSeriesInstantDetails
)

@Serializable
data class TimeNextHours(
    val summary: TimeSeriesSummary,
    val details: TimeSeriesDetails
)

@Serializable
data class TimeSeriesSummary(
    @SerialName("symbol_code")
    val symbolCode: String
)

@Serializable
data class TimeSeriesInstantDetails(
    @SerialName("air_pressure_at_sea_level") val airPressureAtSeaLevel : Double,
    @SerialName("air_temperature") val airTemperature : Double,
    @SerialName("cloud_area_fraction") val cloudAreaFraction: Double,
    @SerialName("relative_humidity") val relativeHumidity: Double,
    @SerialName("wind_from_direction") val windFromDirection: Double,
    @SerialName("wind_speed") val windSpeed: Double,
)

@Serializable
data class TimeSeriesDetails(
    @SerialName("precipitation_amount")
    val precipitationAmount: Double?
)

@Serializable
data class Meta(
    @SerialName("updated_at")
    val updatedAt: String,
    val units: Units
)

@Serializable
data class Units(
    @SerialName("air_pressure_at_sea_level")
    val airPressureAtSeaLevel: String,
    @SerialName("air_temperature")
    val airTemperature: String,
    @SerialName("cloud_area_fraction")
    val cloudAreaFraction: String,
    @SerialName("precipitation_amount")
    val precipitationAmount: String,
    @SerialName("relative_humidity")
    val relativeHumidity: String,
    @SerialName("wind_from_direction")
    val windFromDirection: String,
    @SerialName("wind_speed")
    val windSpeed: String
)
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
    val meta: Meta
)

@Serializable
data class Meta(
    @SerialName("updated_at")
    val updatedAt: String
)
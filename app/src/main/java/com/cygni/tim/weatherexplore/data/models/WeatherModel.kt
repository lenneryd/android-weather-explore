package com.cygni.tim.weatherexplore.data.models

import android.location.Location

data class WeatherModel(val point: Point, val updatedAt: String)

data class Point(val lat: Double, val lon: Double)

fun Location.toPoint(): Point = Point(latitude, longitude)
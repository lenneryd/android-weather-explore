package com.cygni.tim.weatherexplore.presentation.navigation

import android.net.Uri
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDestination

enum class Arguments(val value: String) {
    Type("type")
}

sealed class Route(
    val value: String,
    val title: String,
    val arguments: List<Arguments> = listOf(),
    val optional: List<Arguments> = listOf()
) {
    data object Navigation : Route(value = "navigation", "Navigation")
    data object Clock : Route(value = "clock", "Clock")
    data object Map : Route(value = "map", "Pick Weather Location")
    data object Weather : Route("weather", "Weather at location", optional = listOf(Arguments.Type))
    data object WeatherMap : Route("mapWeather", "Weather")
    companion object {
        val entries = listOf(Navigation, Clock, Map, Weather, WeatherMap)
    }
}

fun NavDestination.asRoute(): Route = Route.entries.first { it.value == this.route }
private fun Route.routeDefinitionBuilder() = Uri.parse(value).buildUpon().apply {
    arguments.forEach { key ->
        appendPath("{${key.value}}")
    }
    optional.forEach { key ->
        appendQueryParameter(key.value, "{${key.value}}")
    }
}

fun Route.routeDefinition(): String = Uri.decode(routeDefinitionBuilder().toString())

fun Route.resolved(): String = routeDefinition()

fun Route.Weather.resolved(type: String? = null): String = Uri.decode(routeDefinitionBuilder().toString()).let { def ->
    if (type != null) def.replace("{${Arguments.Type.value}}", type) else def
}

fun NavBackStackEntry.get(argument: Arguments): String? = arguments?.getString(argument.value)

package com.cygni.tim.weatherexplore.presentation

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class WeatherExploreApplication: Application() {
    companion object {
        lateinit var instance: WeatherExploreApplication
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}

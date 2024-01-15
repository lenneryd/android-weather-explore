package com.cygni.tim.weatherexplore.data.api.retrofit

import com.cygni.tim.weatherexplore.data.api.models.WeatherEntity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface WeatherApi {

    @GET("locationforecast/2.0/complete")
    @Headers("User-Agent: WeatherExplore/0.1 github.com/lenneryd")
    suspend fun getWeather(@Query("lat") lat: String, @Query("lon") lon: String): Response<WeatherEntity>

}
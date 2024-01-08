package com.cygni.tim.weatherexplore.data

import android.content.Context
import com.cygni.tim.weatherexplore.R
import com.cygni.tim.weatherexplore.data.api.retrofit.WeatherApi
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.Cache
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object OkHttpModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(@ApplicationContext context: Context): OkHttpClient = OkHttpClient.Builder()
        // Can add Logger interceptor here.
        .cache(Cache(context.cacheDir, DEFAULT_CACHE_SIZE))
        .addInterceptor(
            HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
        )
        .build()

    private const val DEFAULT_CACHE_SIZE = 34L * 1024L
}

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    private val json = Json {
        ignoreUnknownKeys = true
        isLenient = true
    }

    @Provides
    @Singleton
    fun provideRetrofit(@ApplicationContext context: Context, okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(context.getString(R.string.weather_api_baseurl))
        .addConverterFactory(
            json.asConverterFactory("application/json".toMediaType())
        )
        .client(okHttpClient)
        .build()
}

@Module
@InstallIn(SingletonComponent::class)
object WeatherApiModule {

    @Provides
    @Singleton
    fun provideWeatherApi(retrofit: Retrofit): WeatherApi = retrofit.create(WeatherApi::class.java)
}
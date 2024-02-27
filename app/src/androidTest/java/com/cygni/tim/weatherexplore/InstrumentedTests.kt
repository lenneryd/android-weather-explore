package com.cygni.tim.weatherexplore

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.cygni.tim.weatherexplore.data.api.models.WeatherEntity
import com.cygni.tim.weatherexplore.data.api.retrofit.WeatherApi
import com.cygni.tim.weatherexplore.data.models.Point
import com.cygni.tim.weatherexplore.data.repo.WeatherRepository
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.Json
import org.junit.Test
import org.junit.runner.RunWith
import retrofit2.Response

@RunWith(AndroidJUnit4::class)
class InstrumentedTests {

    private val json = Json {
        ignoreUnknownKeys = true
        isLenient = true
        explicitNulls = false
    }

    private val weatherEntity: WeatherEntity =
        json.decodeFromString<WeatherEntity>(Fixtures.complete)

    @Test
    fun testWeatherRepo() {
        val weather = mockk<WeatherApi>()
        val response = mockk<Response<WeatherEntity>>()

        response.applyMockDefaults(responseBody = weatherEntity)
        coEvery { weather.getWeather(any(), any()) } returns response
        val repo = WeatherRepository(weather)
        runTest {
            assertTrue(repo.getWeather(Point(59.326038, 17.8172507)).first().isSuccess)
        }
    }
}
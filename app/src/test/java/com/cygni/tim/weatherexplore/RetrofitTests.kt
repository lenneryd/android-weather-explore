package com.cygni.tim.weatherexplore

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.printToLog
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.cygni.tim.weatherexplore.data.models.Point
import com.cygni.tim.weatherexplore.presentation.colors.AppYuTheme
import com.cygni.tim.weatherexplore.presentation.compose.WeatherScreenComposable
import com.cygni.tim.weatherexplore.presentation.viewmodel.WeatherViewModel
import junit.framework.TestCase.assertTrue
import org.junit.Before
import org.junit.Ignore
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.shadows.ShadowLog

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class RetrofitTests {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    @Throws(Exception::class)
    fun setUp() {
        ShadowLog.stream = System.out // Redirect Logcat to console
    }

    private fun testWeatherUIState() = WeatherViewModel.WeatherUIState.WeatherUI(
        Point(
            lat = 59.326038,
            lon = 17.8172507
        ),
        updatedAtString = "Updated at 09:41 (14 minutes ago)",
        selectedTime = "Tuesday 09:00",
        slider = WeatherViewModel.SliderData(15, 3),
        listOf(
            WeatherViewModel.WeatherBlock.TempWithSymbolIcon("partlycloudy_day", "-14.3"),
            WeatherViewModel.WeatherBlock.WindWithStrength(291.0f, "SW", "3.6 m/s"),
            WeatherViewModel.WeatherBlock.CloudCoverage(69.0, "69%"),
            WeatherViewModel.WeatherBlock.PrecipitationPotential(45.0, "45%"),
            WeatherViewModel.WeatherBlock.PrecipitationAmount(
                WeatherViewModel.PrecipitationData(WeatherViewModel.PrecipitationType.Rain, "1", "2mm", 85.0, "85%"),
                WeatherViewModel.PrecipitationData(WeatherViewModel.PrecipitationType.Rain, "6", "2mm", 85.0, "85%"),
                WeatherViewModel.PrecipitationData(WeatherViewModel.PrecipitationType.Rain, "12", "2mm", 85.0, "85%"),
            ),
        )
    )

    @Test
    fun testWeatherComposeScreen() {
        composeTestRule.mainClock.autoAdvance = false
        var enabled by mutableStateOf(false)
        composeTestRule.setContent {
            AppYuTheme {
                WeatherScreenComposable(
                    state = testWeatherUIState(),
                    onNavigateToMap = {},
                    onToggleScreenType = {},
                    onUpdateSelectedTime = {},
                    onDismissMessage = {}
                )
            }
        }
        enabled = true
        composeTestRule.mainClock.advanceTimeBy(10_000)

        composeTestRule.onNodeWithText("Tuesday 09:00").assertExists("Cannot find selected day")
        composeTestRule.onNodeWithText("-14.3").assertExists("Cannot find Temp with symbol.").printToLog("Test")
        composeTestRule.onNodeWithTag(WeatherViewModel.WeatherBlock.Type.TempWithSymbol.type, useUnmergedTree = true)
            .let { node ->
                node.assertExists("Cannot find WeatherViewModel.WeatherBlock.Type.TempWithSymbol node")
            }
    }

    @Ignore("Intentionally skipped test")
    @Test
    fun testFailingTest() {
        assertTrue(false)
    }
}

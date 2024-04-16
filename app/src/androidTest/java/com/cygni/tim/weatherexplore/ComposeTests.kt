package com.cygni.tim.weatherexplore

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.cygni.tim.weatherexplore.data.models.Point
import com.cygni.tim.weatherexplore.presentation.colors.AppYuTheme
import com.cygni.tim.weatherexplore.presentation.compose.WeatherScreenComposable
import com.cygni.tim.weatherexplore.presentation.viewmodel.WeatherViewModel

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Ignore
import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ComposeTests {

    @get:Rule
    val composeTestRule = createComposeRule()

    private fun weatherUIState() = WeatherViewModel.WeatherUIState.WeatherUI(
        Point(
            lat = 59.326038,
            lon = 17.8172507
        ),
        updatedAtString = "Updated at 09:41 (14 minutes ago)",
        selectedTimeFormat = "cccc HH:mm",
        blocks = listOf(
            WeatherViewModel.WeatherUIState.TimeSeriesBlock(
                "2024-01-10T13:00:00Z",
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
            ))
    )

    @Test
    fun testWeatherComposeScreen() {
        composeTestRule.mainClock.autoAdvance = false
        var enabled by mutableStateOf(false)
        composeTestRule.setContent {
            AppYuTheme {
                WeatherScreenComposable(
                    state = weatherUIState(),
                    onNavigateToMap = {},
                    onToggleScreenType = {},
                    onUpdateSelectedTime = {_, _ ->},
                    onDismissMessage = {}
                )
            }
        }
        enabled = true
        composeTestRule.mainClock.advanceTimeBy(10_000)

        composeTestRule.onNodeWithText("Wednesday 13:00").assertExists("Cannot find selected day")
        composeTestRule.onNodeWithText("-14.3").assertExists("Cannot find Temp with symbol.")
        composeTestRule.onNodeWithTag(WeatherViewModel.WeatherBlock.Type.TempWithSymbol.type, useUnmergedTree = true)
            .let { node ->
                node.assertExists("Cannot find WeatherViewModel.WeatherBlock.Type.TempWithSymbol node")
            }
    }

    @Ignore("Ignored as it is an intentionally skipped test.")
    @Test
    fun testFailingTest() {
        assertTrue(false)
    }
}

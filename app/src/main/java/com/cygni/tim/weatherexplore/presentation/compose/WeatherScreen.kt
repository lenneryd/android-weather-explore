package com.cygni.tim.weatherexplore.presentation.compose

import android.content.res.Configuration
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cygni.tim.weatherexplore.R
import com.cygni.tim.weatherexplore.data.models.Point
import com.cygni.tim.weatherexplore.presentation.colors.AppYuTheme
import com.cygni.tim.weatherexplore.presentation.compose.icon.ShimmerIcon
import com.cygni.tim.weatherexplore.presentation.viewmodel.WeatherViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun WeatherScreen(
    state: WeatherViewModel.WeatherUIState,
    onNavigateToMap: (Point) -> Unit,
    onNavigateToGoogleMaps: (Point) -> Unit,
    onToggleScreenType: () -> Unit,
    onUpdateSelectedTime: (Float) -> Unit,
    onClearMessage: (WeatherViewModel.Message) -> Unit,
) {
    WeatherScreenComposable(state = state,
        onNavigateToMap = { onNavigateToMap(it) },
        onNavigateToGoogleMaps = { onNavigateToGoogleMaps(it) },
        onUpdateSelectedTime = {
            onUpdateSelectedTime(it)
        }, onToggleScreenType = {
            onToggleScreenType()
        }, onDismissMessage = {
            onClearMessage(it)
        }
    )
}

@Composable
fun WeatherScreenComposable(
    state: WeatherViewModel.WeatherUIState,
    onNavigateToMap: (Point) -> Unit = {},
    onNavigateToGoogleMaps: (Point) -> Unit = {},
    onUpdateSelectedTime: (Float) -> Unit = {},
    onToggleScreenType: () -> Unit = {},
    onDismissMessage: (WeatherViewModel.Message) -> Unit = {}
) {

    val isPreview: Boolean = LocalInspectionMode.current
    CompositionLocalProvider(
        LocalPreviewState provides WeatherPreviewState(
            skipAnimations = isPreview,
            showSlider = isPreview
        )
    ) {
        when (state) {
            is WeatherViewModel.WeatherUIState.WeatherUI -> WeatherUIComposable(
                state = state,
                onNavigateToMap = onNavigateToMap,
                onNavigateToGoogleMaps = onNavigateToGoogleMaps,
                onUpdateSelectedTime = onUpdateSelectedTime,
                onTimelineClicked = onToggleScreenType,
                dismissMessage = onDismissMessage,
            )

            is WeatherViewModel.WeatherUIState.WeatherTimelineUI -> WeatherTimelineScreen(
                state
            )

            is WeatherViewModel.WeatherUIState.FailureUIState -> FailureComposable(state.message)
            WeatherViewModel.WeatherUIState.PendingUIState -> PendingComposable()
        }
    }
}

@Composable
fun WeatherUIComposable(
    state: WeatherViewModel.WeatherUIState.WeatherUI,
    onNavigateToMap: (Point) -> Unit,
    onNavigateToGoogleMaps: (Point) -> Unit,
    onUpdateSelectedTime: (Float) -> Unit,
    onTimelineClicked: () -> Unit,
    dismissMessage: (WeatherViewModel.Message) -> Unit
) {
    val snackbarHostState = remember { SnackbarHostState() }
    var sliderShown by remember { mutableStateOf(false) }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        bottomBar = {
            WeatherBottomAppBar(state.updatedAtString)
        },
        floatingActionButton = {
            Column {
                AnimatedVisibility(visible = sliderShown) {
                    FloatingVerticalSlider(
                        modifier = Modifier.padding(bottom = 8.dp),
                        slider = state.slider,
                        onUpdateSelectedTime = onUpdateSelectedTime
                    )
                }

                FloatingActionButton(
                    onClick = {
                        sliderShown = !sliderShown
                    },
                    containerColor = MaterialTheme.colorScheme.tertiaryContainer,
                    contentColor = MaterialTheme.colorScheme.tertiary,
                    shape = CircleShape
                ) {
                    AnimatedContent(targetState = sliderShown, label = "Animated time icon") { showSlider ->
                        if (showSlider) {
                            Icon(
                                imageVector = Icons.Default.Check,
                                contentDescription = "Choose Time icon",
                                tint = MaterialTheme.colorScheme.tertiary,
                            )
                        } else {
                            Icon(
                                painter = painterResource(id = R.drawable.progress_clock),
                                contentDescription = "Choose Time icon",
                                tint = MaterialTheme.colorScheme.tertiary,
                            )
                        }
                    }
                }
            }
        }
    ) { padding ->
        if (state.snackbarMessages.isNotEmpty()) {
            LaunchedEffect(snackbarHostState) {
                val message = state.snackbarMessages.first()
                snackbarHostState.showSnackbar(message = message.text)
                dismissMessage(message)
            }
        }

        Column(
            verticalArrangement = Arrangement.Top,
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .pointerInput(Unit) {
                    detectTapGestures(onTap = {
                        sliderShown = false
                    })
                }
        ) {
            CurrentTimeRow(state = state)

            CurrentWeatherBlock(
                state = state,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp, start = 8.dp, end = 8.dp),
                onTimelineClick = { onTimelineClicked() },
                onLocationClick = { onNavigateToMap(it) },
                onGoogleMapsClick = { onNavigateToGoogleMaps(it) },
            )
        }
    }
}

@Composable
fun CurrentTimeRow(state: WeatherViewModel.WeatherUIState.WeatherUI) {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(MaterialTheme.colorScheme.primaryContainer)
            .padding(top = 8.dp, bottom = 8.dp)
    ) {
        Text(
            text = state.selectedTime,
            fontSize = 16.sp,
            color = MaterialTheme.colorScheme.primary,
            textAlign = TextAlign.Center
        )
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CurrentWeatherBlock(
    state: WeatherViewModel.WeatherUIState.WeatherUI,
    modifier: Modifier,
    onLocationClick: (Point) -> Unit,
    onGoogleMapsClick: (Point) -> Unit,
    onTimelineClick: () -> Unit,
) {
    val skipAnimations = LocalPreviewState.current.skipAnimations
    var numItems by remember { mutableIntStateOf(if (skipAnimations) state.blocks.size else 0) }
    LaunchedEffect(Unit) {
        this.launch {
            for (i in (0..state.blocks.size)) {
                delay(200)
                numItems = i
            }
        }
    }

    FlowRow(
        modifier
            .background(color = MaterialTheme.colorScheme.background, shape = RoundedCornerShape(8.dp))
            .padding(horizontal = 8.dp)
            .verticalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        CompositionLocalProvider(LocalGridSize provides GridSize()) {
            for (i in 0 until state.blocks.size) {
                AnimatedVisibility(visible = i < numItems, enter = fadeIn(), exit = fadeOut()) {
                    when (val item = state.blocks[i]) {
                        is WeatherViewModel.WeatherBlock.TempWithSymbolIcon -> {
                            TempWithWeatherIcon(state = item)
                        }

                        is WeatherViewModel.WeatherBlock.WindWithStrength -> {
                            WindDirectionWithStrength(state = item)
                        }

                        is WeatherViewModel.WeatherBlock.CloudCoverage -> {
                            CloudCoverItem(state = item)
                        }

                        is WeatherViewModel.WeatherBlock.PrecipitationPotential -> {
                            PrecipitationPotential(state = item)
                        }

                        is WeatherViewModel.WeatherBlock.PrecipitationAmount -> {
                            PrecipitationAmount(state = item, onClick = { onTimelineClick() })
                        }

                        is WeatherViewModel.WeatherBlock.MapLink.GoToMap -> {
                            GoToMapItem(
                                state = item,
                                "Show on Map",
                                onClick = {
                                    onLocationClick(item.point)
                                })
                        }

                        is WeatherViewModel.WeatherBlock.MapLink.GoToGoogleMaps -> {
                            GoToMapItem(
                                state = item,
                                "Google Maps",
                                onClick = {
                                    onGoogleMapsClick(item.point)
                                })
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun PendingComposable() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxHeight()) {
            CircularProgressIndicator(
                color = MaterialTheme.colorScheme.tertiary,
                modifier = Modifier
                    .size(64.dp)
            )
        }
    }
}

@Composable
fun FailureComposable(message: String) {
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { padding ->
        LaunchedEffect(snackbarHostState) {
            snackbarHostState.showSnackbar(message = message, actionLabel = "Ok")
        }

        Box(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            ShimmerIcon(
                painterResource(id = R.drawable.alert_circle_outline),
                listOf(
                    MaterialTheme.colorScheme.surfaceVariant,
                    MaterialTheme.colorScheme.background,
                    MaterialTheme.colorScheme.surfaceVariant,
                ), modifier = Modifier.size(128.dp)
            )
        }
    }
}

@Composable
fun WeatherBottomAppBar(updatedAtString: String) {
    BottomAppBar(
        containerColor = MaterialTheme.colorScheme.primaryContainer,
        contentColor = MaterialTheme.colorScheme.primary,
        modifier = Modifier.height(42.dp)
    ) {

    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .background(color = MaterialTheme.colorScheme.primaryContainer)
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Text(
            text = updatedAtString,
            fontSize = 16.sp,
            color = MaterialTheme.colorScheme.primary,
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showSystemUi = true, apiLevel = 33, showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun WeatherScreenPreview() {
    AppYuTheme {
        WeatherScreenComposable(
            state = weatherPreviewState(),
        )
    }
}

@Preview(showBackground = true, apiLevel = 33, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun WeatherScreenNightPreview() {
    AppYuTheme {
        WeatherScreenComposable(
            state = weatherPreviewState(),
        )
    }
}

@Preview(showBackground = true, apiLevel = 33, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun WeatherScreenFailurePreview() {
    AppYuTheme {
        WeatherScreenComposable(
            state = WeatherViewModel.WeatherUIState.FailureUIState("Failed to load weather"),
        )
    }
}

@Preview(showBackground = true, apiLevel = 33, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun WeatherScreenProgressPreview() {
    AppYuTheme {
        WeatherScreenComposable(
            state = WeatherViewModel.WeatherUIState.PendingUIState,
        )
    }
}

private fun weatherPreviewState() = Point(
    lat = 59.326038,
    lon = 17.8172507
).let { point ->
    WeatherViewModel.WeatherUIState.WeatherUI(
        location = point,
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
            WeatherViewModel.WeatherBlock.MapLink.GoToMap(point),
            WeatherViewModel.WeatherBlock.MapLink.GoToGoogleMaps(point)
        )
    )
}

val LocalGridSize = compositionLocalOf { GridSize() }
val LocalPreviewState = compositionLocalOf { WeatherPreviewState(skipAnimations = false, showSlider = false) }

data class GridSize(
    val height: Dp = 128.dp,
    val width: Dp = 128.dp,
)

data class WeatherPreviewState(
    val skipAnimations: Boolean,
    val showSlider: Boolean
)
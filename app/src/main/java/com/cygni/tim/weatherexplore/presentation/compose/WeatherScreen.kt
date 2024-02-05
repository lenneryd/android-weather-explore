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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
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
    onToggleScreenType: () -> Unit,
    onUpdateSelectedTime: (Float) -> Unit,
    onClearMessage: (WeatherViewModel.Message) -> Unit,
) {
    WeatherScreenComposable(state = state, null, onNavigateToMap,
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
    previewState: WeatherPreviewState?,
    onNavigateToMap: (Point) -> Unit = {},
    onUpdateSelectedTime: (Float) -> Unit = {},
    onToggleScreenType: () -> Unit = {},
    onDismissMessage: (WeatherViewModel.Message) -> Unit = {}
) {
    when (state) {
        is WeatherViewModel.WeatherUIState.WeatherUI -> WeatherUIComposable(
            state,
            previewState,
            onNavigateToMap,
            onUpdateSelectedTime,
            onToggleScreenType,
            onDismissMessage,
        )

        is WeatherViewModel.WeatherUIState.WeatherTimelineUI -> WeatherTimelineScreen(
            state
        )

        is WeatherViewModel.WeatherUIState.FailureUIState -> FailureComposable(state.message)
        WeatherViewModel.WeatherUIState.PendingUIState -> PendingComposable()
    }
}

@Composable
fun WeatherUIComposable(
    state: WeatherViewModel.WeatherUIState.WeatherUI,
    previewState: WeatherPreviewState?,
    onNavigateToMap: (Point) -> Unit,
    onUpdateSelectedTime: (Float) -> Unit,
    onTimelineClicked: () -> Unit,
    dismissMessage: (WeatherViewModel.Message) -> Unit
) {
    val snackbarHostState = remember { SnackbarHostState() }
    var sliderShown by remember { mutableStateOf(previewState?.showSlider ?: false) }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        bottomBar = {
            WeatherBottomAppBar(state.updatedAtString)
        },
        floatingActionButton = {
            Column {
                AnimatedVisibility(visible = sliderShown) {
                    floatingVerticalSlider(
                        slider = state.slider,
                        modifier = Modifier.padding(bottom = 8.dp),
                        onUpdateSelectedTime = onUpdateSelectedTime
                    )
                }

                FloatingActionButton(
                    onClick = { sliderShown = !sliderShown },
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
                state = state, previewState = previewState, modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp, start = 8.dp, end = 8.dp),
                onLocationClick = { onNavigateToMap(it) },
                onTimelineClick = { onTimelineClicked() }
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
    previewState: WeatherPreviewState?,
    modifier: Modifier,
    onLocationClick: (Point) -> Unit,
    onTimelineClick: () -> Unit,
) {
    var numItems by remember { mutableIntStateOf(if (previewState?.skipAnimations == true) state.blocks.size else 0) }
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
        for (i in 0 until state.blocks.size) {
            AnimatedVisibility(visible = i < numItems, enter = fadeIn(), exit = fadeOut()) {
                when (val item = state.blocks[i]) {
                    is WeatherViewModel.WeatherBlock.TempWithSymbolIcon -> {
                        tempWithWeatherIcon(state = item)
                    }

                    is WeatherViewModel.WeatherBlock.WindWithStrength -> {
                        windDirectionWithStrength(state = item)
                    }

                    is WeatherViewModel.WeatherBlock.CloudCoverage -> {
                        cloudCoverItem(state = item)
                    }

                    is WeatherViewModel.WeatherBlock.PrecipitationPotential -> {
                        precipitationPotential(state = item)
                    }

                    is WeatherViewModel.WeatherBlock.PrecipitationAmount -> {
                        precipitationAmount(state = item, onClick = { onTimelineClick() })
                    }

                    is WeatherViewModel.WeatherBlock.GoToMap -> {
                        GoToMapItem(
                            state = item,
                            onClick = {
                            onLocationClick(item.point)
                        })
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

@Preview(showSystemUi = true, showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun WeatherScreenPreview() {
    AppYuTheme {
        WeatherScreenComposable(
            state = weatherPreviewState(),
            previewState = WeatherPreviewState()
        )
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun WeatherScreenNightPreview() {
    AppYuTheme {
        WeatherScreenComposable(
            state = weatherPreviewState(),
            previewState = WeatherPreviewState()
        )
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun WeatherScreenFailurePreview() {
    AppYuTheme {
        WeatherScreenComposable(
            state = WeatherViewModel.WeatherUIState.FailureUIState("Failed to load weather"),
            previewState = WeatherPreviewState()
        )
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun WeatherScreenProgressPreview() {
    AppYuTheme {
        WeatherScreenComposable(
            state = WeatherViewModel.WeatherUIState.PendingUIState,
            previewState = WeatherPreviewState()
        )
    }
}

private fun weatherPreviewState() = WeatherViewModel.WeatherUIState.WeatherUI(
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

data class WeatherPreviewState(
    val skipAnimations: Boolean = true,
    val showSlider: Boolean = true
)
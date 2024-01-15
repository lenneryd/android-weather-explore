package com.cygni.tim.weatherexplore.presentation.compose

import android.content.res.Configuration
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
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
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
    viewModel: WeatherViewModel,
    onNavigateToMap: (Point) -> Unit,
    onDismissMessage: (WeatherViewModel.Message) -> Unit
) {
    val uiState by viewModel.uiState.collectAsState(WeatherViewModel.WeatherUIState.PendingUIState)
    WeatherScreenComposable(state = uiState, null, onNavigateToMap, onDismissMessage)
}

@Composable
fun WeatherScreenComposable(
    state: WeatherViewModel.WeatherUIState,
    previewState: WeatherPreviewState?,
    onNavigateToMap: (Point) -> Unit = {},
    onDismissMessage: (WeatherViewModel.Message) -> Unit = {}
) {

    AnimatedContent(targetState = state, label = "State Animation") { s ->
        when (s) {
            is WeatherViewModel.WeatherUIState.WeatherUI -> WeatherUIComposable(
                s,
                previewState,
                onNavigateToMap,
                onDismissMessage
            )

            is WeatherViewModel.WeatherUIState.FailureUIState -> FailureComposable(s.message)
            WeatherViewModel.WeatherUIState.PendingUIState -> PendingComposable()
        }
    }
}

@Composable
fun WeatherUIComposable(
    state: WeatherViewModel.WeatherUIState.WeatherUI,
    previewState: WeatherPreviewState?,
    onNavigateToMap: (Point) -> Unit,
    dismissMessage: (WeatherViewModel.Message) -> Unit
) {
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        bottomBar = {
            BottomAppBar(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.primary,
                modifier = Modifier.height(42.dp)
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .background(color = MaterialTheme.colorScheme.primaryContainer)
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                ) {
                    Text(
                        text = "Updated at: ${state.updatedAt} (${state.forecastAge} ago)",
                        fontSize = 16.sp,
                        color = MaterialTheme.colorScheme.primary,
                        textAlign = TextAlign.Center
                    )
                }
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { onNavigateToMap(state.location) },
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.primary
            ) {
                Icon(
                    imageVector = Icons.Default.ExitToApp,
                    contentDescription = "Map Link to location",
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(start = 8.dp, end = 8.dp)
                )
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
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            CurrentWeatherBlock(
                state = state, previewState = previewState, modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp, start = 8.dp, end = 8.dp, bottom = 24.dp)
            ) {
                onNavigateToMap(it)
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CurrentWeatherBlock(
    state: WeatherViewModel.WeatherUIState.WeatherUI,
    previewState: WeatherPreviewState?,
    modifier: Modifier,
    onLocationClick: (Point) -> Unit
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
            .padding(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        for (i in 0 until state.blocks.size) {
            AnimatedVisibility(visible = i < numItems, enter = fadeIn(), exit = fadeOut()) {
                WeatherItem(state.blocks[i])
            }
        }
    }
}

@Composable
fun WeatherItem(state: WeatherViewModel.WeatherBlock) {
    when (state) {
        is WeatherViewModel.WeatherBlock.TempWithSymbolIcon -> {
            tempWithWeatherIcon(state = state)
        }

        is WeatherViewModel.WeatherBlock.WindWithStrength -> {
            windDirectionWithStrength(state = state)
        }

        is WeatherViewModel.WeatherBlock.CloudCoverage -> {
            cloudCoverItem(state = state)
        }

        is WeatherViewModel.WeatherBlock.PrecipitationPotential -> {
            precipitationPotential(state = state)
        }

        is WeatherViewModel.WeatherBlock.PrecipitationAmount -> {
            precipitationAmount(state = state)
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
        lon =17.8172507
    ),
    updatedAt = "09:41",
    forecastAge = "14 minutes",
    listOf(
        WeatherViewModel.WeatherBlock.TempWithSymbolIcon("partlycloudy_day", "-14.3"),
        WeatherViewModel.WeatherBlock.WindWithStrength(291.0f, "SW", "3.6 m/s"),
        WeatherViewModel.WeatherBlock.CloudCoverage(69.0, "69%"),
        WeatherViewModel.WeatherBlock.PrecipitationPotential(45.0, "45%"),
        WeatherViewModel.WeatherBlock.PrecipitationAmount(
            WeatherViewModel.PrecipitationData(WeatherViewModel.PrecipitationType.Rain, "1", "2mm", 85.0, "85%"),
            WeatherViewModel.PrecipitationData(WeatherViewModel.PrecipitationType.Rain, "6", "2mm", 85.0, "85%"),
            WeatherViewModel.PrecipitationData(WeatherViewModel.PrecipitationType.Rain, "12", "2mm", 85.0, "85%"),
        )
    )
)

data class WeatherPreviewState(
    val skipAnimations: Boolean = true
)
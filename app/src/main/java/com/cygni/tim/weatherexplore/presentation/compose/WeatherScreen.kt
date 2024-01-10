package com.cygni.tim.weatherexplore.presentation.compose

import android.content.res.Configuration
import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.defaultMinSize
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
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.cygni.tim.weatherexplore.R
import com.cygni.tim.weatherexplore.data.models.Point
import com.cygni.tim.weatherexplore.data.repo.truncLatitude
import com.cygni.tim.weatherexplore.data.repo.truncLongitude
import com.cygni.tim.weatherexplore.presentation.colors.AppYuTheme
import com.cygni.tim.weatherexplore.presentation.compose.icon.ShimmerIcon
import com.cygni.tim.weatherexplore.presentation.viewmodel.WeatherViewModel

@Composable
fun WeatherScreen(
    viewModel: WeatherViewModel,
    onNavigateToMap: (Point) -> Unit,
    onDismissMessage: (WeatherViewModel.Message) -> Unit
) {
    val uiState by viewModel.uiState.collectAsState(WeatherViewModel.WeatherUIState.PendingUIState)
    WeatherScreenComposable(state = uiState, onNavigateToMap, onDismissMessage)
}

@Composable
fun WeatherScreenComposable(
    state: WeatherViewModel.WeatherUIState,
    onNavigateToMap: (Point) -> Unit = {},
    onDismissMessage: (WeatherViewModel.Message) -> Unit = {}
) {
    AnimatedContent(targetState = state, label = "State Animation") { s ->
        when (s) {
            is WeatherViewModel.WeatherUIState.WeatherUI -> WeatherUIComposable(s, onNavigateToMap, onDismissMessage)
            is WeatherViewModel.WeatherUIState.FailureUIState -> FailureComposable(s.message)
            WeatherViewModel.WeatherUIState.PendingUIState -> PendingComposable()
        }
    }
}

@Composable
fun WeatherUIComposable(
    state: WeatherViewModel.WeatherUIState.WeatherUI,
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
                        .padding(vertical = 8.dp)) {
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
                    painter = painterResource(id = R.drawable.arrow_top_right),
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
            CurrentWeatherBlock(state = state, modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp, start = 8.dp, end = 8.dp, bottom = 24.dp)
            ) {
                onNavigateToMap(it)
            }
        }
    }
}

@Composable
fun CurrentWeatherBlock(state: WeatherViewModel.WeatherUIState.WeatherUI, modifier: Modifier, onLocationClick: (Point) -> Unit) {
    Box(
        modifier = modifier
            .background(color = MaterialTheme.colorScheme.primaryContainer, shape = RoundedCornerShape(16.dp))
    ) {
        Column(
            modifier = Modifier
                .padding(top = 8.dp, bottom = 8.dp)
                .align(Alignment.TopCenter)
        ) {

            Row(modifier = Modifier.clickable {
                onLocationClick(state.location)
            }) {
                Text(
                    text = "Location: (lon: ${state.location.truncLatitude()}, lat: ${state.location.truncLongitude()})",
                    fontSize = 18.sp,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 12.dp, end = 8.dp)
                )

                Icon(
                    painter = painterResource(id = R.drawable.arrow_top_right),
                    contentDescription = "Map Link to location",
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(start = 8.dp, end = 8.dp)
                )
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

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun WeatherScreenPreview() {
    AppYuTheme {
        WeatherScreenComposable(
            state = weatherPreviewState()
        )
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun WeatherScreenNightPreview() {
    AppYuTheme {
        WeatherScreenComposable(
            state = weatherPreviewState()
        )
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun WeatherScreenFailurePreview() {
    AppYuTheme {
        WeatherScreenComposable(
            state = WeatherViewModel.WeatherUIState.FailureUIState("Failed to load weather")
        )
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun WeatherScreenProgressPreview() {
    AppYuTheme {
        WeatherScreenComposable(
            state = WeatherViewModel.WeatherUIState.PendingUIState
        )
    }
}

private fun weatherPreviewState() = WeatherViewModel.WeatherUIState.WeatherUI(
    Point(
        lat = 17.8172507,
        lon = 59.326038
    ),
    updatedAt = "09:41",
    forecastAge = "14 minutes"
)

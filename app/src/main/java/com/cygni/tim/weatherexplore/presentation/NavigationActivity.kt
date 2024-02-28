package com.cygni.tim.weatherexplore.presentation

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.cygni.tim.weatherexplore.data.models.Point
import com.cygni.tim.weatherexplore.domain.usecase.LocationUseCase
import com.cygni.tim.weatherexplore.presentation.colors.AppYuTheme
import com.cygni.tim.weatherexplore.presentation.compose.ClockScreen
import com.cygni.tim.weatherexplore.presentation.compose.MapScreen
import com.cygni.tim.weatherexplore.presentation.compose.MapWeatherScreen
import com.cygni.tim.weatherexplore.presentation.compose.NavigationScreen
import com.cygni.tim.weatherexplore.presentation.compose.WeatherScreen
import com.cygni.tim.weatherexplore.presentation.navigation.Arguments
import com.cygni.tim.weatherexplore.presentation.navigation.Route
import com.cygni.tim.weatherexplore.presentation.navigation.asRoute
import com.cygni.tim.weatherexplore.presentation.navigation.get
import com.cygni.tim.weatherexplore.presentation.navigation.resolved
import com.cygni.tim.weatherexplore.presentation.navigation.routeDefinition
import com.cygni.tim.weatherexplore.presentation.viewmodel.MapScreenViewModel
import com.cygni.tim.weatherexplore.presentation.viewmodel.MapWeatherScreenViewModel
import com.cygni.tim.weatherexplore.presentation.viewmodel.WeatherViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class NavigationActivity : AppCompatActivity() {

    @Inject
    lateinit var locationUseCase: LocationUseCase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            NavigationActivityScreen(onNavigateToGoogleMaps = { navigateToGoogleMaps(it) }, onCloseApp = { finish() })
        }

        handleIntent()
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)

        handleIntent()
    }

    private fun handleIntent() {
        lifecycleScope.launch {
            val result = handleSendText(intent).first()
            when {
                result.isSuccess -> locationUseCase.setLocation(result.getOrThrow())
                result.isFailure -> Log.w(
                    NavigationActivity::class.java.simpleName,
                    "Location update failure: ${result.exceptionOrNull()?.message}"
                )
            }
        }
    }

    private fun navigateToGoogleMaps(point: Point): Boolean {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("geo:${point.lat},${point.lon}"))
        //mapIntent.setPackage("com.google.android.apps.maps")
        return try {
            startActivity(intent)
            true
        } catch (e: ActivityNotFoundException) {
            false
        }
    }

    private fun handleSendText(intent: Intent): Flow<Result<Point>> = callbackFlow {
        val sharedText = intent.getStringExtra(Intent.EXTRA_TEXT);
        if (sharedText != null) {
            Log.d(NavigationActivity::class.java.simpleName, "Got shared text: $sharedText")

            try {
                // 59.3271437, 17.8170686
                val (latitude, longitude) = sharedText.split(",").map { it.trim().toDoubleOrNull() }

                if (latitude != null && longitude != null) {
                    trySend(Result.success(Point(latitude, longitude)))
                } else {
                    trySend(Result.failure(Exception("No location in shared text: $sharedText")))
                }
                close()
            } catch (e: Exception) {
                e.printStackTrace()
                trySend(Result.failure(e))
                close()
            }
        }

        awaitClose()
    }
}

@Composable
@Preview
fun NavigationActivityScreen(
    onNavigateToGoogleMaps: (Point) -> Unit = {},
    onCloseApp: () -> Unit = {}
) {
    val navController = rememberNavController()
    AppYuTheme {
        Scaffold(
            topBar = { NavigationTopBar(navController, onCloseApp) }
        ) { padding ->
            NavigationNavHost(padding, navController, onNavigateToGoogleMaps)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationTopBar(navController: NavHostController, onCloseApp: () -> Unit) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                navController.currentDestination?.asRoute()?.title.orEmpty(),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.primary
            )
        },
        navigationIcon = {
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = "Back button",
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .padding(start = 8.dp)
                    .size(32.dp)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = rememberRipple(bounded = false)
                    ) {
                        if (!navController.navigateUp()) {
                            onCloseApp()
                        }
                    }
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.tertiaryContainer,
            titleContentColor = MaterialTheme.colorScheme.onTertiaryContainer
        )
    )
}

@Composable
fun NavigationNavHost(padding: PaddingValues, navController: NavHostController, onNavigateToGoogleMaps: (Point) -> Unit) {
    NavHost(
        navController = navController,
        startDestination = Route.Navigation.routeDefinition(),
        modifier = Modifier.padding(padding)
    ) {
        composable(Route.Navigation.value) {
            NavigationScreen(
                onClock = { navController.navigate(Route.Clock.resolved()) },
                onWeather = { navController.navigate(Route.Weather.resolved(it.value)) },
                onMap = { navController.navigate(Route.Map.resolved()) }
            )
        }
        composable(
            Route.Weather.routeDefinition(),
            arguments = listOf(
                navArgument(Arguments.Type.value) {
                    defaultValue = WeatherViewModel.DisplayType.Blocks.value
                }
            )
        ) {
            WeatherScreenNav(
                displayType = WeatherViewModel.DisplayType.entries.firstOrNull { enum ->
                    enum.value == it.get(Arguments.Type)
                } ?: WeatherViewModel.DisplayType.Blocks,
                navigateToMap = { navController.navigate(Route.WeatherMap.resolved()) },
                onNavigateToGoogleMaps = { onNavigateToGoogleMaps(it) }
            )
        }
        composable(Route.Clock.value) {
            ClockScreenNav()
        }
        composable(Route.Map.value) {
            MapScreenNav()
        }

        composable(Route.WeatherMap.value) {
            MapWeatherScreenNav()
        }
    }
}

@Composable
fun ClockScreenNav() {
    ClockScreen(hiltViewModel())
}

@Composable
fun WeatherScreenNav(
    displayType: WeatherViewModel.DisplayType?,
    navigateToMap: (Point) -> Unit,
    onNavigateToGoogleMaps: (Point) -> Unit,
) {
    val vm = hiltViewModel<WeatherViewModel>()
    vm.onSwitchView(displayType ?: WeatherViewModel.DisplayType.Blocks)
    val uiState by vm.uiState.collectAsState(WeatherViewModel.WeatherUIState.PendingUIState)
    WeatherScreen(
        state = uiState,
        onNavigateToMap = { navigateToMap(it) },
        onNavigateToGoogleMaps = { onNavigateToGoogleMaps(it) },
        onToggleScreenType = { vm.toggleView() },
        onUpdateSelectedTime = { vm.onUpdateSelectedTime(it) },
        onClearMessage = { vm.clearMessage(it) }
    )
}

@Composable
fun MapScreenNav() {
    val vm = hiltViewModel<MapScreenViewModel>()
    val uiState by vm.uiState.collectAsState()
    MapScreen(mapState = uiState, onChangedPosition = { vm.onChangedPosition(it) })
}

@Composable
fun MapWeatherScreenNav() {
    val vm = hiltViewModel<MapWeatherScreenViewModel>()
    val uiState by vm.uiState.collectAsState()
    MapWeatherScreen(mapWeatherState = uiState)
}

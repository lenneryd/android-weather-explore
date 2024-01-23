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
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import com.cygni.tim.weatherexplore.data.models.Point
import com.cygni.tim.weatherexplore.domain.usecase.LocationUseCase
import com.cygni.tim.weatherexplore.presentation.colors.AppYuTheme
import com.cygni.tim.weatherexplore.presentation.compose.ClockScreen
import com.cygni.tim.weatherexplore.presentation.compose.NavigationScreen
import com.cygni.tim.weatherexplore.presentation.compose.WeatherScreen
import com.cygni.tim.weatherexplore.presentation.destinations.ClockScreenNavDestination
import com.cygni.tim.weatherexplore.presentation.destinations.NavigationScreenNavDestination
import com.cygni.tim.weatherexplore.presentation.destinations.TypedDestination
import com.cygni.tim.weatherexplore.presentation.destinations.WeatherScreenNavDestination
import com.cygni.tim.weatherexplore.presentation.viewmodel.WeatherViewModel
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.manualcomposablecalls.composable
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
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
            NavigationActivityScreen(onNavigateToMap = { navigateToMap(it) }, onCloseApp = { finish() })
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

    private fun navigateToMap(point: Point): Boolean {
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun NavigationActivityScreen(onNavigateToMap: (Point) -> Unit = {}, onCloseApp: () -> Unit = {}) {
    val navController = rememberNavController()
    AppYuTheme {
        Scaffold(
            topBar = {

                val currentDestination =
                    navController.appCurrentDestinationAsState().value ?: NavGraphs.root.startAppDestination

                CenterAlignedTopAppBar(
                    title = {
                        Text(
                            currentDestination.toDestinationTitle(),
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
        ) { padding ->
            DestinationsNavHost(
                navController = navController,
                navGraph = NavGraphs.root,
                modifier = Modifier.padding(padding)
            ) {
                composable(WeatherScreenNavDestination) {
                    WeatherScreenNav(
                        displayType = this.navArgs.displayType ?: WeatherViewModel.DisplayType.Blocks,
                        onNavigateToMap = { onNavigateToMap(it) },
                        onToggleScreenType = {
                            this.destinationsNavigator.navigate(WeatherScreenNavDestination(it.toggle()))
                        }
                    )
                }
            }
        }
    }
}

private fun <T> TypedDestination<T>.toDestinationTitle(): String = when (this) {
    ClockScreenNavDestination -> "Clock"
    NavigationScreenNavDestination -> "Navigation"
    WeatherScreenNavDestination -> "Weather"
}

@RootNavGraph(start = true)
@Destination
@Composable
fun NavigationScreenNav(navigator: DestinationsNavigator) {
    NavigationScreen(
        onClock = { navigator.navigate(ClockScreenNavDestination) },
        onWeather = { navigator.navigate(WeatherScreenNavDestination(it)) },
    )
}

@Destination
@Composable
fun ClockScreenNav() {
    ClockScreen(hiltViewModel())
}

@Destination
@Composable
fun WeatherScreenNav(
    displayType: WeatherViewModel.DisplayType?,
    onNavigateToMap: (Point) -> Unit,
    onToggleScreenType: (WeatherViewModel.DisplayType) -> Unit
) {
    WeatherScreen(displayType, hiltViewModel(), onNavigateToMap, onToggleScreenType)
}
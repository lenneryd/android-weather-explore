package com.cygni.tim.weatherexplore.presentation

import android.app.Activity
import android.app.Activity.RESULT_OK
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
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
import com.cygni.tim.weatherexplore.presentation.compose.DocumentScanScreen
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
import com.cygni.tim.weatherexplore.presentation.viewmodel.DocumentScanViewModel
import com.cygni.tim.weatherexplore.presentation.viewmodel.MapScreenViewModel
import com.cygni.tim.weatherexplore.presentation.viewmodel.MapWeatherScreenViewModel
import com.cygni.tim.weatherexplore.presentation.viewmodel.WeatherViewModel
import com.google.mlkit.vision.documentscanner.GmsDocumentScannerOptions
import com.google.mlkit.vision.documentscanner.GmsDocumentScanning
import com.google.mlkit.vision.documentscanner.GmsDocumentScanningResult
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
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
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
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
        composable(Route.Navigation.value) { _ ->
            NavigationScreen(
                onClock = { navController.navigate(Route.Clock.resolved()) },
                onWeather = { navController.navigate(Route.Weather.resolved(it.value)) },
                onMap = { navController.navigate(Route.Map.resolved()) },
                onScanning = { navController.navigate(Route.DocumentScan.resolved()) }
            )
        }
        composable(
            Route.Weather.routeDefinition(),
            arguments = listOf(
                navArgument(Arguments.Type.value) {
                    defaultValue = WeatherViewModel.DisplayType.Blocks.value
                }
            )
        ) { backstack ->
            WeatherScreenNav(
                displayType = WeatherViewModel.DisplayType.entries.firstOrNull { enum ->
                    enum.value == backstack.get(Arguments.Type)
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

        composable(Route.DocumentScan.value) {
            DocumentScanNav()
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

@Composable
fun DocumentScanNav() {
    val vm = hiltViewModel<DocumentScanViewModel>()
    val uiState by vm.uiState.collectAsState()

    val client by remember {
        mutableStateOf(
            GmsDocumentScanning.getClient(
                GmsDocumentScannerOptions.Builder()
                    .setGalleryImportAllowed(false)
                    .setPageLimit(2)
                    .setResultFormats(GmsDocumentScannerOptions.RESULT_FORMAT_JPEG)
                    .setScannerMode(GmsDocumentScannerOptions.SCANNER_MODE_FULL)
                    .build()
            )
        )
    }


    val activityLauncher = rememberLauncherForActivityResult(contract = ActivityResultContracts.StartIntentSenderForResult()) { result ->
        val scanResult = when (result.resultCode) {
            RESULT_OK -> {
                val data = GmsDocumentScanningResult.fromActivityResultIntent(result.data)
                data?.pages?.let { pages ->
                    pages.firstOrNull()?.imageUri
                }
            }

            else -> {
                Log.w(NavigationActivity::class.java.simpleName, "Document scan failed")
                null
            }
        }

        Log.d(NavigationActivity::class.java.simpleName, "Received scanning result: $scanResult")
        vm.onScanResult(scanResult)
    }

    when (uiState) {
        is DocumentScanViewModel.DocumentScanUIState.StartScanningState -> {
            val activity = LocalContext.current as Activity
            LaunchedEffect(Unit) {

                val intent = client.getStartScanIntent(activity).addOnSuccessListener {
                    IntentSenderRequest.Builder(it).build()
                }
                    .addOnFailureListener { error ->
                        Log.w(
                            NavigationActivity::class.java.simpleName,
                            "Document scan failed: ${error.message}"
                        )
                    }
                    .await()
                vm.onScanIntent(intent)
            }
        }

        is DocumentScanViewModel.DocumentScanUIState.ScanWithIntent -> {
            activityLauncher.launch(
                IntentSenderRequest.Builder(
                    (uiState as DocumentScanViewModel.DocumentScanUIState.ScanWithIntent).intent
                ).build()
            )
        }

        else -> {}
    }

    DocumentScanScreen(state = uiState, scanClicked = {
        vm.onScanClicked()
    },
        generateOCRClicked = { src ->
            vm.onGenerateOCRClicked(src)
        }
    ) {
        vm.onRetryClicked()
    }
}

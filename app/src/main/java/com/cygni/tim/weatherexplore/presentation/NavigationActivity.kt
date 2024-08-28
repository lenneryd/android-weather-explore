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
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.SharedTransitionScope
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
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import com.cygni.tim.weatherexplore.data.models.Point
import com.cygni.tim.weatherexplore.domain.usecase.LocationUseCase
import com.cygni.tim.weatherexplore.presentation.colors.AppYuTheme
import com.cygni.tim.weatherexplore.presentation.compose.ClockScreen
import com.cygni.tim.weatherexplore.presentation.compose.DocumentScanScreen
import com.cygni.tim.weatherexplore.presentation.compose.MapScreen
import com.cygni.tim.weatherexplore.presentation.compose.MapWeatherScreen
import com.cygni.tim.weatherexplore.presentation.compose.NavigationScreen
import com.cygni.tim.weatherexplore.presentation.compose.WeatherDetailScreen
import com.cygni.tim.weatherexplore.presentation.compose.WeatherScreenComposable
import com.cygni.tim.weatherexplore.presentation.compose.WeatherTimelineScreen
import com.cygni.tim.weatherexplore.presentation.navigation.Route
import com.cygni.tim.weatherexplore.presentation.navigation.asRoute
import com.cygni.tim.weatherexplore.presentation.navigation.resolved
import com.cygni.tim.weatherexplore.presentation.navigation.routeDefinition
import com.cygni.tim.weatherexplore.presentation.viewmodel.DocumentScanViewModel
import com.cygni.tim.weatherexplore.presentation.viewmodel.MapScreenViewModel
import com.cygni.tim.weatherexplore.presentation.viewmodel.MapWeatherScreenViewModel
import com.cygni.tim.weatherexplore.presentation.viewmodel.WeatherDetailsViewModel
import com.cygni.tim.weatherexplore.presentation.viewmodel.WeatherTimelineViewModel
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

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun NavigationNavHost(padding: PaddingValues, navController: NavHostController, onNavigateToGoogleMaps: (Point) -> Unit) {
    SharedTransitionLayout {
        CompositionLocalProvider(LocalSharedElementTransitionScope provides this@SharedTransitionLayout) {
            NavHost(
                navController = navController,
                startDestination = Route.Navigation.routeDefinition(),
                modifier = Modifier.padding(padding)
            ) {
                composable(Route.Navigation.value) { _ ->
                    CompositionLocalProvider(LocalAnimatedVisibilityScope provides this@composable) {
                        NavigationScreen(
                            onClock = { navController.navigate(Route.Clock.resolved()) },
                            onWeatherBlocks = { navController.navigate(Route.WeatherBlocks.resolved()) },
                            onWeatherTimeline = { navController.navigate(Route.WeatherTimeline.resolved()) },
                            onMap = { navController.navigate(Route.Map.resolved()) },
                            onScanning = { navController.navigate(Route.DocumentScan.resolved()) }
                        )
                    }
                }
                composable(Route.WeatherBlocks.value) {
                    val vm = hiltViewModel<WeatherViewModel>()
                    val uiState = vm.uiState.collectAsState(initial = WeatherViewModel.WeatherUIState.LoadingWeather)

                    CompositionLocalProvider(LocalAnimatedVisibilityScope provides this@composable) {
                        WeatherBlocksScreenNav(
                            uiState,
                            navigateToMap = { navController.navigate(Route.WeatherMap.resolved()) },
                            onNavigateToGoogleMaps = { onNavigateToGoogleMaps(it) },
                            onUpdateSelectedTime = { position, finished -> vm.onUpdateSelectedTime(position, finished) },
                            onNavigateToTimeline = { navController.navigate(Route.WeatherTimeline.resolved()) },
                            onNavigateToDetails = { navController.navigate(Route.WeatherDetails.resolved()) }

                        )
                    }
                }

                composable(Route.WeatherTimeline.value) {
                    val vm = hiltViewModel<WeatherTimelineViewModel>()
                    val uiState = vm.uiState.collectAsState(initial = WeatherTimelineViewModel.WeatherTimeline.LoadingWeatherTimeline)

                    CompositionLocalProvider(LocalAnimatedVisibilityScope provides this@composable) {
                        WeatherTimelineNav(uiState)
                    }
                }

                composable(Route.WeatherDetails.value) {
                    val vm = hiltViewModel<WeatherDetailsViewModel>()
                    val uiState = vm.uiState.collectAsState(initial = WeatherDetailsViewModel.WeatherDetails.LoadingWeatherDetails())

                    CompositionLocalProvider(LocalAnimatedVisibilityScope provides this@composable) {
                        WeatherDetailsNav(uiState)
                    }
                }

                composable(Route.Clock.value) {
                    CompositionLocalProvider(LocalAnimatedVisibilityScope provides this@composable) {
                        ClockScreenNav()
                    }
                }
                composable(Route.Map.value) {
                    CompositionLocalProvider(LocalAnimatedVisibilityScope provides this@composable) {
                        MapScreenNav()
                    }
                }

                composable(Route.WeatherMap.value) {
                    CompositionLocalProvider(LocalAnimatedVisibilityScope provides this@composable) {
                        MapWeatherScreenNav()
                    }
                }

                composable(Route.DocumentScan.value) {
                    CompositionLocalProvider(LocalAnimatedVisibilityScope provides this@composable) {
                        DocumentScanNav()
                    }
                }
            }
        }
    }
}

@Composable
fun ClockScreenNav() {
    ClockScreen(hiltViewModel())
}

@Composable
fun WeatherBlocksScreenNav(
    uiState: State<WeatherViewModel.WeatherUIState>,
    navigateToMap: (Point) -> Unit,
    onNavigateToGoogleMaps: (Point) -> Unit,
    onUpdateSelectedTime: (position: Float, finished: Boolean) -> Unit,
    onNavigateToTimeline: () -> Unit,
    onNavigateToDetails: () -> Unit
) {
    WeatherScreenComposable(
        state = uiState.value,
        onNavigateToMap = { navigateToMap(it) },
        onNavigateToGoogleMaps = onNavigateToGoogleMaps,
        onUpdateSelectedTime = { position, finished -> onUpdateSelectedTime(position, finished) },
        onNavigateToTimeline = { onNavigateToTimeline() },
        onShowDetails = { onNavigateToDetails() },
    )
}

@Composable
fun WeatherTimelineNav(
    uiState: State<WeatherTimelineViewModel.WeatherTimeline>
) {
    WeatherTimelineScreen(uiState.value)
}

@Composable
fun WeatherDetailsNav(uiState: State<WeatherDetailsViewModel.WeatherDetails>) {
    WeatherDetailScreen(state = uiState.value)
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
                IntentSenderRequest.Builder((uiState as DocumentScanViewModel.DocumentScanUIState.ScanWithIntent).intent).build()
            )
        }

        else -> {}
    }

    DocumentScanScreen(
        state = uiState,
        scanClicked = {
            vm.onScanClicked()
        },
        generateOCRClicked = { src ->
            vm.onGenerateOCRClicked(src)
        }
    ) {
        vm.onRetryClicked()
    }
}

val LocalAnimatedVisibilityScope: ProvidableCompositionLocal<AnimatedVisibilityScope?> = compositionLocalOf { null }

@OptIn(ExperimentalSharedTransitionApi::class)
val LocalSharedElementTransitionScope: ProvidableCompositionLocal<SharedTransitionScope?> = compositionLocalOf { null }

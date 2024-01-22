package com.cygni.tim.weatherexplore.presentation

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.core.os.bundleOf
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.fragment
import androidx.navigation.navArgument
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.cygni.tim.weatherexplore.R
import com.cygni.tim.weatherexplore.data.models.Point
import com.cygni.tim.weatherexplore.databinding.NavigationActivityBinding
import com.cygni.tim.weatherexplore.presentation.colors.AppYuTheme
import com.cygni.tim.weatherexplore.presentation.compose.ClockScreen
import com.cygni.tim.weatherexplore.presentation.compose.ColorsScreenComposable
import com.cygni.tim.weatherexplore.presentation.compose.MainScreenComposable
import com.cygni.tim.weatherexplore.presentation.compose.NavigationScreen
import com.cygni.tim.weatherexplore.presentation.compose.WeatherScreen
import com.cygni.tim.weatherexplore.presentation.viewmodel.WeatherViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NavigationActivity : AppCompatActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {
            AppYuTheme {
                Scaffold(
                    topBar = {
                        CenterAlignedTopAppBar(
                            title = {
                                Text("My App")
                            },
                            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                                containerColor = MaterialTheme.colorScheme.tertiaryContainer,
                                titleContentColor = MaterialTheme.colorScheme.onTertiaryContainer
                            )
                        )
                    }
                ) { padding ->
                    NavigationActivityNavHost(onNavigateToMap = { this.navigateToMap(it) }, modifier = Modifier.padding(padding))
                }
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
}

@Composable
fun NavigationActivityNavHost(
    modifier: Modifier = Modifier,
    startDestination: String = "navigation",
    onNavigateToMap: (Point) -> Boolean,
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable("navigation") {
            NavigationScreen(
                onClock = { navController.navigate("clock") },
                onWeather = { navController.navigate("weather/${it.value}") },
            )
        }

        composable("clock") {
            ClockScreen(viewModel = hiltViewModel())
        }

        composable("weather/{display}") { entry ->
            WeatherScreen(
                displayType = WeatherViewModel.DisplayType.fromString(entry.arguments?.getString("display")!!),
                viewModel = hiltViewModel(),
                onNavigateToMap = { onNavigateToMap(it) },
                onToggleScreenType = {
                    navController.navigate("weather/${it.value}")
                },
            )
        }
    }
}

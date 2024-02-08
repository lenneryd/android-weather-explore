package com.cygni.tim.weatherexplore.presentation.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.cygni.tim.weatherexplore.R
import com.cygni.tim.weatherexplore.data.models.Point
import com.cygni.tim.weatherexplore.presentation.icons.WeatherIcons
import com.cygni.tim.weatherexplore.presentation.viewmodel.MapScreenViewModel
import com.cygni.tim.weatherexplore.presentation.viewmodel.MapWeatherScreenViewModel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.clustering.Clustering
import com.google.maps.android.compose.rememberCameraPositionState
import kotlinx.coroutines.launch

@Composable
fun MapScreen(mapState: MapScreenViewModel.MapState, onChangedPosition: (LatLng) -> Unit = {}) {
    if (mapState.pos != null) {
        var point by remember {
            mutableStateOf(LatLng(mapState.pos.lat, mapState.pos.lon))
        }

        val cameraPositionState: CameraPositionState = rememberCameraPositionState {
            position = CameraPosition.fromLatLngZoom(LatLng(mapState.pos.lat, mapState.pos.lon), 11f)
        }

        var uiSettings by remember { mutableStateOf(MapUiSettings()) }
        var properties by remember {
            mutableStateOf(MapProperties(mapType = MapType.SATELLITE))
        }

        val scope = rememberCoroutineScope()

        Box(Modifier.fillMaxSize()) {
            GoogleMap(
                modifier = Modifier.fillMaxSize(),
                properties = properties,
                uiSettings = uiSettings,
                cameraPositionState = cameraPositionState,
                onMapClick = {
                    scope.launch {
                        point = it
                        cameraPositionState.animate(CameraUpdateFactory.newLatLng(it))
                        onChangedPosition(it)
                    }
                }
            ) {
                Marker(
                    state = MarkerState(position = point),
                    title = "Position",
                    snippet = "Weather Source"
                )
            }
        }
    }
}

@Composable
fun MapWeatherScreen(mapWeatherState: MapWeatherScreenViewModel.MapWeatherState) {
    when (mapWeatherState) {
        is MapWeatherScreenViewModel.MapWeatherState.Weather -> {
            var initial by remember {
                mutableStateOf(LatLng(mapWeatherState.point.lat, mapWeatherState.point.lon))
            }

            var clickedPoint by remember {
                mutableStateOf(LatLng(mapWeatherState.point.lat, mapWeatherState.point.lon))
            }
            val cameraPositionState: CameraPositionState = rememberCameraPositionState {
                position = CameraPosition.fromLatLngZoom(LatLng(mapWeatherState.point.lat, mapWeatherState.point.lon), 11f)
            }

            var uiSettings by remember { mutableStateOf(MapUiSettings()) }
            var properties by remember {
                mutableStateOf(MapProperties(mapType = MapType.SATELLITE))
            }

            val scope = rememberCoroutineScope()

            Box(Modifier.fillMaxSize()) {
                GoogleMap(
                    modifier = Modifier.fillMaxSize(),
                    properties = properties,
                    uiSettings = uiSettings,
                    cameraPositionState = cameraPositionState,
                    onMapClick = {
                        scope.launch {
                            clickedPoint = it
                            cameraPositionState.animate(CameraUpdateFactory.newLatLng(it))
                        }
                    }
                ) {
                    Marker(
                        state = MarkerState(position = initial),
                        title = "Position",
                        snippet = "Weather Source"
                    )

                    Clustering(
                        items = mapWeatherState.items,
                        // Optional: Handle clicks on clusters, cluster items, and cluster item info windows
                        onClusterClick = {
                            scope.launch {
                                cameraPositionState.animate(
                                    CameraUpdateFactory.newLatLngZoom(
                                        it.position,
                                        cameraPositionState.position.zoom + 1
                                    )
                                )
                            }
                            false
                        },
                        onClusterItemClick = { false },
                        onClusterItemInfoWindowClick = { false },
                        // Optional: Custom rendering for clusters
                        clusterContent = {
                            it.items.firstOrNull()?.symbol?.let { symbol ->
                                Box {
                                    WeatherIcons.resolve(LocalContext.current, symbol)?.resId?.let { res ->
                                        Image(
                                            painter = painterResource(id = res),
                                            contentDescription = "Map Link to location",
                                            modifier = Modifier
                                                .wrapContentSize()
                                        )
                                    }
                                }
                            }
                        },
                        // Optional: Custom rendering for non-clustered items
                        clusterItemContent = {
                            Box {
                                WeatherIcons.resolve(LocalContext.current, it.symbol)?.resId?.let { res ->
                                    Image(
                                        painter = painterResource(id = res),
                                        contentDescription = "Map Link to location",
                                        modifier = Modifier
                                            .wrapContentSize()
                                    )
                                }
                            }
                        },
                    )
                }
            }

        }

        else -> {}
    }
}


@Preview
@Composable
fun MapScreenPreview() {
    MapScreen(
        mapState = MapScreenViewModel.MapState(
            pos = Point(
                lat = 59.326038,
                lon = 17.8172507
            )
        )
    )
}
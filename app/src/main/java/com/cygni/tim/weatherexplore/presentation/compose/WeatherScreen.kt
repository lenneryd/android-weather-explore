package com.cygni.tim.weatherexplore.presentation.compose

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.cygni.tim.weatherexplore.data.models.Point
import com.cygni.tim.weatherexplore.data.repo.truncLatitude
import com.cygni.tim.weatherexplore.data.repo.truncLongitude
import com.cygni.tim.weatherexplore.presentation.colors.AppYuTheme
import com.cygni.tim.weatherexplore.presentation.viewmodel.WeatherViewModel

@Composable
fun WeatherScreen(viewModel: WeatherViewModel) {
    val uiState by viewModel.uiState.collectAsState(WeatherViewModel.WeatherUIState.PendingUIState)
    WeatherScreenComposable(state = uiState)
}

@Composable
fun WeatherScreenComposable(state: WeatherViewModel.WeatherUIState) {
    when (state) {
        is WeatherViewModel.WeatherUIState.WeatherUI -> WeatherUIComposable(state)
        is WeatherViewModel.WeatherUIState.FailureUIState -> FailureComposable(state.message)
        WeatherViewModel.WeatherUIState.PendingUIState -> PendingComposable()
    }
}

@Composable
fun WeatherUIComposable(state: WeatherViewModel.WeatherUIState.WeatherUI) {

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
    ) {

        val (lat, lon, updatedAt) = createRefs()

        Row(modifier = Modifier.constrainAs(lat) {
            top.linkTo(parent.top)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }.padding(top = 16.dp, bottom = 8.dp)) {
            Text(
                text = "Latitude: ${state.location.truncLatitude()}",
                fontSize = 18.sp,
                color = MaterialTheme.colorScheme.primary
            )
        }
        Row(modifier = Modifier.constrainAs(lon) {
            top.linkTo(lat.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }) {
            Text(
                text = "Longitude: ${state.location.truncLongitude()}",
                fontSize = 18.sp,
                color = MaterialTheme.colorScheme.primary
            )
        }
        Row(modifier = Modifier.constrainAs(updatedAt) {
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            bottom.linkTo(parent.bottom)
            height = Dimension.wrapContent
            width = Dimension.wrapContent
        }.padding(vertical = 8.dp)) {
            Text(
                text = "Updated at: ${state.updatedAt} (${state.forecastAge} ago)",
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@Composable
fun PendingComposable() {
    CircularProgressIndicator()
}

@Composable
fun FailureComposable(message: String) {
    Text(message)
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

private fun weatherPreviewState() = WeatherViewModel.WeatherUIState.WeatherUI(
    Point(
        lat = 17.8172507,
        lon = 59.326038
    ),
    updatedAt = "09:41",
    forecastAge = "14 minutes ago"
)

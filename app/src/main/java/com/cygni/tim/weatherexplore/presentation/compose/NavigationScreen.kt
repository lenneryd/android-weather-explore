package com.cygni.tim.weatherexplore.presentation.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cygni.tim.weatherexplore.R
import com.cygni.tim.weatherexplore.presentation.viewmodel.WeatherViewModel

@Composable
fun NavigationScreen(
    onClock: () -> Unit = {},
    onWeather: (WeatherViewModel.DisplayType) -> Unit = {},
    onComposeYu: () -> Unit = {}
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painterResource(id = R.drawable.image),
            contentDescription = "Main navigation image",
            tint = MaterialTheme.colorScheme.tertiary,
            modifier = Modifier
                .padding(top = 64.dp)
                .size(128.dp)
        )
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Navigation",
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.primary,
            )
            Button(onClick = { onClock() }, modifier = Modifier.padding(top = 20.dp)) {
                Text(
                    text = "Navigate to Clock",
                )
            }

            Button(onClick = { onWeather(WeatherViewModel.DisplayType.Blocks) }, modifier = Modifier.padding(top = 8.dp)) {
                Text(
                    text = "Navigate to Weather Blocks",
                    modifier = Modifier.padding(top = 8.dp)
                )
            }

            Button(onClick = { onWeather(WeatherViewModel.DisplayType.Timeline) }, modifier = Modifier.padding(top = 8.dp)) {
                Text(
                    text = "Navigate to Weather Timeline",
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
        }
    }
}

@Preview
@Composable
fun NavigationScreenPreview() {
    NavigationScreen()
}
package com.cygni.tim.weatherexplore.presentation.compose

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cygni.tim.weatherexplore.R
import com.cygni.tim.weatherexplore.presentation.icons.WeatherIcons
import com.cygni.tim.weatherexplore.presentation.viewmodel.WeatherViewModel

@Composable
fun WeatherTimelineScreen(state: WeatherViewModel.WeatherUIState.WeatherTimelineUI) {
    Scaffold(
        bottomBar = { WeatherBottomAppBar(state.updatedAtString) }
    ) { padding ->
        WeatherTimelineComposable(
            state = state, modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun WeatherTimelineComposable(state: WeatherViewModel.WeatherUIState.WeatherTimelineUI, modifier: Modifier) {
    LazyColumn(modifier) {
        items(items = state.list, key = {item -> item.key}) { item ->
            when (item) {
                is WeatherViewModel.WeatherTimelineItem.WeatherDayDivider -> {
                    Row(modifier = Modifier.animateItemPlacement()) {
                        WeatherTimelineDividerItem(state = item)
                    }
                }

                is WeatherViewModel.WeatherTimelineItem.HourDivider -> {
                    Row(modifier = Modifier.animateItemPlacement()) {
                        Divider(color = MaterialTheme.colorScheme.primary)
                    }
                }

                is WeatherViewModel.WeatherTimelineItem.WeatherHourlyTimelineItem -> {
                    Row(modifier = Modifier.animateItemPlacement()) {
                        WeatherTimelineHourItem(state = item)
                    }
                }
            }
        }
    }
}

@Composable
fun WeatherTimelineDividerItem(state: WeatherViewModel.WeatherTimelineItem.WeatherDayDivider) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(MaterialTheme.colorScheme.primaryContainer)
            .padding(vertical = 12.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(text = state.text, fontSize = 16.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
    }
}

@Composable
fun WeatherTimelineHourItem(state: WeatherViewModel.WeatherTimelineItem.WeatherHourlyTimelineItem) {
    val icon = state.weatherIcon?.let { WeatherIcons.resolve(LocalContext.current, it) }

    Column() {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Max)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(0.7f)
            ) {
                Row(
                    modifier = Modifier
                        .width(64.dp)
                        .padding(top = 8.dp),
                    horizontalArrangement = Arrangement.Absolute.SpaceBetween
                ) {
                    Text(
                        text = state.hourString,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1.0f)
            ) {
                icon?.let { icon ->
                    Image(
                        painter = painterResource(id = icon.resId),
                        contentDescription = icon.english,
                        modifier = Modifier
                            .size(32.dp)
                            .padding(top = 8.dp)
                    )
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(0.7f)
            ) {
                Text(
                    text = state.airTemp,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(2.0f)
            ) {
                Row() {
                    Text(
                        text = state.windDirectionStr,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.padding(top = 8.dp, start = 8.dp, end = 8.dp)
                    )

                    Box(modifier = Modifier.padding(top = 8.dp)) {
                        Icon(
                            painter = painterResource(id = R.drawable.arrow_projectile),
                            contentDescription = "Wind arrow",
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier
                                .size(24.dp)
                                .rotate(-45f + 180f + state.windDirection.toFloat())
                        )
                    }

                    Text(
                        text = state.windStrength,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.padding(top = 8.dp, start = 8.dp, end = 8.dp)
                    )
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(0.7f),
                horizontalAlignment = Alignment.End
            ) {
                Text(
                    text = state.precipitation,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(top = 8.dp, end = 8.dp)
                )
            }
        }
        Row() {
            Text(
                text = icon?.english.orEmpty(),
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(start = 8.dp, bottom = 4.dp)
            )
        }
    }
}

@Preview
@Composable
fun WeatherTimelineComposablePreview() {
    WeatherTimelineScreen(
        state = WeatherViewModel.WeatherUIState.WeatherTimelineUI(
            updatedAtString = "Updated at 09:41 (14 minutes ago)",
            list = listOf(
                WeatherViewModel.WeatherTimelineItem.WeatherDayDivider("Thursday"),
                WeatherViewModel.WeatherTimelineItem.WeatherHourlyTimelineItem(
                    time = "11",
                    hourString = "11",
                    weatherIcon = "partlycloudy_day",
                    airTemp = "-5",
                    windDirection = 28.0,
                    windDirectionStr = "S",
                    windStrength = "8 (14)",
                    precipitation = "0"
                ),
                WeatherViewModel.WeatherTimelineItem.WeatherHourlyTimelineItem(
                    time = "12",
                    hourString = "12",
                    weatherIcon = "fair_day",
                    airTemp = "-5",
                    windDirection = 70.0,
                    windDirectionStr = "S",
                    windStrength = "3 (6)",
                    precipitation = "0"
                ),
                WeatherViewModel.WeatherTimelineItem.WeatherDayDivider("Thursday"),
                WeatherViewModel.WeatherTimelineItem.WeatherHourlyTimelineItem(
                    time = "11",
                    hourString = "11",
                    weatherIcon = "partlycloudy_day",
                    airTemp = "-5",
                    windDirection = 28.0,
                    windDirectionStr = "S",
                    windStrength = "8 (14)",
                    precipitation = "0"
                ),
                WeatherViewModel.WeatherTimelineItem.WeatherHourlyTimelineItem(
                    time = "12",
                    hourString = "12",
                    weatherIcon = "fair_day",
                    airTemp = "-5",
                    windDirection = 70.0,
                    windDirectionStr = "S",
                    windStrength = "3 (6)",
                    precipitation = "0"
                ),
                WeatherViewModel.WeatherTimelineItem.WeatherHourlyTimelineItem(
                    time = "13",
                    hourString = "13",
                    weatherIcon = "heavyrainshowers_day",
                    airTemp = "-5",
                    windDirection = 128.0,
                    windDirectionStr = "S",
                    windStrength = "2 (9)",
                    precipitation = "0.5"
                )
            )
        )
    )
}

package com.cygni.tim.weatherexplore.presentation.compose

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.layout
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.cygni.tim.weatherexplore.R
import com.cygni.tim.weatherexplore.data.models.Point
import com.cygni.tim.weatherexplore.presentation.icons.WeatherIcons
import com.cygni.tim.weatherexplore.presentation.viewmodel.WeatherViewModel

@Composable
fun tempWithWeatherIcon(
    state: WeatherViewModel.WeatherBlock.TempWithSymbolIcon,
    onClick: () -> Unit = {}
) {
    LocalGridSize.current.let { grid ->
        ElevatedBlock(onClick = onClick, grid.height, grid.width, testTag = state.tag, iconContent = {
            AnimatedContent(targetState = state.weatherIcon, label = "Weather Icon") { icon ->
                WeatherIcons.resolve(LocalContext.current, icon)?.resId?.let { res ->
                    Image(
                        painter = painterResource(id = res),
                        contentDescription = "Map Link to location",
                        modifier = Modifier
                            .wrapContentSize()
                    )
                }
            }
        }) {
            Text(
                text = state.currentTemp,
                color = MaterialTheme.colorScheme.primary,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.wrapContentSize()
            )
        }
    }
}

@Composable
fun GoToMapItem(
    state: WeatherViewModel.WeatherBlock.MapLink,
    text: String,
    onClick: () -> Unit = {}
) {
    LocalGridSize.current.let { grid ->
        ElevatedBlock(
            onClick = onClick,
            gridHeight = grid.height,
            gridWidth = grid.width,
            testTag = state.tag,
            iconContent = {
                Icon(
                    painter = painterResource(id = R.drawable.map),
                    contentDescription = "Map Link to location",
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier
                        .padding(8.dp)
                        .size(48.dp)
                )
            }
        ) {
            Text(
                text = text,
                color = MaterialTheme.colorScheme.primary,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.wrapContentSize()
            )
        }
    }
}

@Composable
fun windDirectionWithStrength(
    state: WeatherViewModel.WeatherBlock.WindWithStrength,
    onClick: () -> Unit = {}
) {
    LocalGridSize.current.let { grid ->
        ElevatedBlock(onClick = onClick, gridHeight = grid.height, gridWidth = grid.width, testTag = state.tag, iconContent = {
            Icon(
                painter = painterResource(id = R.drawable.arrow_projectile),
                contentDescription = "Wind direction",
                modifier = Modifier
                    .size(64.dp)
                    .rotate(-45f + 180f + state.degrees)
                    .padding(12.dp),
                tint = MaterialTheme.colorScheme.primary

            )
        }) {
            Text(
                text = "${state.strength} (${state.direction})",
                color = MaterialTheme.colorScheme.primary,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.wrapContentSize()
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun cloudCoverItem(
    state: WeatherViewModel.WeatherBlock.CloudCoverage,
    onClick: () -> Unit = {}
) {
    LocalGridSize.current.let { grid ->
        ElevatedBlock(onClick = onClick, gridHeight = grid.height, gridWidth = grid.width, testTag = state.tag, iconContent = {
            Box(
                modifier = Modifier
                    .wrapContentSize()
                    .padding(8.dp), contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    progress = (state.percent / 100.0).toFloat(),
                    color = MaterialTheme.colorScheme.tertiary,
                    strokeWidth = 3.dp,
                    strokeCap = StrokeCap.Round,
                    trackColor = MaterialTheme.colorScheme.onTertiary,
                    modifier = Modifier
                        .size(60.dp)
                )

                Icon(
                    painter = painterResource(id = R.drawable.cloud_percent),
                    contentDescription = "Cloud direction",
                    modifier = Modifier
                        .size(60.dp)
                        .padding(8.dp),
                    tint = MaterialTheme.colorScheme.primary
                )
            }

        }) {
            Text(
                text = state.percentText,
                color = MaterialTheme.colorScheme.primary,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.wrapContentSize()
            )
        }
    }
}

@Composable
fun precipitationPotential(
    state: WeatherViewModel.WeatherBlock.PrecipitationPotential,
    onClick: () -> Unit = {}
) {
    LocalGridSize.current.let { grid ->
        ElevatedBlock(onClick = onClick, gridHeight = grid.height, gridWidth = grid.width, testTag = state.tag, iconContent = {
            Box(
                modifier = Modifier
                    .wrapContentSize()
                    .padding(8.dp), contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    progress = (state.percent / 100.0f).toFloat(),
                    color = MaterialTheme.colorScheme.tertiary,
                    strokeWidth = 3.dp,
                    strokeCap = StrokeCap.Round,
                    trackColor = MaterialTheme.colorScheme.onTertiary,
                    modifier = Modifier
                        .size(60.dp)
                )

                Icon(
                    painter = painterResource(id = R.drawable.water_percent),
                    contentDescription = "Cloud direction",
                    modifier = Modifier
                        .size(60.dp)
                        .padding(8.dp),
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        }) {
            Text(
                text = state.percentText,
                color = MaterialTheme.colorScheme.primary,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.wrapContentSize()
            )
        }
    }
}

@Composable
fun precipitationAmountSingle(
    state: WeatherViewModel.PrecipitationData,
) {

    LocalGridSize.current.let { grid ->
        ConstraintLayout(
            modifier = Modifier
                .width(grid.width)
                .height(grid.height)
        ) {

            val (clock, hour, content) = createRefs()

            Icon(
                painter = painterResource(id = R.drawable.progress_clock),
                contentDescription = "Hours Icon",
                modifier = Modifier
                    .constrainAs(clock) {
                        top.linkTo(parent.top, margin = 4.dp)
                        start.linkTo(parent.start)
                    }
                    .size(30.dp),
                tint = MaterialTheme.colorScheme.tertiary
            )

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .constrainAs(hour) {
                        top.linkTo(clock.top, margin = (-4).dp)
                        start.linkTo(clock.end, margin = (-10).dp)
                    }
                    .size(21.dp)
                    .background(MaterialTheme.colorScheme.tertiary, shape = CircleShape)

            ) {
                Text(
                    text = state.hours,
                    fontSize = 12.sp,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.onTertiary
                )
            }

            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .constrainAs(content) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        bottom.linkTo(parent.bottom)
                        end.linkTo(parent.end)
                    }
                    .height(grid.height)
                    .width(grid.width)

            ) {
                Box(
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(8.dp), contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(id = state.type.symbol),
                        contentDescription = "Precipitation Amount",
                        modifier = Modifier
                            .size(60.dp)
                            .padding(12.dp),
                        tint = MaterialTheme.colorScheme.primary
                    )

                    if (state.probability != null) {
                        CircularProgressIndicator(
                            progress = (state.probability / 100.0f).toFloat(),
                            color = MaterialTheme.colorScheme.tertiary,
                            strokeWidth = 2.dp,
                            strokeCap = StrokeCap.Round,
                            trackColor = MaterialTheme.colorScheme.onTertiary,
                            modifier = Modifier
                                .size(50.dp)
                        )
                    }
                }

                if (state.amount != null) {
                    Text(
                        text = state.amount,
                        color = MaterialTheme.colorScheme.primary,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.wrapContentSize()
                    )
                } else {
                    state.probabilityText?.let {
                        Text(
                            text = state.probabilityText,
                            color = MaterialTheme.colorScheme.primary,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.wrapContentSize()
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun precipitationAmount(
    state: WeatherViewModel.WeatherBlock.PrecipitationAmount,
    onClick: () -> Unit = {}
) {
    LocalGridSize.current.let { grid ->
        ElevatedCard(
            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
            onClick = onClick,
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier
                    .height(grid.height)
                    .width(grid.width * 3)
                    .background(color = MaterialTheme.colorScheme.primaryContainer, shape = RoundedCornerShape(8.dp))
                    .padding(4.dp)
            ) {
                state.hours1?.let {
                    precipitationAmountSingle(state = it)
                }

                state.hours6?.let {
                    precipitationAmountSingle(state = it)
                }

                state.hours12?.let {
                    precipitationAmountSingle(state = it)
                }
            }
        }
    }
}

@Composable
fun floatingVerticalSlider(
    slider: WeatherViewModel.SliderData,
    onUpdateSelectedTime: (Float) -> Unit = {},
    modifier: Modifier = Modifier
) {
    var sliderPosition by remember { mutableFloatStateOf(slider.currentStep.toFloat()) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.background(MaterialTheme.colorScheme.tertiaryContainer, RoundedCornerShape(24.dp))
    ) {
        Slider(
            value = sliderPosition,
            valueRange = slider.getRange(),
            onValueChange = {
                sliderPosition = it
                onUpdateSelectedTime(sliderPosition)
            },
            onValueChangeFinished = {
                onUpdateSelectedTime(sliderPosition)
            },
            colors = SliderDefaults.colors(
                activeTrackColor = MaterialTheme.colorScheme.tertiary,
                inactiveTrackColor = MaterialTheme.colorScheme.onTertiary,
                thumbColor = MaterialTheme.colorScheme.tertiary
            ),
            modifier = Modifier
                .graphicsLayer {
                    rotationZ = 270f
                    transformOrigin = TransformOrigin(0.0f, 0.0f)
                }
                .layout { measurable, constraints ->
                    val placeable = measurable.measure(
                        Constraints(
                            minWidth = constraints.minHeight,
                            maxWidth = constraints.maxHeight,
                            minHeight = constraints.minWidth,
                            maxHeight = constraints.maxWidth,
                        )
                    )
                    layout(placeable.height, placeable.width) {
                        placeable.place(-placeable.width, 0)
                    }
                }
                .padding(horizontal = 16.dp)
                .width(180.dp)
                .height(50.dp)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ElevatedBlock(
    onClick: () -> Unit,
    gridHeight: Dp,
    gridWidth: Dp,
    testTag: WeatherViewModel.WeatherBlock.Type,
    iconContent: @Composable () -> Unit,
    textContent: @Composable () -> Unit
) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        onClick = onClick,
        modifier = Modifier.testTag(testTag.type)
    ) {
        Box(
            modifier = Modifier
                .height(gridHeight)
                .width(gridWidth)
                .background(color = MaterialTheme.colorScheme.primaryContainer, shape = RoundedCornerShape(8.dp))
                .padding(4.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()
            ) {
                iconContent()
                textContent()
            }
        }
    }
}

@Preview
@Composable
fun floatingVerticalSliderPreview() {
    floatingVerticalSlider(
        WeatherViewModel.SliderData(
            25,
            15
        )
    )
}

@Preview
@Composable
fun tempWithWeatherIconPreview() {
    tempWithWeatherIcon(state = (WeatherViewModel.WeatherBlock.TempWithSymbolIcon("partlycloudy_day", "-14.3")))
}

@Preview
@Composable
fun windDirectionWithStrengthPreview() {
    windDirectionWithStrength(state = (WeatherViewModel.WeatherBlock.WindWithStrength(291.0f, "SW", "3.6 m/s")))
}

@Preview
@Composable
fun cloudCoverageItemPreview() {
    cloudCoverItem(state = (WeatherViewModel.WeatherBlock.CloudCoverage(69.0, "69%")))
}

@Preview
@Composable
fun precipitationPotentialPreview() {
    precipitationPotential(state = (WeatherViewModel.WeatherBlock.PrecipitationPotential(45.0, "45%")))
}

@Preview
@Composable
fun precipitationAmountSinglePreview() {
    precipitationAmountSingle(
        state = WeatherViewModel.PrecipitationData(
            WeatherViewModel.PrecipitationType.Rain,
            "1",
            "2mm",
            85.0,
            "85%"
        )
    )
}

@Preview
@Composable
fun precipitationAmountPreview() {
    precipitationAmount(
        state = WeatherViewModel.WeatherBlock.PrecipitationAmount(
            WeatherViewModel.PrecipitationData(WeatherViewModel.PrecipitationType.Rain, "1", "0.5mm", 100.0, "100%"),
            WeatherViewModel.PrecipitationData(WeatherViewModel.PrecipitationType.Snow, "6", "2.5mm", 60.0, "60%"),
            WeatherViewModel.PrecipitationData(WeatherViewModel.PrecipitationType.Sleet, "12", null, 42.0, "42%"),
        )
    )
}

@Preview
@Composable
fun goToMapPreview() {
    GoToMapItem(
        WeatherViewModel.WeatherBlock.MapLink.GoToMap(
            Point(
                lat = 59.326038,
                lon = 17.8172507
            )
        ),
        "Google Maps"
    )
}
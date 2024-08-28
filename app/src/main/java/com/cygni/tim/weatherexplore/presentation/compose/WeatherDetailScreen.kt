package com.cygni.tim.weatherexplore.presentation.compose

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.cygni.tim.weatherexplore.presentation.LocalAnimatedVisibilityScope
import com.cygni.tim.weatherexplore.presentation.LocalSharedElementTransitionScope
import com.cygni.tim.weatherexplore.presentation.icons.WeatherIcons
import com.cygni.tim.weatherexplore.presentation.viewmodel.WeatherDetailsViewModel
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun WeatherDetailScreen(
    state: WeatherDetailsViewModel.WeatherDetails,
) {
    when (state) {
        is WeatherDetailsViewModel.WeatherDetails.WeatherDetailsUI -> WeatherDetailsComposable(state = state)
        is WeatherDetailsViewModel.WeatherDetails.LoadingWeatherDetails -> WeatherDetailsComposable(state = state.dummyData)
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun WeatherDetailsComposable(state: WeatherDetailsViewModel.WeatherDetails.WeatherDetailsUI) {
    Scaffold(
        bottomBar = {
            WeatherBottomAppBar(state.updatedAtString)
        }
    ) { padding ->
        ConstraintLayout(
            modifier = Modifier
                .padding(padding)
                .wrapContentHeight()
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
        ) {
            val topGradientHeight = 160.dp

            val (topGradient, weatherCircle, timeText, leftSideBlock, rightSideBlock, bottomBlock) = createRefs()
            val topHalfGuide = createGuidelineFromTop(topGradientHeight / 2)
            val topGuide = createGuidelineFromTop(topGradientHeight)
            val barrier = createBottomBarrier(rightSideBlock, leftSideBlock)

            val weatherData = WeatherIcons.resolve(LocalContext.current, state.icon)
            Box(
                modifier = Modifier
                    .background(
                        brush = Brush.verticalGradient(
                            listOf(MaterialTheme.colorScheme.primary, MaterialTheme.colorScheme.primaryContainer)
                        )
                    )
                    .constrainAs(topGradient) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        height = Dimension.value(topGradientHeight)
                        width = Dimension.fillToConstraints
                    }
            )

            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(top = 8.dp, bottom = 8.dp)
                    .constrainAs(timeText) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
            ) {
                Text(
                    text = DateTimeFormatter.ofPattern(state.selectedTimeFormat)
                        .format(ZonedDateTime.parse(state.time).toLocalDateTime()),
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.onPrimary,
                    textAlign = TextAlign.Center
                )
            }

            val shared = LocalSharedElementTransitionScope.current
            val animation = LocalAnimatedVisibilityScope.current

            Box(modifier = Modifier.constrainAs(weatherCircle) {
                top.linkTo(topHalfGuide)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                height = Dimension.wrapContent
            }) {
                weatherData?.resId?.let { res ->
                    Image(
                        painter = painterResource(id = res),
                        contentDescription = "Weather Icon",
                        modifier = if (shared != null && animation != null) {
                            with(shared) {
                                return@with Modifier.sharedElement(rememberSharedContentState(key = KEY_ICON_IMAGE), animation, boundsTransform = { initialRect, targetRect ->
                                    spring(
                                        dampingRatio = 0.6f,
                                        stiffness = 200f
                                    )
                                })
                            }
                        } else {
                            Modifier
                        }
                            .size(topGradientHeight)
                            .background(
                                brush = Brush.sweepGradient(
                                    listOf(
                                        MaterialTheme.colorScheme.primary,
                                        MaterialTheme.colorScheme.primaryContainer,
                                        MaterialTheme.colorScheme.primary
                                    )
                                ), shape = CircleShape
                            )
                            .padding(36.dp)
                    )
                }
            }

            Column(
                modifier = Modifier
                    .constrainAs(leftSideBlock) {
                        top.linkTo(topGuide)
                        start.linkTo(parent.start)
                        end.linkTo(weatherCircle.start)
                        bottom.linkTo(weatherCircle.bottom)
                        height = Dimension.fillToConstraints
                        width = Dimension.fillToConstraints
                    }
                    .padding(all = 8.dp)
            ) {
                Text(
                    text = "${state.temperature}°",
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.wrapContentSize()
                )
            }

            Column(
                modifier = Modifier
                    .constrainAs(rightSideBlock) {
                        top.linkTo(topGuide)
                        start.linkTo(weatherCircle.end)
                        end.linkTo(parent.end)
                        bottom.linkTo(weatherCircle.bottom)
                        height = Dimension.fillToConstraints
                        width = Dimension.fillToConstraints
                    }
                    .padding(all = 8.dp)
            ) {
                Text(
                    text = weatherData?.english ?: "",
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.wrapContentSize()
                )
            }

            Column(
                modifier = Modifier
                    .constrainAs(bottomBlock) {
                        top.linkTo(barrier)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom)
                        height = Dimension.wrapContent
                        width = Dimension.fillToConstraints

                    }
                    .padding(start = 8.dp, top = 16.dp, end = 8.dp, bottom = 24.dp)
            ) {
                Text(
                    text = loremIpsum(),
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.wrapContentSize()
                )
            }
        }
    }
}


@OptIn(ExperimentalSharedTransitionApi::class)
@Preview
@Composable
fun WeatherDetailScreenPreview() {
    WeatherDetailScreen(
        WeatherDetailsViewModel.WeatherDetails.WeatherDetailsUI(
            updatedAtString = "Updated at 09:41 (14 minutes ago)",
            icon = "partlycloudy_day",
            temperature = "-14.3",
            selectedTimeFormat = "cccc HH:mm",
            time = "2024-01-10T13:00:00Z",
        ),
    )
}

private fun loremIpsum(): String {
    return """
        Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque dapibus eu ligula nec dignissim. Maecenas et mollis sapien. Pellentesque accumsan turpis magna, nec dapibus neque fermentum non. Proin pellentesque purus elit, quis rhoncus mi mattis id. In hac habitasse platea dictumst. Sed ut ullamcorper arcu. Duis fringilla blandit sapien quis fringilla. Cras finibus commodo elit eu facilisis. Nullam bibendum massa in metus accumsan, in sodales massa rutrum. Suspendisse potenti.
        
        Quisque non fermentum orci. Duis tristique quam eu orci scelerisque, quis placerat diam accumsan. Sed quis cursus massa. Duis sollicitudin porttitor odio, in pretium est hendrerit vel. Duis tincidunt velit at ultricies laoreet. Phasellus nisi purus, porttitor ac erat ac, hendrerit consequat dolor. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum imperdiet eros sapien, id dapibus risus pretium ac.
    """.trimIndent()
}

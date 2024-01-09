package com.cygni.tim.weatherexplore.presentation.compose.icon

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.cygni.tim.weatherexplore.R

/**
 * Source from here: https://stackoverflow.com/questions/75725308/how-to-create-vectordrawable-with-gradient-and-multiple-colors-with-jetpack-comp
 */

@Composable
fun ClipIconSample() {
    val vectorRes2: Painter = painterResource(id = R.drawable.alert_circle_outline)
    Icon(
        vectorRes2,
        modifier = Modifier
            .drawWithContent {
                val height = size.height

                with(drawContext.canvas.nativeCanvas) {
                    val checkPoint = saveLayer(null, null)

                    // Destination
                    drawContent()

                    // Source
                    drawRect(
                        Color(0xffEC407A),
                        topLeft = Offset(0f, height / 2),
                        size = Size(size.width, size.height / 2),
                        blendMode = BlendMode.SrcIn

                    )

                    restoreToCount(checkPoint)

                }
            }
            .size(100.dp),
        contentDescription = null
    )
}

@Composable
fun GradientClipIconSample() {
    val vectorRes2: Painter = painterResource(id = R.drawable.alert_circle_outline)
    Icon(
        vectorRes2,
        modifier = Modifier
            .drawWithContent {
                with(drawContext.canvas.nativeCanvas) {
                    val checkPoint = saveLayer(null, null)

                    // Destination
                    drawContent()

                    // Source
                    drawRect(
                        brush = Brush.sweepGradient(
                            colors = listOf(
                                Color.Green,
                                Color.Cyan,
                                Color.Red,
                                Color.Blue,
                                Color.Yellow,
                                Color.Magenta,
                            )
                        ),
                        blendMode = BlendMode.SrcIn
                    )

                    restoreToCount(checkPoint)

                }
            }
            .size(100.dp),
        contentDescription = null
    )
}

@Composable
fun FillIconSample() {
    val vectorRes2: Painter = painterResource(id = R.drawable.alert_circle_outline)
    var targetValue by remember { mutableFloatStateOf(0f) }
    val progress by animateFloatAsState(
        targetValue = targetValue,
        animationSpec = tween(1000),
        label = "Animated progress"
    )
    Icon(
        vectorRes2,
        modifier = Modifier
            .clickable(
                interactionSource = MutableInteractionSource(),
                indication = null,
            ) {
                targetValue = if (targetValue == 0f) {
                    1f
                } else {
                    0f
                }
            }
            .drawWithContent {
                val height = size.height * progress

                with(drawContext.canvas.nativeCanvas) {
                    val checkPoint = saveLayer(null, null)

                    val totalHeight = size.height
                    val filledHeight = totalHeight * progress

                    // Destination
                    drawContent()

                    // Source
                    drawRect(
                        Color(0xffEC407A),
                        topLeft = Offset(0f, totalHeight - height),
                        size = Size(size.width, height),
                        blendMode = BlendMode.SrcIn

                    )

                    restoreToCount(checkPoint)

                }
            }
            .size(100.dp),
        contentDescription = null
    )
}

@Composable
fun ShimmerIcon(painter: Painter, colors: List<Color>, modifier: Modifier) {
    val transition = rememberInfiniteTransition(label = "Shimmering alert transition")
    val progress by transition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(3500, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ), label = "Shimmering progress animation"
    )

    Icon(
        painter,
        modifier = modifier
            .drawWithContent {
                with(drawContext.canvas.nativeCanvas) {
                    val checkPoint = saveLayer(null, null)
                    val canvasWidth = size.width

                    val brush = Brush.horizontalGradient(
                        colors = colors,
                        startX = canvasWidth * progress,
                        endX = canvasWidth * progress * 2f
                    )

                    // Destination
                    drawContent()

                    // Source
                    drawRect(
                        brush = brush,
                        blendMode = BlendMode.SrcIn
                    )

                    restoreToCount(checkPoint)

                }
            },
        contentDescription = null
    )
}
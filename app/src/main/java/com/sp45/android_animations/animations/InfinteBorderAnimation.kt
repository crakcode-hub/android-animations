package com.sp45.android_animations.animations

import androidx.compose.animation.core.EaseInOutCirc
import androidx.compose.animation.core.Easing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun InfiniteBorderAnimation() {
    Row(
        modifier = Modifier.fillMaxSize().background(Color.LightGray),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        Box(
            modifier = Modifier
                .size(100.dp)
                .animatedBorder(
                    borderColors = listOf(
                        Color.Black,
                        Color.Green,
                        Color.White
                    ),
                    backgroundColor = Color.LightGray,
                    shape = RoundedCornerShape(100),
                    borderWidth = 4.dp,
                    animationDurationInMillis = 1750,
                    laps = 3,
                    easing = EaseInOutCirc
                )
        ) {
            // Some content here
        }
        Box(
            modifier = Modifier
                .size(100.dp)
                .animatedBorder(
                    borderColors = listOf(
                        Color.Black,
                        Color.Green,
                        Color.White
                    ),
                    backgroundColor = Color.LightGray,
                    shape = RoundedCornerShape(100),
                    borderWidth = 4.dp,
                    animationDurationInMillis = 1750
                )
        ) {
            // Some content here
        }
    }
}

fun Modifier.animatedBorder(
    borderColors: List<Color>,
    backgroundColor: Color,
    shape: Shape = CircleShape,
    borderWidth: Dp = 1.dp,
    animationDurationInMillis: Int = 1000,
    laps: Int = 1,
    easing: Easing = LinearEasing
): Modifier = composed {
    val brush = Brush.sweepGradient(borderColors)
    val infiniteTransition = rememberInfiniteTransition(label = "animatedBorder")
    val angle by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f * laps,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = animationDurationInMillis, easing = easing),
            repeatMode = RepeatMode.Restart
        ),
        label = "angleAnimation"
    )

    this.clip(shape)
        .padding(borderWidth)
        .drawWithContent {
            rotate(angle) {
                drawCircle(
                    brush = brush,
                    radius = size.width,
                    blendMode = BlendMode.SrcIn
                )
            }
            drawContent()
        }
        .background(color = backgroundColor, shape = shape)
}

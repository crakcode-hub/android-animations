package com.sp45.android_animations.animations

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun createOrbitAnimation(speedMillis: Int, infiniteTransition: InfiniteTransition): Float {
    return infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(speedMillis, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ), label = ""
    ).value
}

@Composable
fun OrbitingObjects() {
    val infiniteTransition = rememberInfiniteTransition(label = "")

    val orbits = listOf(
        Triple(100f, 4000, Color.Red),
        Triple(150f, 6000, Color.Blue),
        Triple(200f, 8000, Color.Green),
        Triple(250f, 10000, Color.Yellow)
    )

    val animatedAngles = orbits.map { (_, speed, _) ->
        createOrbitAnimation(speed, infiniteTransition)
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val centerX = size.width / 2
            val centerY = size.height / 2

            orbits.forEachIndexed { index, (radius, _, color) ->
                val angle = animatedAngles[index]

                val radians = Math.toRadians(angle.toDouble())

                val x = centerX + radius * cos(radians).toFloat()
                val y = centerY + radius * sin(radians).toFloat()

                drawCircle(color = color, radius = 10.dp.toPx(), center = androidx.compose.ui.geometry.Offset(x, y))
            }

            drawCircle(color = Color.White, radius = 20.dp.toPx(), center = androidx.compose.ui.geometry.Offset(centerX, centerY))
        }
    }
}

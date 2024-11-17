package com.sp45.android_animations.animations

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ExpandingRings() {
    val infiniteTransition = rememberInfiniteTransition()

    // Helper function
    @Composable
    fun createRingAnimation(delayMillis: Int): Pair<Float, Float> {
        val radius by infiniteTransition.animateFloat(
            initialValue = 0f,
            targetValue = 300f, // Larger radius
            animationSpec = infiniteRepeatable(
                animation = tween(1200, easing = LinearEasing, delayMillis = delayMillis),
                repeatMode = RepeatMode.Restart
            ), label = ""
        )
        val alpha by infiniteTransition.animateFloat(
            initialValue = 1f,
            targetValue = 0f,
            animationSpec = infiniteRepeatable(
                animation = tween(1200, easing = LinearEasing, delayMillis = delayMillis),
                repeatMode = RepeatMode.Restart
            ), label = ""
        )
        return radius to alpha
    }

    //three rings
    val (radius1, alpha1) = createRingAnimation(0)
    val (radius2, alpha2) = createRingAnimation(400)
    val (radius3, alpha3) = createRingAnimation(800)

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier.size(600.dp)) { // Larger size for the canvas
            // Draw three expanding rings
            drawCircle(color = Color.Cyan.copy(alpha = alpha1), radius = radius1)
            drawCircle(color = Color.Magenta.copy(alpha = alpha2), radius = radius2)
            drawCircle(color = Color.Yellow.copy(alpha = alpha3), radius = radius3)
        }
    }
}

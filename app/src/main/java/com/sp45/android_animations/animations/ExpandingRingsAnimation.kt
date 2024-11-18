package com.sp45.android_animations.animations

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.Text

@Composable
fun ExpandingRings() {
    val infiniteTransition = rememberInfiniteTransition(label = "")

    @Composable
    fun createRingAnimation(delayMillis: Int): Pair<Float, Float> {
        val radius by infiniteTransition.animateFloat(
            initialValue = 0f,
            targetValue = 300f,
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

    val (radius1, alpha1) = createRingAnimation(0)
    val (radius2, alpha2) = createRingAnimation(400)
    val (radius3, alpha3) = createRingAnimation(800)

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier.fillMaxSize(0.3f),
            contentAlignment = Alignment.Center
        ) {
            Canvas(modifier = Modifier.size(600.dp)) {
                drawCircle(color = Color.Cyan.copy(alpha = alpha1), radius = radius1)
                drawCircle(color = Color.Magenta.copy(alpha = alpha2), radius = radius2)
                drawCircle(color = Color.Yellow.copy(alpha = alpha3), radius = radius3)
            }
        }
        Text(
            text = "Loading...",
            fontSize = 20.sp,
            color = Color.Gray,
            textAlign = TextAlign.Center
        )
    }
}

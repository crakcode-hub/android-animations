package com.sp45.android_animations.animations

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun BouncingBallAnimation() {
    val ballPosition = remember { androidx.compose.animation.core.Animatable(0f) }
    val ballSize = 50.dp
    val ballColor = MaterialTheme.colorScheme.primary
    MaterialTheme.colorScheme.primary.copy(alpha = 0.3f)
    val ballScale = if (ballPosition.value > 490f) 1.2f else 1f

    LaunchedEffect(Unit) {
        ballPosition.animateTo(
            targetValue = 500f,
            animationSpec = infiniteRepeatable(
                animation = tween(1000, easing = LinearEasing),
                repeatMode = RepeatMode.Reverse
            )
        )
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(100.dp,200.dp),
        contentAlignment = Alignment.Center,
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            drawCircle(
                color = Color.Black.copy(alpha = 0.3f),
                radius = (ballSize.toPx() / 2) * ballScale,
                center = Offset(size.width / 2, ballPosition.value + 8f)
            )
            drawCircle(
                color = ballColor,
                radius = (ballSize.toPx() / 2) * ballScale,
                center = Offset(size.width / 2, ballPosition.value)
            )
        }
    }
}
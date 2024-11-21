package com.sp45.android_animations.animations

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.InfiniteTransition
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.unit.dp

@Composable
fun FloatingElements() {
    val infiniteTransition = rememberInfiniteTransition(label = "")

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        repeat(6) { index ->
            AnimatedElement(
                infiniteTransition = infiniteTransition,
                index = index
            )
        }
    }
}

@Composable
private fun AnimatedElement(
    infiniteTransition: InfiniteTransition,
    index: Int
) {
    val baseDelay = 500 * index

    val scale by infiniteTransition.animateFloat(
        initialValue = 0.8f,
        targetValue = 1.2f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 2000 + (index * 100),
                easing = FastOutSlowInEasing,
                delayMillis = baseDelay
            ),
            repeatMode = RepeatMode.Reverse
        ), label = ""
    )

    val rotation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 3000 + (index * 200),
                easing = LinearEasing,
                delayMillis = baseDelay
            )
        ), label = ""
    )

    val xOffset by infiniteTransition.animateFloat(
        initialValue = -100f,
        targetValue = 100f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 4000 + (index * 300),
                easing = FastOutSlowInEasing,
                delayMillis = baseDelay
            ),
            repeatMode = RepeatMode.Reverse
        ), label = ""
    )

    val yOffset by infiniteTransition.animateFloat(
        initialValue = -50f,
        targetValue = 50f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 3000 + (index * 300),
                easing = FastOutSlowInEasing,
                delayMillis = baseDelay
            ),
            repeatMode = RepeatMode.Reverse
        ), label = ""
    )

    val alpha by infiniteTransition.animateFloat(
        initialValue = 0.4f,
        targetValue = 0.8f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 2000,
                easing = FastOutSlowInEasing,
                delayMillis = baseDelay
            ),
            repeatMode = RepeatMode.Reverse
        ), label = ""
    )

    val elementColors = listOf(
        MaterialTheme.colorScheme.primary,
        MaterialTheme.colorScheme.secondary,
        MaterialTheme.colorScheme.tertiary,
        MaterialTheme.colorScheme.error,
        MaterialTheme.colorScheme.inverseOnSurface,
        MaterialTheme.colorScheme.inversePrimary
    )

    val isSquare = index % 2 == 0
    val size = (40 + (index * 5)).dp

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .offset(x = xOffset.dp, y = yOffset.dp)
                .scale(scale)
                .rotate(rotation)
                .alpha(alpha)
                .size(size)
                .background(
                    elementColors[index % elementColors.size],
                    if (isSquare) RoundedCornerShape(8.dp) else CircleShape
                )
        )
    }
}
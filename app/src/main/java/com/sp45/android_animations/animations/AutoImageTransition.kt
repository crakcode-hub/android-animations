package com.sp45.android_animations.animations

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.sp45.android_animations.R
import kotlinx.coroutines.delay

@Composable
fun AutoImageTransition(
    displayTimeMillis: Long = 1000L,
    fadeDurationMillis: Int = 500
) {
    val images = listOf(
        R.drawable.img_2,
        R.drawable.img,
        R.drawable.ic_launcher_background
    )

    var currentIndex by remember { mutableIntStateOf(0) }
    val fadeAnim = remember { Animatable(0f) }

    LaunchedEffect(currentIndex) {
        fadeAnim.animateTo(1f, animationSpec = tween(fadeDurationMillis))
        delay(displayTimeMillis)
        fadeAnim.animateTo(0f, animationSpec = tween(fadeDurationMillis))

        currentIndex = (currentIndex + 1) % images.size //next img
    }

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(images[currentIndex]),
            contentDescription = "Carousel Image $currentIndex",
            modifier = Modifier
                .size(200.dp)
                .graphicsLayer(alpha = fadeAnim.value),
            contentScale = ContentScale.Crop
        )
    }
}

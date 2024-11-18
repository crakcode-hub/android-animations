package com.sp45.android_animations.animations

import androidx.compose.animation.*
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.sp45.android_animations.R

@Composable
fun ButtonToImage() {
    var isVisible by remember { mutableStateOf(false) }

    val rotation by animateFloatAsState(
        targetValue = if (isVisible) 720f else 0f,
        animationSpec = tween(durationMillis = 1200),
        label = ""
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        contentAlignment = Alignment.Center
    ) {
        AnimatedVisibility(
            visible = !isVisible,
            enter = fadeIn(animationSpec = tween(durationMillis = 1000)),
            exit = fadeOut(animationSpec = tween(durationMillis = 1000))
        ) {
            Button(
                onClick = { isVisible = true },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6200EE)),
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(16.dp)
                    .size(width = 120.dp, height = 50.dp)
            ) {
                Text(text = "Click Me!", color = Color.White)
            }
        }

        AnimatedVisibility(
            visible = isVisible,
            enter = fadeIn(animationSpec = tween(durationMillis = 1200)) +
                    scaleIn(initialScale = 0.5f, animationSpec = tween(durationMillis = 1200)),
            exit = fadeOut(animationSpec = tween(durationMillis = 1200)) +
                    scaleOut(targetScale = 0.5f, animationSpec = tween(durationMillis = 1200))
        ) {
            Image(
                painter = painterResource(id = R.drawable.img_2),
                contentDescription = "CrakCode Image",
                modifier = Modifier
                    .fillMaxSize()
                    .graphicsLayer {
                        rotationZ = rotation
                    }
            )
        }
    }
}

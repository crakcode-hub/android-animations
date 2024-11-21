package com.sp45.android_animations.animations

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
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
            .background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center
    ) {
        AnimatedVisibility(
            visible = !isVisible,
            enter = fadeIn(animationSpec = tween(durationMillis = 1000)),
            exit = fadeOut(animationSpec = tween(durationMillis = 1000))
        ) {
            Button(
                onClick = { isVisible = true },
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(16.dp)
                    .size(width = 120.dp, height = 50.dp)
            ) {
                Text(
                    text = stringResource(R.string.click_me),
                    color = MaterialTheme.colorScheme.onPrimary
                )
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
                contentDescription = stringResource(R.string.crakcode_image),
                modifier = Modifier
                    .fillMaxSize()
                    .graphicsLayer {
                        rotationZ = rotation
                    }
            )
        }
    }
}
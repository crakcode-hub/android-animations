package com.sp45.android_animations.animations

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sp45.android_animations.R

@Composable
fun ExpandingRings() {
    val infiniteTransition = rememberInfiniteTransition(label = "")

    @Composable
    fun CreateRingAnimation(delayMillis: Int): Pair<Float, Float> {
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

    val (radius1, alpha1) = CreateRingAnimation(0)
    val (radius2, alpha2) = CreateRingAnimation(400)
    val (radius3, alpha3) = CreateRingAnimation(800)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
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
            text = stringResource(R.string.loading),
            fontSize = 20.sp,
            color = MaterialTheme.colorScheme.onBackground,
            textAlign = TextAlign.Center
        )
    }
}
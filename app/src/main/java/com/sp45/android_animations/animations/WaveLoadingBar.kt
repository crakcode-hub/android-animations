package com.sp45.android_animations.animations

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sp45.android_animations.R

@Composable
fun WaveLoadingBar() {
    val waveColors = listOf(Color(0xFF4CAF50), Color(0xFF2196F3), Color(0xFFFF5722))
    val waveOffsets = listOf(0f, 0.4f, 0.8f)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .padding(vertical = 24.dp)
        ) {
            waveColors.forEachIndexed { index, color ->
                Wave(
                    waveColor = color,
                    offsetFactor = waveOffsets[index],
                    modifier = Modifier.fillMaxSize()
                )
            }
        }

        Text(
            text = stringResource(R.string.loading),
            fontSize = 20.sp,
            color = Color.White
        )
    }
}

@Composable
fun Wave(waveColor: Color, offsetFactor: Float, modifier: Modifier = Modifier) {
    val infiniteTransition = rememberInfiniteTransition()
    val waveAnim by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(2000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        )
    )

    Canvas(modifier = modifier) {
        val width = size.width
        val height = size.height
        val waveHeight = 20.dp.toPx()

        val wavePoints = List(10) { i ->
            val x = width * i / 9
            val y = height / 2 + waveHeight * kotlin.math.sin((i + waveAnim * 10 + offsetFactor * 10) * 0.5f)
            x to y
        }

        translate(left = -width / 10) {
            drawPath(
                path = androidx.compose.ui.graphics.Path().apply {
                    moveTo(0f, height / 2)
                    wavePoints.forEach { (x, y) ->
                        lineTo(x, y)
                    }
                    lineTo(width, height)
                    lineTo(0f, height)
                    close()
                },
                color = waveColor.copy(alpha = 0.5f)
            )
        }
    }
}

package com.sp45.android_animations.animations

import android.annotation.SuppressLint
import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.Text
import kotlin.random.Random

@SuppressLint("UseOfNonLambdaOffsetOverload")
@Composable
fun TextExplosion() {
    val text = "CrakCode"
    val infiniteTransition = rememberInfiniteTransition(label = "")

    val randomOffsets = remember {
        text.map {
            Pair(
                Random.nextFloat() * 500 - 250,
                Random.nextFloat() * 500 - 250
            )
        }
    }

    val animations = randomOffsets.mapIndexed { _, (targetX, targetY) ->
        val offsetX = infiniteTransition.animateFloat(
            initialValue = 0f,
            targetValue = targetX,
            animationSpec = infiniteRepeatable(
                animation = tween(
                    durationMillis = 3000,
                    easing = LinearEasing
                ),
                repeatMode = RepeatMode.Reverse
            ), label = ""
        )

        val offsetY = infiniteTransition.animateFloat(
            initialValue = 0f,
            targetValue = targetY,
            animationSpec = infiniteRepeatable(
                animation = tween(
                    durationMillis = 3000,
                    easing = LinearEasing
                ),
                repeatMode = RepeatMode.Reverse
            ), label = ""
        )

        Pair(offsetX, offsetY)
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
            text.forEachIndexed { index, char ->
                val (offsetX, offsetY) = animations[index]

                Box(
                    modifier = Modifier
                        .offset(offsetX.value.dp, offsetY.value.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = char.toString(),
                        style = TextStyle(
                            color = Color.Green,
                            fontSize = 32.sp
                        )
                    )
                }
            }
        }
    }
}

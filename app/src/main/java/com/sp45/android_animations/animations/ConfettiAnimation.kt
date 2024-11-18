package com.sp45.android_animations.animations

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.sp45.android_animations.R
import kotlinx.coroutines.launch
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin
import kotlin.random.Random

private data class Confetti(
    var position: Offset,
    val color: Color,
    val size: Float,
    var velocity: Offset,
    var lifetime: Float
)

@Composable
fun BirthdayPopperAnimation() {
    var isPlaying by remember { mutableStateOf(false) }
    var confetti by remember { mutableStateOf(emptyList<Confetti>()) }
    val scope = rememberCoroutineScope()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        contentAlignment = Alignment.Center
    ) {
        Button(
            onClick = {
                if (!isPlaying) {
                    isPlaying = true
                    confetti = generateConfetti(Offset(500f, 800f))
                    scope.launch {
                        kotlinx.coroutines.delay(3000)
                        isPlaying = false
                    }
                }
            },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 50.dp)
        ) {
            Text(stringResource(R.string.pop))
        }

        if (isPlaying) {
            ConfettiAnimation(confetti)
        }
    }
}

@Composable
private fun ConfettiAnimation(initialConfetti: List<Confetti>) {
    val animate = remember { Animatable(0f) }

    LaunchedEffect(Unit) {
        animate.animateTo(
            targetValue = 1f,
            animationSpec = tween(
                durationMillis = 3000,
                easing = LinearEasing
            )
        )
    }

    Canvas(modifier = Modifier.fillMaxSize()) {
        initialConfetti.forEach { confetti ->

            val gravity = 1000f * animate.value * animate.value


            val newPosition = Offset(
                x = confetti.position.x + (confetti.velocity.x * 900f * animate.value),
                y = confetti.position.y + (confetti.velocity.y * 900f * animate.value) + gravity
            )


            val alpha = 2f - animate.value

            drawCircle(
                color = confetti.color.copy(alpha = alpha),
                radius = confetti.size,
                center = newPosition
            )
        }
    }
}

private fun generateConfetti(origin: Offset): List<Confetti> {
    val colors = listOf(
        Color(0xFFFF69B4),
        Color(0xFFFFD700),
        Color(0xFF00FF00),
        Color(0xFF00BFFF),
        Color(0xFFFF1493),
        Color(0xFFFFA500)
    )

    return List(200) {
        val angle = Random.nextDouble(PI * 0.8, PI * 2.2)
        val speed = Random.nextDouble(0.2, 1.0)

        Confetti(
            position = origin,
            color = colors.random(),
            size = Random.nextFloat() * 6f + 2f,
            velocity = Offset(
                x = (cos(angle) * speed).toFloat(),
                y = (sin(angle) * speed).toFloat()
            ),
            lifetime = 1f
        )
    }
}
package com.sp45.android_animations.animations

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.sp45.android_animations.R

@Composable
fun ValueSpringAnimation() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var sliderPosition by remember { mutableFloatStateOf(0f) }
        var roundnessPercent by remember { mutableFloatStateOf(0f) }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Slider(
                value = sliderPosition,
                onValueChange = { sliderPosition = it },
                valueRange = 0f..300f,
                steps = 5
            )
            Text(
                text = stringResource(R.string.dynamic_circle_of_size_dp, sliderPosition.toInt()),
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
            Slider(
                value = roundnessPercent,
                onValueChange = { roundnessPercent = it },
                valueRange = 0f..100f,
                steps = 5
            )
            Text(
                text = stringResource(
                    R.string.dynamic_circle_of_roundness,
                    roundnessPercent.toInt()
                ), color = Color.White, fontWeight = FontWeight.Bold
            )
        }

        val sliderSize by animateDpAsState(
            targetValue = sliderPosition.dp,
            label = "Size animation",
            animationSpec = spring(
                dampingRatio = Spring.DampingRatioHighBouncy,
                stiffness = Spring.StiffnessVeryLow
            )
        )

        val color: Color by animateColorAsState(
            targetValue = when {
                sliderPosition < 100f -> Color.Green
                sliderPosition < 200f -> Color.Yellow
                else -> Color.Red
            },
            label = stringResource(R.string.color_animation)
        )

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            val roundnessDp = (roundnessPercent / 100f) * (sliderSize.value / 2)
            Box(
                modifier = Modifier
                    .size(sliderSize)
                    .clip(RoundedCornerShape(roundnessDp.dp))
                    .background(color),
                contentAlignment = Alignment.Center
            ) {

            }
        }
    }
}
package com.sp45.android_animations.animations

import androidx.compose.animation.core.EaseOutCubic
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ValueTweenAnimation() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var isRound by remember {
            mutableStateOf(false)
        }
        var toggleSize by remember {
            mutableStateOf(false)
        }
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Button(onClick = {
                isRound = !isRound
            }) {
                Text(text = "Toggle roundness")
            }
            Button(onClick = {
                toggleSize = !toggleSize
            }) {
                Text(text = "Toggle size")
            }
            Button(onClick = {
                toggleSize = !toggleSize
                isRound = !isRound
            }) {
                Text(text = "Toggle both")
            }
        }
        val borderRadius by animateIntAsState(
            targetValue = if (isRound) 0 else 50,
            label = "Border radius animation",
            animationSpec = tween(
                1000,
                easing = EaseOutCubic
            )
        )
        val size by animateDpAsState(
            targetValue = if (toggleSize) 400.dp else 200.dp,
            label = "Size animation",
            animationSpec = tween(
                1000,
                easing = EaseOutCubic
            )
        )
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .size(size)
                    .clip(RoundedCornerShape(borderRadius))
                    .background(Color.Red)
            )
        }
    }
}

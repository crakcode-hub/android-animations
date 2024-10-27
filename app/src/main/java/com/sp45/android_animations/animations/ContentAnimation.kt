package com.sp45.android_animations.animations

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun ContentAnimation() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var toggle by remember {
            mutableStateOf(false)
        }
        var isAnimating by remember {
            mutableStateOf(false)
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                enabled = !isAnimating,
                onClick = {
                    toggle = !toggle
                }
            ) {
                Text(text = "Toggle")
            }
        }
        AnimatedContent(
            targetState = toggle,
            modifier = Modifier.fillMaxWidth().weight(1f),
            label = "Content animation",
            transitionSpec = {
                slideInHorizontally {
                    -it
                } togetherWith slideOutHorizontally {
                    it
                }
            }
        ) { targetToggle ->
            isAnimating = transition.currentState != transition.targetState
            if (targetToggle) {
                Box(modifier = Modifier.background(Color.Green))
            } else {
                Box(modifier = Modifier.background(Color.Red))
            }
        }
    }
}

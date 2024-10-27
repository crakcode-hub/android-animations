package com.sp45.android_animations.animations

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ContentCountAnimation() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var count by remember {
            mutableIntStateOf(0)
        }
        var isAnimating by remember {
            mutableStateOf(false)
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = {
                    if (isAnimating) return@Button
                    count += 1
                }
            ) {
                Text(text = "Count up")
            }
            Button(
                onClick = {
                    if (isAnimating) return@Button
                    count -= 1
                }
            ) {
                Text(text = "Count down")
            }
        }
        Spacer(modifier = Modifier.size(128.dp))
        AnimatedContent(
            modifier = Modifier.fillMaxWidth(),
            targetState = count,
            contentAlignment = Alignment.Center,
            label = "Content animation",
            transitionSpec = {
                val towards = if (targetState >= initialState) {
                    AnimatedContentTransitionScope.SlideDirection.Up
                } else {
                    AnimatedContentTransitionScope.SlideDirection.Down
                }
                val animationTime = 800
                (
                    slideIntoContainer(
                        towards = towards,
                        animationSpec = tween(animationTime)
                    ) + fadeIn(
                        animationSpec = tween(animationTime)
                    ) togetherWith slideOutOfContainer(
                        towards = towards,
                        animationSpec = tween(animationTime)
                    ) + fadeOut(
                        animationSpec = tween(animationTime)
                    )
                ) using SizeTransform(false)
            }
        ) { targetCount ->
            isAnimating = transition.targetState != transition.currentState
            Text(
                modifier = Modifier.wrapContentSize(),
                text = "$targetCount",
                fontSize = 96.sp
            )
        }
    }
}

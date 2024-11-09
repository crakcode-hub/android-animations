package com.sp45.android_animations.animations

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp

@Composable
fun FlipCard() {
    var cardFlipped by remember { mutableStateOf(false) }
    val rotation by animateFloatAsState(
        targetValue = if (cardFlipped) 180f else 0f,
        animationSpec = tween(600), label = ""
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Box(
            modifier = Modifier
                .size(200.dp) // Size of the card
                .clickable { cardFlipped = !cardFlipped }
                .graphicsLayer { rotationY = rotation } // Only rotate the front side
                .align(Alignment.Center)
        ) {
            // Card front side
            Card(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.DarkGray)
                    .graphicsLayer { rotationY = if (cardFlipped) 180f else 0f } // Only rotate the front
            ) {
                Box(modifier = Modifier.fillMaxSize()) {
                    if (!cardFlipped) {
//                        Text(
//                            text = "Front",
//                            modifier = Modifier.align(Alignment.Center)
//                        )
                    }
                }
            }
        }
    }
}

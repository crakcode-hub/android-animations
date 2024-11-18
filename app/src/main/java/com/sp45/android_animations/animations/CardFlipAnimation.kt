package com.sp45.android_animations.animations

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sp45.android_animations.R

@Composable
fun CardFlipping() {
    var isFlipped by remember { mutableStateOf(false) }
    var showBackImage by remember { mutableStateOf(false) } // State for image update

    val rotationY by animateFloatAsState(
        targetValue = if (isFlipped) 180f else 0f,
        animationSpec = tween(durationMillis = 1000), label = ""
    )

    LaunchedEffect(rotationY) {
        if (rotationY > 90 && !showBackImage) {
            showBackImage = true
        } else if (rotationY <= 90 && showBackImage) {
            showBackImage = false
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF000000)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(220.dp, 300.dp)
                .graphicsLayer {
                    this.rotationY = rotationY // Use the animateFloatAsState value directly
                }
                .clip(RoundedCornerShape(16.dp))
                .background(
                    Brush.linearGradient(
                        colors = listOf(Color(0xFF2196F3), Color(0xFF00BCD4))
                    )
                )
                .clickable { isFlipped = !isFlipped },
            contentAlignment = Alignment.Center
        ) {
            val imageResource = if (showBackImage) R.drawable.img else R.drawable.img_2
            Image(
                painter = painterResource(id = imageResource),
                contentDescription = if (showBackImage) "Back of the card" else "Front of the card",
                modifier = Modifier
                    .size(180.dp)
                    .clip(RoundedCornerShape(12.dp))
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = if (isFlipped) "Tap to See Front" else "Tap to Flip",
            color = Color(0xFFFFFFFF)
        )
    }
}

@Preview
@Composable
fun RotatingCardPreview() {
    CardFlipping()
}

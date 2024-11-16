package com.sp45.android_animations.animations

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.layout.ContentScale
import com.sp45.android_animations.R

@Composable
fun SlidingDoorAnimation() {
    var widthState by remember { mutableStateOf(0f) } // Track the current width in dp
    val width by animateDpAsState(
        targetValue = widthState.dp,
        animationSpec = tween(durationMillis = 300, easing = FastOutSlowInEasing)
    )

    // Handle swipe gestures
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .pointerInput(Unit) {
                detectHorizontalDragGestures { _, dragAmount ->
                    // Scale the drag amount to make even small drags more noticeable
                    val dragChange = dragAmount * 5f // Adjust scaling factor as needed
                    widthState = (widthState + dragChange).coerceIn(0f, 500f) // Limit the range from 0 to 500dp
                }
            }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .width(width) // Animate the width
                    .height(300.dp)
                    .clip(RoundedCornerShape(16.dp)) // Clip the image with rounded corners
            ) {
                // Image to move horizontally based on the swipe state
                Image(
                    painter = painterResource(id = R.drawable.img_2), // Replace with your image resource
                    contentDescription = "Hidden Content",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop // Ensures the image fills the space
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Instruction Text to inform user to swipe
            Text(
                text = "Swipe left or right to open/close the door",
                modifier = Modifier.align(Alignment.CenterHorizontally),
                color = Color.White
            )
        }
    }
}

@Preview
@Composable
fun PreviewSlidingDoorAnimation() {
    SlidingDoorAnimation()
}

package com.sp45.android_animations.animations

import android.annotation.SuppressLint
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
import androidx.compose.runtime.mutableFloatStateOf
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

@SuppressLint("AutoboxingStateCreation")
@Composable
fun SlidingDoorAnimation() {
    var widthState by remember { mutableFloatStateOf(0f) }
    val width by animateDpAsState(
        targetValue = widthState.dp,
        animationSpec = tween(durationMillis = 300, easing = FastOutSlowInEasing), label = ""
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .pointerInput(Unit) {
                detectHorizontalDragGestures { _, dragAmount ->
                    val dragChange = dragAmount * 5f
                    widthState = (widthState + dragChange).coerceIn(0f, 500f)
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
                    .width(width)
                    .height(300.dp)
                    .clip(RoundedCornerShape(16.dp))
            ) {

                Image(
                    painter = painterResource(id = R.drawable.img_2),
                    contentDescription = "Hidden Content",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Swipe right or left to open/close the door",
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

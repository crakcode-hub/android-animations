package com.sp45.android_animations.animations

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sp45.android_animations.R

@Composable
fun CardFlipping() {
    var isFlipped by remember { mutableStateOf(false) }
    var showBackImage by remember { mutableStateOf(false) }

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
            .background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(220.dp, 300.dp)
                .graphicsLayer {
                    this.rotationY = rotationY
                }
                .clip(RoundedCornerShape(16.dp))
                .background(
                    Brush.linearGradient(
                        colors = listOf(
                            MaterialTheme.colorScheme.primary,
                            MaterialTheme.colorScheme.secondary
                        )
                    )
                )
                .clickable { isFlipped = !isFlipped },
            contentAlignment = Alignment.Center
        ) {
            val imageResource = if (showBackImage) R.drawable.img else R.drawable.img_2
            Image(
                painter = painterResource(id = imageResource),
                contentDescription = if (showBackImage) stringResource(R.string.back_of_the_card) else stringResource(
                    R.string.front_of_the_card
                ),
                modifier = Modifier
                    .size(180.dp)
                    .clip(RoundedCornerShape(12.dp))
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = if (isFlipped) stringResource(R.string.see_front) else stringResource(R.string.flip),
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}

@Preview
@Composable
fun RotatingCardPreview() {
    CardFlipping()
}

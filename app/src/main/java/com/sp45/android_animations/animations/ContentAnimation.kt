package com.sp45.android_animations.animations

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.sp45.android_animations.R

@Composable
fun ContentAnimation() {
    var isPictureOne by remember { mutableStateOf(true) }
    val image1 = painterResource(R.drawable.img_2)
    val image2 = painterResource(R.drawable.img)

    Column(
        modifier = Modifier
            .padding(16.dp, 100.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,

        ) {
        Button(onClick = { isPictureOne = !isPictureOne }) {
            Text(stringResource(R.string.change_profile_picture))
        }
        Spacer(modifier = Modifier.height(16.dp))
        AnimatedContent(
            targetState = isPictureOne,
            transitionSpec = {
                (fadeIn() + scaleIn(initialScale = 0.8f)).togetherWith(
                    fadeOut() + scaleOut(
                        targetScale = 1.2f
                    )
                )
            }, label = ""
        ) { targetState ->
            Image(
                painter = if (targetState) image1 else image2,
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .size(150.dp)
                    .clip(RoundedCornerShape(16.dp))
            )
        }
    }
}

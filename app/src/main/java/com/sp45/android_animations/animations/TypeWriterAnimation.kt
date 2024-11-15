package com.sp45.android_animations.animations

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

@Composable
fun TypeWriterAnimation() {
    Box( modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center){
        TypeWriterComponent(
            text = "Type Writer Animation!",
            textStyle = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            ),
            cursorColor = Color.White
        )
    }
}


@Composable
fun TypeWriterComponent(
    text: String,
    textStyle: TextStyle = TextStyle(
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold,
        color = Color.White
    ),
    cursorColor: Color = Color.White,
    cursorBlinkDuration: Int = 600,
    charAppearanceDuration: Int = 350,
    charAppearanceDelay: Int = 130
) {
    val currentTextIndex = remember { mutableIntStateOf(0) }
    val cursorVisibility = remember { mutableStateOf(true) }

    LaunchedEffect(text) {
        while (true) {
            while (true) {
                cursorVisibility.value = !cursorVisibility.value
                delay(cursorBlinkDuration.toLong())
            }
        }
    }

    LaunchedEffect(text) {
        for (i in text.indices) {
            currentTextIndex.intValue = i
            delay(charAppearanceDelay.toLong())
        }
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text.take(currentTextIndex.intValue),
            style = textStyle,
            modifier = Modifier.animateContentSize(
                animationSpec = tween(charAppearanceDuration)
            )
        )
        if (cursorVisibility.value) {
            Text(
                "|",
                style = textStyle.copy(color = cursorColor),
                modifier = Modifier.padding(start = 4.dp)
            )
        }
    }
}
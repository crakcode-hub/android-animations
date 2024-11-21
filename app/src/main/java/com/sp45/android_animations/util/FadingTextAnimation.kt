package com.sp45.android_animations.util

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import com.sp45.android_animations.R
import kotlinx.coroutines.delay

@Composable
fun FadingTextAnimation() {
    val messages: List<String> = listOf(
        stringResource(R.string.resources),
        stringResource(R.string.mentorship),
        stringResource(R.string.interview_prep)
    )
    val fadeDuration = 1000
    val pauseDuration = 1500
    val currentMessageIndex = remember { mutableIntStateOf(0) }

    LaunchedEffect(Unit) {
        while (true) {
            delay((fadeDuration + pauseDuration).toLong())
            currentMessageIndex.intValue = (currentMessageIndex.intValue + 1) % messages.size
        }
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.background(MaterialTheme.colorScheme.background)
    ) {
        Crossfade(
            targetState = currentMessageIndex.intValue,
            animationSpec = tween(durationMillis = fadeDuration)
        ) { index ->
            Text(
                text = messages[index],
                style = TextStyle(fontSize = 16.sp),
                color = MaterialTheme.colorScheme.onBackground
            )
        }
    }
}
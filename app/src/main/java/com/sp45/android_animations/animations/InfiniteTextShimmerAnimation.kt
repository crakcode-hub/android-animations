package com.sp45.android_animations.animations

import androidx.compose.animation.core.EaseInOutBack
import androidx.compose.animation.core.EaseInOutCirc
import androidx.compose.animation.core.Easing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun InfiniteTextShimmerAnimation() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(8.dp),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val fontSize = 64.sp

        Text(
            text = "CrakCode",
            style = TextStyle(
                fontSize = fontSize
            ).textShimmerBrush(
                animationDurationMillis = 2000,
                easing = EaseInOutCirc,
                shimmerLaps = 3
            )
        )

        Text(
            text = "CrakCode",
            style = TextStyle(
                fontSize = fontSize
            ).textShimmerBrush(
                shimmerColors = listOf(
                    Color.Black,
                    Color.Green,
                    Color.White
                ),
                animationDurationMillis = 1500,
                easing = EaseInOutBack
            )
        )

        Text(
            text = "CrakCode",
            style = TextStyle(
                fontSize = fontSize
            ).textShimmerBrush(
                shimmerColors = listOf(
                    Color.Black,
                    Color.Green
                ),
                animationDurationMillis = 1200
            )
        )
    }
}

@Composable
private fun TextStyle.textShimmerBrush(
    shimmerColors: List<Color> = listOf(Color.Gray, Color.DarkGray, Color.LightGray),
    animationDurationMillis: Int = 1000,
    shimmerLaps: Int = 1,
    easing: Easing = LinearEasing
): TextStyle {
    val currentFontSizePx = with(LocalDensity.current) { fontSize.toPx() }

    val transition = rememberInfiniteTransition(label = "transition")
    val offset by transition.animateFloat(
        initialValue = 0f,
        targetValue = currentFontSizePx * 2 * shimmerLaps,
        animationSpec = infiniteRepeatable(
            animation = tween(animationDurationMillis, easing = easing)
        ),
        label = "Shimmer animation"
    )
    val brush = Brush.linearGradient(
        colors = shimmerColors,
        start = Offset(offset, offset),
        end = Offset(offset + currentFontSizePx, offset + currentFontSizePx),
        tileMode = TileMode.Mirror
    )

    return copy(brush = brush)
}


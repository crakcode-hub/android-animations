package com.sp45.android_animations.animations

import androidx.compose.animation.core.EaseOutCubic
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun ValueTweenAnimation() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var isRound by remember {
            mutableStateOf(false)
        }
        var toggleSize by remember {
            mutableStateOf(false)
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CardButton({ isRound = !isRound }, "Toggle roundness")
            CardButton({ toggleSize = !toggleSize }, "Toggle size")
            CardButton({
                toggleSize = !toggleSize
                isRound = !isRound
            }, "Toggle both")
        }
        val borderRadius by animateIntAsState(
            targetValue = if (isRound) 0 else 50,
            label = "Border radius animation",
            animationSpec = tween(
                1000,
                easing = EaseOutCubic
            )
        )
        val size by animateDpAsState(
            targetValue = if (toggleSize) 300.dp else 100.dp,
            label = "Size animation",
            animationSpec = tween(
                1000,
                easing = EaseOutCubic
            )
        )
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .size(size)
                    .clip(RoundedCornerShape(borderRadius))
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(Color(0xFF004d00), Color(0xFF1E1E1E))
                        )
                    )
                    .border(
                        border = BorderStroke(2.dp, Color.White),
                        shape = RoundedCornerShape(borderRadius)
                    )
            )
        }
    }
}

@Composable
fun CardButton(onclick: () -> Unit, text: String) {
    Card(
        onClick = onclick,
        modifier = Modifier
            .width(200.dp)
            .padding(5.dp)
            .border(border = BorderStroke(1.dp, Color.LightGray), shape = RoundedCornerShape(8.dp)),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(30.dp)
                .width(60.dp)
                .background(Color.Black),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = text,
                fontSize = 20.sp,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}
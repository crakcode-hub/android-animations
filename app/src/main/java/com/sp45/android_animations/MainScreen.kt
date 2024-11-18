package com.sp45.android_animations

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun MainScreen(navController: NavController) {
    val animations = listOf(
        AnimationItem("Swipe To Delete Animation", Color(0xFF6200EE)),
        AnimationItem("Bouncing Ball Animation", Color(0xFF03DAC5)),
        AnimationItem("Value Spring Animation", Color(0xFF00BCD4)),
        AnimationItem("Content Animation", Color(0xFFFFEB3B)),
        AnimationItem("Carousel Slider", Color(0xFF3F51B5)),
        AnimationItem("Expandable Card Animation", Color(0xFF4CAF50)),
        AnimationItem("Wave Loading Bar", Color(0xFFFF9800)),
        AnimationItem("Confetti Animation", Color(0xFF9C27B0)),
        AnimationItem("Flip Card", Color(0xFF2196F3)),
        AnimationItem("Floating Elements", Color(0xFFE91E63)),
        AnimationItem("Type Writer Animation", Color(0xFF4CAF50)),
        AnimationItem("Emoji Progress Bar", Color(0xFF3F51B5)),
        AnimationItem("Expanding Rings", Color(0xFF4CAF50)),
        AnimationItem("Orbiting Objects", Color(0xFF3F51B5)),
        AnimationItem("Card Flipping", Color(0xFFE91E63)),
        AnimationItem("Button to Image",Color(0xFFFF9800)),
        AnimationItem("Image Transition",Color(0xFF6200EE)),
        AnimationItem("Sliding Door", Color(0xFF00BCD4)),
        AnimationItem("Text Explosion", Color(0xFFE91E63))
        )

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(animations.size) { index ->
                AnimationCard(
                    animationItem = animations[index],
                    onClick = {
                        val route =
                            NavigationDestinations.createAnimationRoute(animations[index].name)
                        navController.navigate(route)
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun AnimationCard(
    animationItem: AnimationItem,
    onClick: () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isHovered by interactionSource.collectIsHoveredAsState()

    var isPressed by remember { mutableStateOf(false) }
    var isVisible by remember { mutableStateOf(false) }

    val elevation by animateFloatAsState(
        targetValue = when {
            isPressed -> 2f
            isHovered -> 8f
            else -> 4f
        }
    )

    val scale by animateFloatAsState(
        targetValue = if (isHovered) 1.02f else 1f
    )

    val accentColor = animationItem.color

    LaunchedEffect(Unit) {
        isVisible = true
    }

    AnimatedVisibility(
        visible = isVisible,
        enter = fadeIn(
            animationSpec = tween(500)
        ) + scaleIn(
            animationSpec = tween(500)
        ),
        exit = fadeOut(
            animationSpec = tween(500)
        ) + scaleOut(
            animationSpec = tween(500)
        )
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .graphicsLayer {
                    scaleX = scale
                    scaleY = scale
                    shadowElevation = elevation
                }
                .pointerInteropFilter {
                    when (it.action) {
                        android.view.MotionEvent.ACTION_DOWN -> isPressed = true
                        android.view.MotionEvent.ACTION_UP -> isPressed = false
                        android.view.MotionEvent.ACTION_CANCEL -> isPressed = false
                    }
                    false
                }
                .clickable(
                    interactionSource = interactionSource,
                    indication = rememberRipple(bounded = true),
                    onClick = onClick
                )
                .animateContentSize(),
            shape = RoundedCornerShape(16.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .drawBehind {
                        rotate(45f) {
                            drawCircle(
                                color = accentColor.copy(alpha = 0.1f),
                                radius = size.width * 0.2f,
                                center = Offset(size.width * 0.8f, size.height * 0.2f)
                            )
                        }
                    }
                    .padding(16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = animationItem.name,
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "Tap to preview",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                        )
                    }

                    IconButton(
                        onClick = onClick,
                        modifier = Modifier
                            .clip(RoundedCornerShape(12.dp))
                            .background(accentColor.copy(alpha = 0.1f))
                    ) {
                        Icon(
                            imageVector = Icons.Default.PlayArrow,
                            contentDescription = "Play Animation",
                            tint = accentColor
                        )
                    }
                }
            }
        }
    }
}

data class AnimationItem(
    val name: String,
    val color: Color
)
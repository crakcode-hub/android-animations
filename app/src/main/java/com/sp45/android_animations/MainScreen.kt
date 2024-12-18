package com.sp45.android_animations

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.sp45.android_animations.util.AnimationCard
import com.sp45.android_animations.util.AnimationItem
import com.sp45.android_animations.util.CustomTopAppBar
import com.sp45.android_animations.util.FadingTextAnimation
import com.sp45.android_animations.util.WebAppText

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
    AnimationItem("Button to Image", Color(0xFFFF9800)),
    AnimationItem("Image Transition", Color(0xFF6200EE)),
    AnimationItem("Sliding Door", Color(0xFF00BCD4)),
    AnimationItem("Text Explosion", Color(0xFFE91E63))
)

@Composable
fun MainScreen(
    navController: NavController
) {
    Scaffold(
        topBar = {
            CustomTopAppBar(
                title = "Android Animations"
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.background)
                    .padding(paddingValues)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 10.dp, top = 7.dp, end = 10.dp, bottom = 7.dp),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(
                        Icons.Default.Info,
                        contentDescription = stringResource(R.string.open_web_app),
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(22.dp)
                    )
                    Spacer(Modifier.width(5.dp))
                    WebAppText(
                        onClick = {
                            navController.navigate(NavigationDestinations.WEB_VIEW)
                        }
                    )
                    Spacer(Modifier.width(5.dp))
                    FadingTextAnimation()
                }
                LazyColumn(
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.background)
                ) {
                    items(
                        animations.size,
                    ) { index ->
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
    )
}
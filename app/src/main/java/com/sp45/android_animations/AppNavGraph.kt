package com.sp45.android_animations

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.sp45.android_animations.animations.AutoImageTransition
import com.sp45.android_animations.animations.BirthdayPopperAnimation
import com.sp45.android_animations.animations.BouncingBallAnimation
import com.sp45.android_animations.animations.ButtonToImage
import com.sp45.android_animations.animations.CarouselSlider
import com.sp45.android_animations.animations.ContentAnimation
import com.sp45.android_animations.animations.EmojiProgressBar
import com.sp45.android_animations.animations.ExpandableCardAnimation
import com.sp45.android_animations.animations.FlipCard
import com.sp45.android_animations.animations.FloatingElements
import com.sp45.android_animations.animations.RotatingCard
import com.sp45.android_animations.animations.SlidingDoorAnimation
import com.sp45.android_animations.animations.SwipeToDeleteAnimation
import com.sp45.android_animations.animations.TypeWriterAnimation
import com.sp45.android_animations.animations.ValueSpringAnimation
import com.sp45.android_animations.animations.WaveLoadingBar
import java.net.URLDecoder
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

object NavigationDestinations {
    const val MAIN = "main"
    const val ANIMATION = "animation/{animationName}"

    fun createAnimationRoute(animationName: String): String {
        val encodedName = URLEncoder.encode(animationName, StandardCharsets.UTF_8.toString())
        return "animation/$encodedName"
    }
}

@Composable
fun AppNavGraph(navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = NavigationDestinations.MAIN
    ) {
        composable(NavigationDestinations.MAIN) {
            MainScreen(navController)
        }

        composable(
            route = NavigationDestinations.ANIMATION,
            arguments = listOf(
                navArgument("animationName") {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            val encodedAnimationName = backStackEntry.arguments?.getString("animationName") ?: ""
            val animationName = URLDecoder.decode(encodedAnimationName, StandardCharsets.UTF_8.toString())

            when (animationName) {
                "Swipe To Delete Animation" -> SwipeToDeleteAnimation()
                "Bouncing Ball Animation" -> BouncingBallAnimation()
                "Value Spring Animation" -> ValueSpringAnimation()
                "Content Animation" -> ContentAnimation()
                "Expandable Card Animation" -> ExpandableCardAnimation()
                "Wave Loading Bar" -> WaveLoadingBar()
                "Confetti Animation" -> BirthdayPopperAnimation()
                "Carousel Slider" -> CarouselSlider()
                "Flip Card" -> FlipCard()
                "Floating Elements" -> FloatingElements()
                "Type Writer Animation" -> TypeWriterAnimation()
                "Emoji Progress Bar" -> EmojiProgressBar()
                "Rotate the Card" -> RotatingCard()
                "Button to Image" -> ButtonToImage()
                "Image Transition" -> AutoImageTransition()
                "Sliding Door" -> SlidingDoorAnimation()
                else -> MainScreen(navController)
            }
        }
    }
}
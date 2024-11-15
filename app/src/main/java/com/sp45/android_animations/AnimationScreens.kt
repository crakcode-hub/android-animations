package com.sp45.android_animations

import androidx.compose.runtime.Composable
import com.sp45.android_animations.animations.AutoImageCarousel
import com.sp45.android_animations.animations.BirthdayPopperAnimation
import com.sp45.android_animations.animations.BouncingBallAnimation
import com.sp45.android_animations.animations.ButtonToImage
import com.sp45.android_animations.animations.ContentAnimation
import com.sp45.android_animations.animations.ContentCountAnimation
import com.sp45.android_animations.animations.ExpandableCardAnimation
import com.sp45.android_animations.animations.FadeInTextAnimation
import com.sp45.android_animations.animations.FlipCard
import com.sp45.android_animations.animations.InfiniteBorderAnimation
import com.sp45.android_animations.animations.InfiniteTextShimmerAnimation
import com.sp45.android_animations.animations.InfiniteValueAnimation
import com.sp45.android_animations.animations.SwipeToDeleteAnimation
import com.sp45.android_animations.animations.ValueSpringAnimation
import com.sp45.android_animations.animations.ValueTweenAnimation

enum class AnimationScreens {
    ValueTweenAnimation,
    ValueSpringAnimation,
    InfiniteValueAnimation,
    InfiniteBorderAnimation,
    InfiniteTextShimmerAnimation,
    ContentAnimation,
    FadeInTextAnimation,
    ExpandableCardAnimation,
    ContentCountAnimation,
    BouncingBallAnimation,
    ConfettiAnimation,
    CardFlippingAnimation,
    ButtonToImageAnimation,
    AutoImageAnimation,
    SwipeToDeleteAnimation;

    @Composable
    fun ComposeScreen() {
        when (this) {
            ValueTweenAnimation -> ValueTweenAnimation()
            ValueSpringAnimation -> ValueSpringAnimation()
            InfiniteValueAnimation -> InfiniteValueAnimation()
            InfiniteBorderAnimation -> InfiniteBorderAnimation()
            InfiniteTextShimmerAnimation -> InfiniteTextShimmerAnimation()
            FadeInTextAnimation -> FadeInTextAnimation()
            ContentAnimation -> ContentAnimation()
            BouncingBallAnimation -> BouncingBallAnimation()
            ExpandableCardAnimation -> ExpandableCardAnimation()
            ContentCountAnimation -> ContentCountAnimation()
            SwipeToDeleteAnimation -> SwipeToDeleteAnimation()
            ConfettiAnimation -> BirthdayPopperAnimation()
            CardFlippingAnimation -> FlipCard()
            ButtonToImageAnimation -> ButtonToImage()
            AutoImageAnimation -> AutoImageCarousel()
        }
    }
}
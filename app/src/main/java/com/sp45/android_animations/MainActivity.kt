package com.sp45.android_animations

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.sp45.android_animations.ui.theme.AndroidAnimationsTheme
import com.sp45.android_animations.util.ThemeManager

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().setKeepOnScreenCondition {
            false
        }
        setContent {
            AndroidAnimationsApp()
        }
    }
}

@Composable
fun AndroidAnimationsApp() {
    val systemDarkTheme = isSystemInDarkTheme()
    ThemeManager.initializeTheme(systemDarkTheme)
    val darkTheme = ThemeManager.observeTheme()

    AndroidAnimationsTheme(darkTheme = darkTheme) {
        AppNavGraph()
    }
}
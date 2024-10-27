package com.sp45.android_animations

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.sp45.android_animations.ui.theme.AndroidanimationsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().setKeepOnScreenCondition {
            false
        }
        setContent {
            AndroidanimationsTheme {
                val navController = rememberNavController()
                val animationSamples = remember {
                    AnimationScreens.entries.toTypedArray()
                }
                val backStackEntry by navController.currentBackStackEntryAsState()
                Scaffold(
                    topBar = {
                        MainAppBar(
                            currentScreen = backStackEntry?.destination?.route ?: MainScreenName,
                            canNavigateBack = navController.previousBackStackEntry != null,
                            navigateBack = { navController.navigateUp() }
                        )
                    }
                ) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = MainScreenName,
                        modifier = Modifier.padding(innerPadding),
                        enterTransition = {
                            slideIntoContainer(
                                towards = AnimatedContentTransitionScope.SlideDirection.Left,
                                animationSpec = tween(700)
                            )
                        },
                        exitTransition = {
                            fadeOut(
                                animationSpec = tween(600)
                            )
                        },
                        popEnterTransition = {
                            fadeIn(
                                animationSpec = tween(600)
                            )
                        },
                        popExitTransition = {
                            slideOutOfContainer(
                                towards = AnimatedContentTransitionScope.SlideDirection.Right,
                                animationSpec = tween(700)
                            )
                        }
                    ) {
                        composable(route = MainScreenName) {
                            MainScreen {
                                navController.navigate(it.name)
                            }
                        }
                        animationSamples.forEach { animation ->
                            composable(route = animation.name) {
                                animation.ComposeScreen()
                            }
                        }
                    }
                }
            }
        }
    }
}
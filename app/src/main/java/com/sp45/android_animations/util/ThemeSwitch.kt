package com.sp45.android_animations.util

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf

@Composable
fun ThemeSwitch(darkTheme: Boolean, onThemeChange: () -> Unit) {
    Switch(
        checked = darkTheme,
        onCheckedChange = { onThemeChange() },
        colors = SwitchDefaults.colors(
            checkedTrackColor = MaterialTheme.colorScheme.secondaryContainer,
            checkedThumbColor = MaterialTheme.colorScheme.primary,
            uncheckedTrackColor = MaterialTheme.colorScheme.secondaryContainer,
            uncheckedThumbColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
        ),
        enabled = true
    )
}

object ThemeManager {
    private var _isDarkTheme = mutableStateOf(false)
    private var isInitialized = false

    fun initializeTheme(defaultDarkTheme: Boolean) {
        if (!isInitialized) {
            _isDarkTheme.value = defaultDarkTheme
            isInitialized = true
        }
    }

    fun toggleTheme() {
        _isDarkTheme.value = !_isDarkTheme.value
    }

    @Composable
    fun observeTheme(): Boolean = _isDarkTheme.value
}
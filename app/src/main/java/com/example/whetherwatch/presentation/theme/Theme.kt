package com.example.whetherwatch.presentation.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.wear.compose.material.Colors
import androidx.wear.compose.material.MaterialTheme

private val WearColorPalette = Colors(
    primary = Color(0xFF4FC3F7),
    primaryVariant = Color(0xFF0288D1),
    secondary = Color(0xFF81C784),
    secondaryVariant = Color(0xFF388E3C),
    background = Color(0xFF0D1117),
    surface = Color(0xFF1A1F29),
    error = Color(0xFFCF6679),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onBackground = Color.White,
    onSurface = Color.White,
    onError = Color.Black
)

@Composable
fun WhetherWatchTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colors = WearColorPalette,
        content = content
    )
}
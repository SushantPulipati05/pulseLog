// ui/theme/Theme.kt
package com.example.pulselog.ui.theme

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    background = Background,
    surface = CardBackground,
    primary = AccentPurple,
    onBackground = TextPrimary,
    onSurface = TextPrimary,
    secondary = AccentBlue
)

@Composable
fun PulseLogTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = DarkColorScheme,
        typography = Typography,
        content = content
    )
}
package com.severett.planetxcompose.jvm.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColorScheme = lightColorScheme(
    primary = ApiumGreen,
    onPrimary = ApiumBlack,
    secondary = ApiumBlack,
    surface = ApiumBlack,
    onSurface = ApiumGreen,
    secondaryContainer = ApiumBlack,
    onSecondaryContainer = ApiumGreen,
    onSurfaceVariant = ApiumGreen,

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    */
)

@Composable
fun ComposeTheme(content: @Composable () -> Unit) {
    /*
    val colorScheme = LightColorScheme
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = true
        }
    }
    */

    MaterialTheme(colorScheme = LightColorScheme, typography = Typography, content = content)
}

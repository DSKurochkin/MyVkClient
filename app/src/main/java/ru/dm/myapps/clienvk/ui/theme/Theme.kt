package ru.dm.myapps.clienvk.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val LightColorScheme = lightColorScheme(
    primary = Color.White,
    onBackground = Color.White,
    secondary = Color.White,
    onPrimary = Black900,
    onSecondary = Black500,
    onSurface = Color.Black,
    onTertiary = Color.Yellow
)

private val DarkColorScheme = darkColorScheme(
    primary = Black900,
    secondary = Black900,
    onBackground = Black900,
    onPrimary = Color.White,
    onSecondary = Black500,
    onSurface = Color.White,
    onTertiary = Color.Green
)

@Composable
fun ClienVKTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {

    val colorScheme = when {
        darkTheme -> {
            DarkColorScheme
        }

        else -> {
            LightColorScheme

        }
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
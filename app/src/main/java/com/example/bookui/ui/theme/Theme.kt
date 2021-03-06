package com.example.bookui.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = Shamrock,
    primaryVariant = Shamrock,
    secondary = Shamrock,
    secondaryVariant = Shamrock,
    background = Tiber,
    surface = Tiber,
    onSurface = Color.White,
    onBackground = Color.White,
    onPrimary = Zuccini,
    onSecondary = Zuccini
)

private val LightColorPalette = lightColors(
    primary = DodgerBlue,
    primaryVariant = DodgerBlue,
    secondary = DodgerBlue,
    secondaryVariant = DodgerBlue,
    onPrimary = Color.White,
    onSecondary = Color.White,
    surface = Color.White,
    onSurface = NileBlue,
    background = Alabaster,
    onBackground = NileBlue
)

@Composable
fun BookUITheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}
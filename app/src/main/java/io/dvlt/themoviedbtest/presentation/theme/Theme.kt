package io.dvlt.themoviedbtest.presentation.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val LightColorPalette = lightColors(
    primary = BLUE900,
    primaryVariant = BLUE900,
    secondary = GOLD500,
    secondaryVariant = GOLD500,
    background = GOLD100,
    surface = GOLD100,
    error = GOLD900,
    onPrimary = GREY200,
    onSecondary = GREY200,
    onBackground = BLACKSHADE40,
    onSurface = BLACKSHADE40,
    onError = GOLD500
)

private val DarkColorPalette = darkColors(
    primary = RED800,
    primaryVariant = RED800,
    secondary = Red500,
    secondaryVariant = Red500,
    background = Black900,
    surface = Black900,
    error = YELLOW900,
    onPrimary = GREY100,
    onSecondary = GREY100,
    onBackground = YELLOW500,
    onSurface = YELLOW500,
    onError = YELLOW100
)


@Composable
fun BaseMoviesTheme(
    darkTheme: Boolean ,
    content: @Composable() () -> Unit
) {
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
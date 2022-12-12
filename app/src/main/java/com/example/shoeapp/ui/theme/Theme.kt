package com.example.shoeapp.ui.theme

import android.R.id.primary
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp



private val ICON_SIZE = 24.dp
val dark_primary = Color(0xFFffb59c)
val dark_onPrimary = Color(0xFF5f1600)
val dark_background = Color(0xFF211a18)
val dark_onBackground = Color(0xFFede0dc)
val dark_surface = Color(0xFF302522)
val dark_onSurface = Color(0xFFede0dc)

private val DarkColorPalette = darkColors(
    //primary = Purple200,
   // primaryVariant = Purple700,
    //secondary = Teal200
    primary = dark_primary,
    onPrimary = dark_onPrimary,
    background = dark_background,
    onBackground = dark_onBackground,
    surface = dark_surface,
    onSurface = dark_onSurface




)


private val LightColorPalette = lightColors(
    primary = Purple500,
    primaryVariant = Purple700,
    secondary = Teal200

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun ShoeAppTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
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
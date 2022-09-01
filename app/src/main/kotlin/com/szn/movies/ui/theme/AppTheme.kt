package com.szn.movies.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

val LightColors = lightColors(
    background = Color(46, 43, 61),
    onBackground = Color.Black,
    primary = Color(4, 7, 34),
    primaryVariant = Color.Blue,
    secondary = Color.White,
    surface = Color.Black,
    secondaryVariant = Color.LightGray
)

private val DarkColors = darkColors(
    background = Color(46, 43, 61),
    onBackground = Color.Black,
    primary = Color(4, 7, 34),
    primaryVariant = Color.Blue,
    secondary = Color.White,
    surface = Color.Black,
    secondaryVariant = Color.LightGray
)

private val Typography = Typography(
    body1 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    body2 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    )
)

private val Shapes = Shapes(
    small = RoundedCornerShape(4.dp),
    medium = RoundedCornerShape(4.dp),
    large = RoundedCornerShape(0.dp)
)

@Composable
fun AppTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {

    val colors = if (darkTheme) DarkColors else LightColors

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}
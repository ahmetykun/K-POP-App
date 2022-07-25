package com.ahmet.ahmetapi_deneme_1.ui.theme

import androidx.compose.ui.graphics.Color
import androidx.compose.material.Colors
import androidx.compose.runtime.Composable

val Purple200 = Color(0xFFBB86FC)
val Purple500 = Color(0xFF6200EE)
val Purple700 = Color(0xFF3700B3)
val Teal200 = Color(0xFF03DAC5)
val YeniCOLOR = Color(0xFF7ABCF0)
val LightGray = Color(0xFFFFFFFF)
val DarkGray = Color(0xFF1B1B1B)
val YilidzColor= Color(0xFFFFC107)

val ShimmerLightGray = Color(0xFFFFFFF1)
val ShimmerMediumGray = Color(0xFFE3E3E3)
val ShimmerDarkGray = Color(0xFF161614)


val Colors.statusBarColor

    get() = if (isLight) YeniCOLOR else Color.Black

val Colors.welcomeScreenBackgroundColor

    get() = if (isLight) Color.White else Color.Black

val Colors.titleColor

    get() = if (isLight) DarkGray else LightGray

val Colors.descriptionColor

    get() = if (isLight) DarkGray.copy(alpha = 0.5f)
    else LightGray.copy(alpha = 0.5f)

val Colors.activeIndicotorColor

    get() = if (isLight) YeniCOLOR else Purple700

val Colors.inactiveIndicotorColor

    get() = if (isLight) YilidzColor else DarkGray

val Colors.bottomColor

    get() = if (isLight) YeniCOLOR else Purple500

val Colors.topAppARAMAbar

    get() = if (isLight) Color.White else LightGray

val Colors.topAppARAMAbarbacgraunt

    get() = if (isLight) YeniCOLOR else Color.Black

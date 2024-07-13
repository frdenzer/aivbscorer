package com.example.aivbscorer.ui.theme

import androidx.compose.ui.graphics.Color

val Purple200 = Color(0xFFBB86FC)
val Purple500 = Color(0xFF6200EE)
val Purple700 = Color(0xFF3700B3)
val Teal200 = Color(0xFF03DAC5)

val Color.name
    get() = when (this) {
        Color.Red -> "Red"
        Color.Blue -> "Blue"
        Color.Green -> "Green"
        Color.Black -> "Black"
        Color.White -> "White"
        Color.Gray -> "Gray"
        Color.DarkGray -> "DarkGray"
        Color.LightGray -> "LightGray"
        Color.Cyan -> "Cyan"
        Color.Magenta -> "Magenta"
        Color.Yellow -> "Yellow"
        Color.Transparent -> "Transparent"
        Color.Unspecified -> "Unspecified"
        else -> this.toString()
    }
package com.example.aivbscorer.ui.theme

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

val Shapes = Shapes(
    small = RoundedCornerShape(14.dp),
    medium = RoundedCornerShape(24.dp),
    large = RoundedCornerShape(40.dp)
)

@Composable
fun VerticalSpacing() {
    Spacer(modifier = Modifier.height(10.dp))
}

@Composable
fun HorizontalSpacing() {
    Spacer(modifier = Modifier.width(10.dp))
}

package com.example.aivbscorer

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.aivbscorer.components.WideButConciseLog

@Preview(heightDp = 250, widthDp = 450)
@Preview(heightDp = 450, widthDp = 250)
@Composable
fun PreviewScoringScreen() {
    ScoringScreen(navController = rememberNavController())
}

@Composable
fun ScoringScreen(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        BigNumbers(modifier = Modifier.weight(1f))
        WideButConciseLog(
            navController, modifier = Modifier
                .height(110.dp)
                .fillMaxWidth()
        )
    }
}


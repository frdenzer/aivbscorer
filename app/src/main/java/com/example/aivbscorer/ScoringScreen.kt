package com.example.aivbscorer

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.aivbscorer.components.WideButConciseLog
import com.example.aivbscorer.eventing.GameEvent

@Preview
@Composable
fun PreviewScoringScreen() {
    ScoringScreen(navController = rememberNavController())
}

@Composable
fun ScoringScreen(navController: NavController) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        val gameEvent by GameViewModel.events.collectAsState(initial = null)

        // reload when event received: Event.ResetLog
        if (gameEvent == GameEvent.ResetLog) {
            BigNumbers()
        }
        WideButConciseLog(navController)
    }
}


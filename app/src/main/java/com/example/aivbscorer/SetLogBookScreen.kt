package com.example.aivbscorer

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import com.example.aivbscorer.components.ScoreItem

@Composable
fun SetLogBookScreen(
    vm: GameViewModel,
    navController: NavController,
) {
    // Assuming GameViewModel is accessible here, either passed as a parameter or obtained via viewModel()
    val setLogBook by vm.setLog.collectAsState()

    LazyColumn {
        items(setLogBook) { scoreEntry ->
            ScoreItem(scoreEntry)
        }
    }
}
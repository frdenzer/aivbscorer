package com.example.aivbscorer

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.aivbscorer.components.ScoreItem

@Preview(showBackground = true)
@Composable
fun SetLogBookScreen() {
    val navController: NavController = rememberNavController()
    // Assuming GameViewModel is accessible here, either passed as a parameter or obtained via viewModel()
    val setLogBook by GameViewModel.setLog.collectAsState()

    LazyColumn {
        items(setLogBook) { scoreEntry ->
            ScoreItem(scoreEntry)
        }
        item {
            // Add a button to navigate back to the ScoringScreen
            Button(onClick = { navController.navigate("ScoringScreen") }) {
                Text(text = "Back")
            }
        }
    }
}
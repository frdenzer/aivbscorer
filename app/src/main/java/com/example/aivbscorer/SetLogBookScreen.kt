package com.example.aivbscorer

import ScoreItem
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.aivbscorer.data.ScoreEntry


@Preview(showBackground = true)
@Composable
fun PreviewSetLogBookScreen() {
    GameViewModel.apply {
        updateSetLogBook(ScoreEntry(Color.Red, 25, Color.Blue, 1))
        updateSetLogBook(ScoreEntry(Color.Red, 0, Color.Blue, 25))
        updateSetLogBook(ScoreEntry(Color.Red, 24, Color.Blue, 26))
    }
    SetLogBookScreen(rememberNavController(), modifier = Modifier.width(393.dp)) // at 393.dp point, preview ends. WTF google?!
}

@Composable
fun SetLogBookScreen(navController: NavController, modifier: Modifier = Modifier) {
    // Assuming GameViewModel is accessible here, either passed as a parameter or obtained via viewModel()
    val setLogBook by GameViewModel.setLog.collectAsState()

    LazyColumn(modifier = modifier) {
        item {
            Row(
                modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(onClick = { navController.navigate(Routes.ScoringScreen.name) }) {
                    Text(text = "\uD83D\uDD22 \uD83C\uDFD0 ${Routes.ScoringScreen.deutsch}")
                }
                // arrow button to navigate back to ScoringScreen
                Button(onClick = { navController.popBackStack() }) {
                    Text("Back")
                }
                Button(onClick = {
                    GameViewModel.resetSetLog()
                }) {
                    Text("Reset score log")
                }
                // resetApp
                Button(onClick = {
                    GameViewModel.resetApp()
                }) {
                    Text("Reset app")
                }
            }
        }
        itemsIndexed(setLogBook) { index, scoreEntry ->
            ScoreItem(index, scoreEntry)
        }
    }
}
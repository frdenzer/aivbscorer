package com.example.aivbscorer.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.aivbscorer.GameViewModel
import com.example.aivbscorer.Routes
import com.example.aivbscorer.data.ScoreEntry
import com.example.aivbscorer.eventing.GameEvent

@Preview(showBackground = true)
@Composable
fun MatchSetLogbookPreview() {
    GameViewModel.apply {
        // index 0, displayIndex 1 -> hidden in abbreviated log
        updateSetLogbook(ScoreEntry(Color.Red, 25, Color.Blue, 1))

        // index 1, displayIndex 2  -> middle entry
        updateSetLogbook(ScoreEntry(Color.Red, 0, Color.Blue, 25))

        // index 2, displayIndex 3 -> top entry
        updateSetLogbook(ScoreEntry(Color.Red, 24, Color.Blue, 26))
    }
    WideButConciseLog(rememberNavController())
}

@Composable
fun WideButConciseLog(navController: NavController, modifier: Modifier = Modifier) {
    val gameEvent by GameViewModel.events.collectAsState(initial = null)

    LazyColumn(modifier = modifier) {
        if (gameEvent is GameEvent.WonGame || GameViewModel.hasLogEntries) {
            GameViewModel.logbookOfSets(true) { index, scoreEntry ->
                item { ScoreItem(index, scoreEntry) }
            }
        }
        item {
            Button(onClick = { navController.navigate(Routes.Logbuch.name) }) {
                Text(text = "Show all")
            }
        }
    }
}

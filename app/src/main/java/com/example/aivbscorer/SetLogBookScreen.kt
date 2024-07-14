package com.example.aivbscorer

import ScoreItem
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
        // index 0, displayIndex 1 -> lowest entry
        updateSetLogBook(ScoreEntry(Color.Red, 25, Color.Blue, 1))

        // index 1, displayIndex 2  -> middle entry
        updateSetLogBook(ScoreEntry(Color.Red, 0, Color.Blue, 25))

        // index 2, displayIndex 3 -> top entry
        updateSetLogBook(ScoreEntry(Color.Red, 24, Color.Blue, 26))
    }
    
    // At 393.dp point, any further preview width increase ends. WTF google?!
    SetLogBookScreen(rememberNavController(), modifier = Modifier.width(393.dp))
}

@Composable
fun SetLogBookScreen(navController: NavController, modifier: Modifier = Modifier) {

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
        GameViewModel.looper { index, scoreEntry ->
            item { ScoreItem(index, scoreEntry) }
        }
    }
}
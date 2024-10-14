package com.example.aivbscorer

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
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
import com.example.aivbscorer.components.ScoreItem
import com.example.aivbscorer.data.ScoreEntry

@Preview(showBackground = true)
@Composable
fun PreviewLogbookScreen() {
    GameViewModel.apply {
        // index 0, displayIndex 1 -> lowest entry
        updateSetLogbook(ScoreEntry(Color.Red, 25, Color.Blue, 1))

        // index 1, displayIndex 2  -> middle entry
        updateSetLogbook(ScoreEntry(Color.Red, 0, Color.Blue, 25))

        // index 2, displayIndex 3 -> top entry
        updateSetLogbook(ScoreEntry(Color.Red, 24, Color.Blue, 26))
    }

    // At 393.dp point, any further preview width increase ends. WTF google?!
    LogbookScreen(rememberNavController(), modifier = Modifier.width(393.dp))
}

@Composable
fun LogbookScreen(navController: NavController, modifier: Modifier = Modifier) {
    val logbookEntries by GameViewModel.logbook.collectAsState()

    fun looper(
    ): List<Pair<Int, ScoreEntry>> = (logbookEntries.size - 1 downTo 0).map { index ->
        index + 1 to logbookEntries[index]
    }

    fun logbookOfSets(
        addItem: (Int, ScoreEntry) -> Unit,
    ) = looper().forEach { (index, entry) ->
        addItem(index, entry)
    }


    LazyColumn(modifier = modifier) {
        item {
            Row(
                modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(onClick = { navController.popBackStack() }) {
                    Text("Back")
                }
                Button(onClick = {
                    GameViewModel.resetSetLog()
                }) {
                    Text("Reset score log")
                }
            }
        }

        if (GameViewModel.hasLogEntries) {
            logbookOfSets { index, scoreEntry ->
                item { ScoreItem(index, scoreEntry) }
            }
        }
    }
}
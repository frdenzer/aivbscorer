package com.example.aivbscorer.components

import ScoreItem
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.aivbscorer.GameViewModel
import com.example.aivbscorer.Routes
import com.example.aivbscorer.data.Constants.TWO
import com.example.aivbscorer.data.ScoreEntry

@Preview(showBackground = true)
@Composable
fun MatchSetLogBookPreview() {
    GameViewModel.apply {
        updateSetLogBook(ScoreEntry(Color.Red, 25, Color.Blue, 1))
        updateSetLogBook(ScoreEntry(Color.Red, 0, Color.Blue, 25))
        updateSetLogBook(ScoreEntry(Color.Red, 24, Color.Blue, 26))
    }
    WideButConciseLog(rememberNavController())
}

@Composable
fun WideButConciseLog(navController: NavController) {
    val modifier: Modifier = Modifier.fillMaxWidth()
    val setLogBook by GameViewModel.setLog.collectAsState()

    LazyColumn(modifier = modifier) {
        // take three, if at most three items are available
        itemsIndexed(setLogBook.take(TWO)) { index, scoreEntry ->
            ScoreItem(index, scoreEntry)
        }
        item {
            Button(onClick = { navController.navigate(Routes.SetLogBookScreen.name) }) {
                Text(text = "Show all")
            }
        }
    }
}



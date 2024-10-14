package com.example.aivbscorer

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.aivbscorer.data.Referee

@Preview(heightDp = 250, widthDp = 450)
@Preview(heightDp = 400, widthDp = 300)
@Preview(heightDp = 300, widthDp = 400)
@Preview(heightDp = 450, widthDp = 250)
@Composable
fun PreviewScoringScreen() {
    ScoringScreen(navController = rememberNavController())
}

@Composable
fun ScoringScreen(navController: NavController) {
    BigNumbers(modifier = Modifier.fillMaxHeight())
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.End,
    ) {
        Button(onClick = Referee.closeSetSavingScore()) {
                Text("Close set")
            }
        Button(onClick = { navController.navigate(Routes.Logbuch.name) }) {
            Text("Show all")
        }
    }
}
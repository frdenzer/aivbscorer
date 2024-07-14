package com.example.aivbscorer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.aivbscorer.components.TeamScoreColumn
import com.example.aivbscorer.components.WideButConciseLog
import com.example.aivbscorer.data.Constants.WIDTH
import com.example.aivbscorer.data.Team

@Preview(showBackground = true)
@Composable
fun ScoringScreenPreview() {
    ScoringScreen(rememberNavController())
}

@Composable
fun ScoringScreen(navController: NavController) {
    val teamA = remember { Team(Color.Red) }
    val teamB = remember { Team(Color.Blue) }

    GameViewModel.initialize(teamA, teamB)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .width(WIDTH),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Gray)
                .weight(1f),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            val modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
            TeamScoreColumn(
                teamA, modifier.background(teamA.colorId)
            )
            TeamScoreColumn(
                teamB, modifier.background(teamB.colorId)
            )
        }

        WideButConciseLog(navController)
    }
}

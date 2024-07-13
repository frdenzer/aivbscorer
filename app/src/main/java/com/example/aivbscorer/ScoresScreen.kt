package com.example.aivbscorer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.aivbscorer.components.TeamScoreColumn
import com.example.aivbscorer.components.WideButConciseLog
import com.example.aivbscorer.data.Constants.WIDTH
import com.example.aivbscorer.data.Team

@Preview(showBackground = true)
@Composable
fun ScoringScreen() {
//    val navController: NavController = rememberNavController()
    val teamA =
        remember { Team(Color.Red, null, GameViewModel::onSetWon, GameViewModel::onResetSetLog) }
    val teamB =
        remember { Team(Color.Blue, teamA, GameViewModel::onSetWon, GameViewModel::onResetSetLog) }
    teamA.opponent = teamB

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
        if (teamA.teamSetsWon + teamB.teamSetsWon == 0) return
        Button(onClick = {
            teamA.resetAllCountersForBothTeams() // reset both Teams scores and sets
        }) {
            Text("Reset score log") // TODO: 1. move onto new fullscreen log screen. 2. make more secure to delete.
        }
        WideButConciseLog()
    }
}

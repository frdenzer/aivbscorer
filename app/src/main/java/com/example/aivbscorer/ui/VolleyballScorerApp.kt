package com.example.aivbscorer.ui

import MatchScoreLog
import TeamScoreColumn
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.aivbscorer.GameViewModel
import com.example.aivbscorer.Team

@Preview(showBackground = true)
@Composable
fun VolleyballScorerAppPreview() {
    VolleyballScorerApp(GameViewModel()).apply { }
}

@Composable
fun VolleyballScorerApp(gvm: GameViewModel) {
    val teamA = remember { Team(Color.Red, null, gvm::onSetWon) }
    val teamB = remember { Team(Color.Blue, teamA, gvm::onSetWon) }
    teamA.opponent = teamB

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Gray),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Column(modifier = Modifier
            .fillMaxHeight()
            .width(150.dp)) {
            Button(onClick = {
                teamA.resetAllCountersForBothTeams() // reset both Teams scores and sets
            }) {
                Text("Reset")
            }
            MatchScoreLog(
                modifier = Modifier.fillMaxHeight(), gameViewModel = gvm
            )
        }
        TeamScoreColumn(
            teamA,
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(.5f)
                .background(teamA.colorId)
        )
        TeamScoreColumn(
            teamB,
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(.5f)
                .background(teamB.colorId)
        )
    }
}
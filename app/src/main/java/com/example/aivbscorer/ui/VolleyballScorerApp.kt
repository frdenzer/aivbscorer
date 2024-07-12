package com.example.aivbscorer.ui

import MatchScoreLog
import TeamScoreColumn
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import com.example.aivbscorer.ui.constants.WIDTH

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
            val commonModifier = Modifier
                .weight(1f)
                .fillMaxHeight()
            TeamScoreColumn(
                teamA, commonModifier.background(teamA.colorId)
            )
            TeamScoreColumn(
                teamB, commonModifier.background(teamB.colorId)
            )
        }
        Button(onClick = {
            teamA.resetAllCountersForBothTeams() // reset both Teams scores and sets
        }) {
            Text("Reset")
        }
        MatchScoreLog(
            modifier = Modifier
                .fillMaxWidth()
//                .height(200.dp)
            ,
            gameViewModel = gvm
        )
    }
}

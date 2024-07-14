package com.example.aivbscorer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.mapSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.aivbscorer.components.TeamScoreColumn
import com.example.aivbscorer.data.Team

@Preview
@Composable
internal fun BigNumbers() {
    val teamA = rememberSaveable(saver = createTeamSaver()) { Team(Color.Red) }
    val teamB = rememberSaveable(saver = createTeamSaver()) { Team(Color.Blue) }
    GameViewModel.initialize(teamA, teamB)
    val wide = Modifier.fillMaxWidth()
    Row(
        modifier = wide,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        TeamScoreColumn(
            teamA,
            wide
                .background(teamA.colorId)
                .weight(1f)
        )
        TeamScoreColumn(
            teamB,
            wide
                .background(teamB.colorId)
                .weight(1f)
        )
    }
}

private fun createTeamSaver(): Saver<Team, *> = mapSaver(save = { team ->
    mapOf(
        "color" to team.colorId.value.toString(), // Convert ULong to String
        "score" to team.teamScore, "setsWon" to team.teamSetsWon
    )
}, restore = {
    Team(
        Color(it["color"]!!.toString().toULong()), it["score"] as Int, it["setsWon"] as Int
    )
})

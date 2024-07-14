package com.example.aivbscorer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.mapSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.aivbscorer.components.TeamScoreColumn
import com.example.aivbscorer.data.Team
import com.example.aivbscorer.eventing.GameEvent

@Preview
@Composable
internal fun BigNumbers() {
    var teamA by remember { mutableStateOf(Team(Color.Red)) }
    var teamB by remember { mutableStateOf(Team(Color.Blue)) }

    val gameEvent by GameViewModel.events.collectAsState(initial = GameEvent.ResetLog)

    if (gameEvent is GameEvent.ResetLog) {
        teamA = rememberSaveable(saver = createTeamSaver()) { Team(Color.Red) }
        teamB = rememberSaveable(saver = createTeamSaver()) { Team(Color.Blue) }
        GameViewModel.initialize(teamA, teamB) // Re-initialize the ViewModel with the reset teams
    }

    val wide = Modifier.fillMaxWidth()
    Row(
        modifier = wide,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        TeamScoreColumn(
            teamA,
            wide.background(teamA.colorId).weight(1f)
        )
        TeamScoreColumn(
            teamB,
            wide.background(teamB.colorId).weight(1f)
        )
    }
}

private fun createTeamSaver(): Saver<Team, *> = mapSaver(save = {
    mapOf(
        "color" to it.colorId.value.toString(), // Convert ULong to String
        "score" to it.teamScore, "setsWon" to it.teamSetsWon
    )
}, restore = {
    Team(
        Color(it["color"].toString().toULong()), it["score"] as Int, it["setsWon"] as Int
    )
})


// Helper composable to use a key for recomposition
@Composable
fun <T> KeyedContent(key: T, content: @Composable () -> Unit) {
    content()
}
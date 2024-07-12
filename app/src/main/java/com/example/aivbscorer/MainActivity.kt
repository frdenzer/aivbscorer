package com.example.aivbscorer

import MatchScoreLog
import TeamScoreColumn
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
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
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import com.example.aivbscorer.ui.theme.VolleyballScorerTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private val viewModel: GameViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launch {
            viewModel.gameEvents.collect { event: GameEvent ->
                when (event) {
                    is GameEvent.HasWonEvent -> {
                        // Update UI based on the winning team
                        updateTeamScores(event.finalScore)
                    }
                    else -> { /* Ignore other events */}
                }
            }
        }

        setContent {
            VolleyballScorerTheme {
                VolleyballScorerApp(viewModel)
            }
        }
    }

    private fun updateTeamScores(entry: ScoreEntry) {
        viewModel.updateScoreLog(entry)
    }
}

@Composable
fun VolleyballScorerApp(gvm: GameViewModel) {
    val teamA = remember { Team(Color.Red, null, gvm::onSetWon) }
    val teamB = remember { Team(Color.Blue, teamA, gvm::onSetWon) }
    teamA.opponent = teamB // Ensure mutual knowledge of opponents

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Gray),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Column(modifier = Modifier.fillMaxHeight()) {
            Button(onClick = {
                teamA.resetAllCountersForBothTeams() // reset both Teams scores and sets
            }) {
                Text("Reset")
            }
            MatchScoreLog(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(200.dp),
                gameViewModel = gvm
            )
        }
        TeamScoreColumn(
            teamA
        )
        TeamScoreColumn(
            teamB
        )
    }
}


data class ScoreEntry(val teamAColor: Color, val teamAScore: Int, val teamBColor: Color, val teamBScore: Int)



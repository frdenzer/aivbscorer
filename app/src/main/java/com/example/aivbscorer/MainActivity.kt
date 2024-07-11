package com.example.aivbscorer

import TeamScoreColumn
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.aivbscorer.ui.theme.VolleyballScorerTheme
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VolleyballScorerTheme {
                VolleyballScorerApp()
            }
        }
    }
}

@Composable
fun VolleyballScorerApp() {
    var teamAScore by remember { mutableIntStateOf(0) }
    var teamBScore by remember { mutableIntStateOf(0) }
    var teamASets by remember { mutableIntStateOf(0) }
    var teamBSets by remember { mutableIntStateOf(0) }

    val resetScore = {
        teamAScore = 0
        teamBScore = 0
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            TeamScoreColumn("Team A", teamAScore, teamASets, {
                teamAScore++
                if (teamAScore == 25) {
                    teamASets++
                    resetScore()
                }
            }, { if (teamAScore > 0) teamAScore-- }, Color.Red)
            TeamScoreColumn("Team B", teamBScore, teamBSets, {
                teamBScore++
                if (teamBScore == 25) {
                    teamBSets++
                    resetScore()
                }
            }, { if (teamBScore > 0) teamBScore-- }, Color.Blue)
        }
        Button(onClick = {
            teamAScore = 0
            teamBScore = 0
            teamASets = 0
            teamBSets = 0
        }) {
            Text("Reset")
        }
    }
}



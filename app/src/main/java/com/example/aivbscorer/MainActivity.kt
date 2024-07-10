package com.example.aivbscorer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.aivbscorer.ui.theme.VolleyballScorerTheme
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
    var teamAScore by remember { mutableStateOf(0) }
    var teamBScore by remember { mutableStateOf(0) }
    var teamASets by remember { mutableStateOf(0) }
    var teamBSets by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            TeamScoreColumn("Team A", teamAScore, teamASets, { teamAScore++ }, { if (teamAScore > 0) teamAScore-- })
            TeamScoreColumn("Team B", teamBScore, teamBSets, { teamBScore++ }, { if (teamBScore > 0) teamBScore-- })
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

@Composable
fun TeamScoreColumn(
    teamName: String,
    score: Int,
    sets: Int,
    onIncrementScore: () -> Unit,
    onDecrementScore: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = teamName, style = MaterialTheme.typography.headlineSmall)
        Text(text = "Score: $score", style = MaterialTheme.typography.headlineMedium)
        Row {
            Button(onClick = onIncrementScore) {
                Text("+")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = onDecrementScore) {
                Text("-")
            }
        }
        Text(text = "Sets: $sets", style = MaterialTheme.typography.headlineSmall)
    }
}

package com.example.aivbscorer.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.aivbscorer.GameViewModel
import com.example.aivbscorer.data.Team
import com.example.aivbscorer.theme.HorizontalSpacing
import com.example.aivbscorer.theme.VerticalSpacing

@Preview(showBackground = true, backgroundColor = 0xF00) // Red
@Composable
fun TeamScoreColumnPreview() {
    TeamScoreColumn(
        team = Team(Color.Red),
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(),
    )
}

@Composable
fun TeamScoreColumn(
    team: Team, modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center // This will center the Row vertically
    ) {
        val referee = GameViewModel.referee
        val textColor = Color.White
        ScalingText(
            text = team.teamScore.toString(),
            color = textColor,
        )
        Row {
            Button(onClick = referee.score(team)) {
                Text("+")
            }
            HorizontalSpacing()
            Button(
                onClick = referee.decrementScore(team),
                enabled = team.teamScore > 0,

                ) {
                Text("-")
            }
        }
        VerticalSpacing()
        Text(
            text = "Sets: ${team.teamSetsWon}",
            style = MaterialTheme.typography.headlineSmall.copy(color = textColor)
        )
        // useful for testing, do not ship in production: the following button stores any result
        Row {
            Button(enabled = team.teamScore > 0, onClick = referee.closeSetSavingScore(team)) {
                Text("close set as win")
            }
        }
    }
}

@Composable
fun ScalingText(text: String, color: Color) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val dynamicFontSize = (screenWidth / (text.length / 3 + 4.5f)).value.coerceAtLeast(12f).sp

    Text(
        text = text, style = MaterialTheme.typography.displayLarge.copy(
            color = color, fontSize = dynamicFontSize
        )
    )
}

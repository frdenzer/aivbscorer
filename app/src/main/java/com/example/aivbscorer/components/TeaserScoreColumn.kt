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
import com.example.aivbscorer.data.Constants.MAX_FONT_SIZE
import com.example.aivbscorer.data.Constants.MIN_FONT_SIZE
import com.example.aivbscorer.data.Referee
import com.example.aivbscorer.data.Team
import com.example.aivbscorer.theme.HorizontalSpacing

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

        val textColor = Color.White
        ScalingText(
            text = team.teamScore.toString(),
            color = textColor,
        )
        Row {
            Button(
                onClick = Referee.decrementScore(team),
                enabled = team.teamScore > 0,

                ) {
                Text("-")
            }
            HorizontalSpacing()
            Button(onClick = Referee.score(team)) {
                Text("+")
            }
        }
    }
}

@Composable
fun ScalingText(text: String, color: Color) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val dynamicFontSize = (screenWidth / (text.length / 3)).value
        .coerceIn(MIN_FONT_SIZE, MAX_FONT_SIZE)
        .sp

    Text(
        text = text, style = MaterialTheme.typography.displayLarge.copy(
            color = color, fontSize = dynamicFontSize
        )
    )
}

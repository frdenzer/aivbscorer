import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.aivbscorer.Team

@Preview(showBackground = true, backgroundColor = 0xF00) // Red
@Composable
fun TeamScoreColumnPreview() {
    TeamScoreColumn(
        team = Team(Color.Red, null) {},
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(.25f),
        // .background(Color.hsl(0, 1f, 0.5f)), // Red
    )
}

@Composable
fun TeamScoreColumn(
    team: Team, modifier: Modifier = Modifier,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {
        val textColor = Color.White
        Text(
            text = "Score: ${team.teamScore}",
            style = MaterialTheme.typography.headlineLarge.copy(color = textColor)
        )
        Row {
            Button(onClick = team::score) {
                Text("+")
            }
            Spacer(modifier = Modifier.width(40.dp))
            Button(
                onClick = team::decrementScore,
                enabled = team.teamScore > 0,

                ) {
                Text("-")
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Sets: ${team.teamSetsWon}",
            style = MaterialTheme.typography.headlineSmall.copy(color = textColor)
        )
        if (team.teamScore < 1) return
        Row {
            Button(onClick = team::closeSetSavingScore) {
                Text("close set with win")
            }
        }
    }
}
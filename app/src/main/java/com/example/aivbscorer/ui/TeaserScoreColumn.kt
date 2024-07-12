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
import androidx.compose.ui.tooling.preview.Preview
import com.example.aivbscorer.Team
import com.example.aivbscorer.ui.theme.HorizontalSpacing
import com.example.aivbscorer.ui.theme.VerticalSpacing

@Preview(showBackground = true, backgroundColor = 0xF00) // Red
@Composable
fun TeamScoreColumnPreview() {
    TeamScoreColumn(
        team = Team(Color.Red, null) {},
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(),
        // .background(Color.hsl(0, 1f, 0.5f)), // Red
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
        Text(
            text = team.teamScore.toString(),
            style = MaterialTheme.typography.displayLarge.copy(color = textColor)
        )
        Row {
            Button(onClick = team::score) {
                Text("+")
            }
            1.Times { HorizontalSpacing() }
            Button(
                onClick = team::decrementScore,
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
        // if (team.teamScore < 1) return
        // Row {
        //     Button(onClick = team::closeSetSavingScore) {
        //         Text("close set with win")
        //     }
        // }
    }
}

@Composable
inline fun <T> Int.Times(content: @Composable () -> T) {
    for (i in 0 until this) {
        content()
    }
}

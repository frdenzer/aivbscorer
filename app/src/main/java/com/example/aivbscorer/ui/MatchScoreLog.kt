import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.aivbscorer.GameViewModel
import com.example.aivbscorer.ScoreEntry
import com.example.aivbscorer.ui.constants.TWO
import com.example.aivbscorer.ui.constants.WIDTH

@Preview(showBackground = true)
@Composable
fun MatchScoreLogPreview() {
    MatchScoreLog(
        GameViewModel().apply {
            updateScoreLog(ScoreEntry(Color.Red, 25, Color.Blue, 1))
            updateScoreLog(ScoreEntry(Color.Red, 0, Color.Blue, 25))
            updateScoreLog(ScoreEntry(Color.Red, 24, Color.Blue, 26))
        },
        modifier = Modifier.width(WIDTH + 1.dp), // +1 dp to avoid linebreak in preview. Real app is fine
    )
}

@Composable
fun MatchScoreLog(
    gameViewModel: GameViewModel,
    modifier: Modifier = Modifier,
) {
    val scoreLog by gameViewModel.scoreLog.collectAsState()

    LazyColumn(modifier = modifier) {
        // take three, if at most three items are available
        items(scoreLog.take(TWO)) { scoreEntry ->
            ScoreItem(scoreEntry)
        }
        if (scoreLog.size > TWO) {
            item {
                Button(onClick = { /* navigateTo("scoreLog")*/ }) {
                    Text(text = "Show all")
                }
            }
        }
    }
}

@Composable
private fun ScoreItem(scoreEntry: ScoreEntry) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(2.dp), horizontalArrangement = Arrangement.Start
    ) {
        Text("${scoreEntry.teamAColor.name}: ${scoreEntry.teamAScore}")
        Text("${scoreEntry.teamBColor.name}: ${scoreEntry.teamBScore}")
    }
}

val Color.name
    get() = when (this) {
        Color.Red -> "Red"
        Color.Blue -> "Blue"
        Color.Green -> "Green"
        Color.Black -> "Black"
        Color.White -> "White"
        Color.Gray -> "Gray"
        Color.DarkGray -> "DarkGray"
        Color.LightGray -> "LightGray"
        Color.Cyan -> "Cyan"
        Color.Magenta -> "Magenta"
        Color.Yellow -> "Yellow"
        Color.Transparent -> "Transparent"
        Color.Unspecified -> "Unspecified"
        else -> this.toString()
    }
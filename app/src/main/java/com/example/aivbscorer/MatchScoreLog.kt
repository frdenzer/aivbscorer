import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.aivbscorer.GameViewModel
import com.example.aivbscorer.ScoreEntry

@Composable
fun MatchScoreLog(modifier: Modifier, gameViewModel: GameViewModel) {
    val scoreLog by gameViewModel.scoreLog.collectAsState()

    Text("Match Score Log")
    LazyColumn(modifier = modifier) {
        items(scoreLog) { scoreEntry ->
            ScoreItem(scoreEntry)
        }
    }
}

@Composable
private fun ScoreItem(scoreEntry: ScoreEntry) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text("Team A: ${scoreEntry.teamAScore}")
        Text("Team B: ${scoreEntry.teamBScore}")
    }
}

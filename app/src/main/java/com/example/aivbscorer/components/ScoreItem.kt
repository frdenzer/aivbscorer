import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.aivbscorer.data.ScoreEntry
import com.example.aivbscorer.theme.HorizontalSpacing
import com.example.aivbscorer.theme.name

@Preview(showBackground = true)
@Composable
fun ScoreItemPreview() {
    ScoreItem(0, ScoreEntry(Color.Red, 25, Color.Blue, 1))
}

@Composable
fun ScoreItem(index: Int, scoreEntry: ScoreEntry) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(2.dp), horizontalArrangement = Arrangement.Start
    ) {
        // index
        Text("${index}.")
        HorizontalSpacing()
        Text("${scoreEntry.teamAColor.name}: ${scoreEntry.teamAScore}")
        HorizontalSpacing()
        Text("${scoreEntry.teamBColor.name}: ${scoreEntry.teamBScore}")
    }
}

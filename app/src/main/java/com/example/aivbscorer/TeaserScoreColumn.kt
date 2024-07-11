import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun TeamScoreColumn(
    teamName: String,
    score: Int,
    sets: Int,
    onIncrementScore: () -> Unit,
    onDecrementScore: () -> Unit,
    backgroundColor: Color
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(.25f)
            .background(backgroundColor),
    ) {
        val textColor = Color.White
        Text(text = "Score: $score", style = MaterialTheme.typography.headlineLarge.copy(color = textColor))
        Text(text = "Sets: $sets", style = MaterialTheme.typography.headlineSmall.copy(color = textColor))
        Row {
            Button(onClick = onIncrementScore) {
                Text("+")
            }
            Spacer(modifier = Modifier.width(40.dp))
            Button(onClick = onDecrementScore) {
                Text("-")
            }
        }
    }
}
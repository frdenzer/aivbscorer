import androidx.compose.foundation.background
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
import androidx.compose.ui.unit.dp
import com.example.aivbscorer.Team

@Composable
fun TeamScoreColumn(
    team: Team
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(.25f)
            .background(team.colorId),
    ) {
        val textColor = Color.White
        Text(text = "Score: ${team.teamScore}", style = MaterialTheme.typography.headlineLarge.copy(color = textColor))
        Row {
            Button(onClick = team::score) {
                Text("+")
            }
            Spacer(modifier = Modifier.width(40.dp))
            Button(onClick = team::decrementScore) {
                Text("-")
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = "Sets: ${team.teamSetsWon}", style = MaterialTheme.typography.headlineSmall.copy(color = textColor))
        Row {
            Button(onClick = team::closeSetSavingScore) {
                Text("force finish set")
            }
        }
    }
}
package com.example.aivbscorer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.aivbscorer.ui.VolleyballScorerApp
import com.example.aivbscorer.ui.theme.VolleyballScorerTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private val viewModel: GameViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launch {
            viewModel.gameEvents.collect { event: GameEvent ->
                when (event) {
                    is GameEvent.HasWonEvent -> {
                        // Update UI based on the winning team
                        updateTeamScores(event.finalScore)
                    }
                    is GameEvent.ResetSetEvent -> {
                        viewModel.resetSetLog()
                    }
                    else -> { /* Ignore other events */}
                }
            }
        }

        setContent {
            VolleyballScorerTheme {
                VolleyballScorerApp(viewModel)
            }
        }
    }

    private fun updateTeamScores(entry: ScoreEntry) {
        viewModel.updateScoreLog(entry)
    }
}




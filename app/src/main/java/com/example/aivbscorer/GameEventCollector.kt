package com.example.aivbscorer

import com.example.aivbscorer.data.ScoreEntry
import kotlinx.coroutines.flow.FlowCollector

class GameEventCollector : FlowCollector<GameEvent> {
    override suspend fun emit(value: GameEvent) {
        when (value) {
            is GameEvent.HasWonEvent -> {
                updateTeamScores(value.finalScore)
            }

            is GameEvent.ResetSetEvent -> {
                GameViewModel.resetSetLog()
            }
        }
    }

    private fun updateTeamScores(entry: ScoreEntry) {
        GameViewModel.updateScoreLog(entry)
    }
}
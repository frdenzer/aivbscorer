package com.example.aivbscorer.eventing

import com.example.aivbscorer.GameViewModel
import kotlinx.coroutines.flow.FlowCollector

class GameEventCollector : FlowCollector<GameEvent> {
    override suspend fun emit(value: GameEvent) = when (value) {
        is GameEvent.HasWonEvent -> {
            GameViewModel.updateSetLogBook(value.finalScore)
        }
    }
}
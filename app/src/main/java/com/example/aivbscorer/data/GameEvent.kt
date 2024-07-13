package com.example.aivbscorer.data

sealed class GameEvent {
    data class HasWonEvent(val finalScore: ScoreEntry)
        : GameEvent()

    data object ResetSetEvent
        : GameEvent()
}
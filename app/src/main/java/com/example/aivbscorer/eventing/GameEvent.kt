package com.example.aivbscorer.eventing

import com.example.aivbscorer.data.ScoreEntry

sealed class GameEvent {
    data class HasWonEvent(val finalScore: ScoreEntry)
        : GameEvent()
}
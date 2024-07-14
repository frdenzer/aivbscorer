package com.example.aivbscorer.eventing

import com.example.aivbscorer.data.ScoreEntry

sealed class GameEvent {
    data class WinEvent(val finalScore: ScoreEntry)
        : GameEvent()
}
package com.example.aivbscorer.eventing

import com.example.aivbscorer.data.ScoreEntry

sealed class GameEvent {
    data class WonGame(val finalScore: ScoreEntry) : GameEvent()
}
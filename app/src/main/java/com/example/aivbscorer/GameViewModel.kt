package com.example.aivbscorer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class GameViewModel : ViewModel() {
    private val _gameEvents = MutableSharedFlow<GameEvent>()
    val gameEvents = _gameEvents.asSharedFlow()

    private val _scoreLog = MutableStateFlow<List<ScoreEntry>>(emptyList())
    val scoreLog = _scoreLog.asStateFlow()

    fun onSetWon(finalScore: ScoreEntry) {
        viewModelScope.launch {
            _gameEvents.emit(GameEvent.HasWonEvent(finalScore))
        }
    }

    // MatchScoreLog instance needs to get informed that a result needs to be stored
    fun updateScoreLog(entry: ScoreEntry) {
        viewModelScope.launch {
            val updatedLog = _scoreLog.value.toMutableList().apply {
                add(entry)
            }
            _scoreLog.value = updatedLog
        }
    }
}

sealed class GameEvent {
    data class HasWonEvent(val finalScore: ScoreEntry)
        : GameEvent()
}
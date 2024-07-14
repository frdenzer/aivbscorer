package com.example.aivbscorer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aivbscorer.data.ScoreEntry
import com.example.aivbscorer.data.Team
import com.example.aivbscorer.eventing.GameEvent
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

object GameViewModel : ViewModel() {
    lateinit var teamA: Team
    lateinit var teamB: Team

    private val _gameEvents = MutableSharedFlow<GameEvent>()
    val gameEvents = _gameEvents.asSharedFlow()

    private val _setLogBook = MutableStateFlow<List<ScoreEntry>>(emptyList())
    val setLog = _setLogBook.asStateFlow()

    fun onSetWon(finalScore: ScoreEntry) {
        viewModelScope.launch {
            _gameEvents.emit(GameEvent.HasWonEvent(finalScore))
        }
    }

    fun resetSetLog() {
            _setLogBook.value = emptyList()
            teamA.teamSetsWon = 0
            teamB.teamSetsWon = 0
    }

    // MatchSetLogBook instance needs to get informed that a result needs to be stored
    fun updateSetLogBook(entry: ScoreEntry) {
        viewModelScope.launch {
            val updatedLog = _setLogBook.value.toMutableList().apply {
                add(0, entry) // top of list for better displaying
            }
            _setLogBook.value = updatedLog
        }
    }

    fun resetApp() {
        teamA.teamScore = 0
        teamB.teamScore = 0
        resetSetLog()
    }
}


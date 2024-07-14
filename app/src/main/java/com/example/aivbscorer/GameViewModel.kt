package com.example.aivbscorer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aivbscorer.data.Constants.ONE
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

    private val _logBook = MutableStateFlow<List<ScoreEntry>>(emptyList())
    val getLogBook = _logBook.asStateFlow()

    fun onSetWon(finalScore: ScoreEntry) {
        viewModelScope.launch {
            _gameEvents.emit(GameEvent.HasWonEvent(finalScore))
        }
    }

    fun resetSetLog() {
        _logBook.value = emptyList()
        teamA.teamSetsWon = 0
        teamB.teamSetsWon = 0
    }

    // MatchSetLogBook instance needs to get informed that a result needs to be stored
    fun updateSetLogBook(entry: ScoreEntry) {
        viewModelScope.launch {
            val updatedLog = _logBook.value.toMutableList().apply {
                add(entry) // top of list for better displaying
            }
            _logBook.value = updatedLog
        }
    }

    fun resetApp() {
        teamA.teamScore = 0
        teamB.teamScore = 0
        resetSetLog()
    }

    fun looper(
        abbreviate: Boolean = false,
        addItem: (Int, ScoreEntry) -> Unit,
    ) {
        val maxIndex = getLogBook.value.size - 1

        // In abbreviated log, only show the last two entries. This is not the default.
        // get only [maxIndex, maxIndex - 1], i.e. the highest two entries
        val end = if (abbreviate) maxIndex - ONE else 0
        val safeEnd = maxOf(end, 0) // Prevents negative index

        for (index in maxIndex downTo safeEnd) {
            addItem(index + 1, getLogBook.value[index])
        }
    }
}


package com.example.aivbscorer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aivbscorer.data.BlueTeam
import com.example.aivbscorer.data.RedTeam
import com.example.aivbscorer.data.ScoreEntry
import com.example.aivbscorer.eventing.GameEvent
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

object GameViewModel : ViewModel() {

    private val _events = MutableSharedFlow<GameEvent>()
    val events = _events.asSharedFlow()

    val logbook = MutableStateFlow<List<ScoreEntry>>(emptyList())
    val hasLogEntries: Boolean
        get() = logbook.value.isNotEmpty()

    fun onSetWon(finalScore: ScoreEntry) {
        viewModelScope.launch {
            _events.emit(GameEvent.WonGame(finalScore))
        }
    }

    fun updateSetLogbook(entry: ScoreEntry) {
        viewModelScope.launch {
            val updatedLog = logbook.value.toMutableList().apply {
                add(entry)
            }
            logbook.value = updatedLog
        }
    }

    fun resetSetLog() {
        logbook.value = emptyList()
        RedTeam.reset()
        BlueTeam.reset()
    }
}


package com.example.aivbscorer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aivbscorer.data.Constants.TWO
import com.example.aivbscorer.data.Referee
import com.example.aivbscorer.data.ScoreEntry
import com.example.aivbscorer.data.Team
import com.example.aivbscorer.eventing.GameEvent
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

object GameViewModel : ViewModel() {
    private lateinit var teamA: Team
    private lateinit var teamB: Team
    lateinit var referee: Referee

    fun initialize(a: Team, b: Team) {
        teamA = a
        teamB = b
        Referee.initialize(a, b, this::onSetWon)
        referee = Referee
    }

    private val _events = MutableSharedFlow<GameEvent>()
    val events = _events.asSharedFlow()

    private val _logbook = MutableStateFlow<List<ScoreEntry>>(emptyList())
    val hasLogEntries: Boolean
        get() = _logbook.value.isNotEmpty()
    fun clearLog() {
        _logbook.value = emptyList()
    }

    private fun onSetWon(finalScore: ScoreEntry) {
        viewModelScope.launch {
            _events.emit(GameEvent.WonGame(finalScore))
        }
    }

    // MatchSetLogbook instance needs to get informed that a result needs to be stored
    fun updateSetLogbook(entry: ScoreEntry) {
        viewModelScope.launch {
            val updatedLog = _logbook.value.toMutableList().apply {
                add(entry) // top of list for better displaying
            }
            _logbook.value = updatedLog
        }
    }

    fun resetSetLog() {
        _logbook.value = emptyList()
        viewModelScope.launch {
            _events.emit(GameEvent.ResetLog)
        }
    }

    fun logbookOfSets(
        abbreviate: Boolean = false,
        addItem: (Int, ScoreEntry) -> Unit,
    ) = looper(abbreviate).forEach { (index, entry) ->
        addItem(index, entry)
    }

    private fun looper(
        abbreviate: Boolean = false,
    ): List<Pair<Int, ScoreEntry>> {
        val maxIndex = _logbook.value.size - 1

        // In abbreviated log, only show the last two entries. This is not the default.
        // get only [maxIndex, maxIndex - 1], i.e. the highest two entries
        val earlyEnd = if (abbreviate) maxIndex - TWO else 0
        val safeEnd = maxOf(earlyEnd, 0) // Prevents negative index

        return (maxIndex downTo safeEnd).map { index ->
            index + 1 to _logbook.value[index]
        }
    }
}


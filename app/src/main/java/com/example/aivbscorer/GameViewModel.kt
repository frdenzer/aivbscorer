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

    fun initialize (a: Team, b:Team){
        teamA = a
        teamB = b
        Referee.initialize(a, b, this::onSetWon)
        referee = Referee
    }

    private val _gameEvents = MutableSharedFlow<GameEvent>()
    val gameEvents = _gameEvents.asSharedFlow()

    private val _logBook = MutableStateFlow<List<ScoreEntry>>(emptyList())
    val hasLogEntries: Boolean
        get() = _logBook.value.isNotEmpty()

    private fun onSetWon(finalScore: ScoreEntry) {
        viewModelScope.launch {
            _gameEvents.emit(GameEvent.WinEvent(finalScore))
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

    fun logBookOfSets(
        abbreviate: Boolean = false,
        addItem: (Int, ScoreEntry) -> Unit,
    ) = looper(abbreviate).forEach { (index, entry) ->
        addItem(index, entry)
    }

    private fun looper(
        abbreviate: Boolean = false,
    ): List<Pair<Int, ScoreEntry>> {
        val maxIndex = _logBook.value.size - 1

        // In abbreviated log, only show the last two entries. This is not the default.
        // get only [maxIndex, maxIndex - 1], i.e. the highest two entries
        val earlyEnd = if (abbreviate) maxIndex - TWO else 0
        val safeEnd = maxOf(earlyEnd, 0) // Prevents negative index

        return (maxIndex downTo safeEnd).map { index ->
            index + 1 to _logBook.value[index]
        }
    }
}


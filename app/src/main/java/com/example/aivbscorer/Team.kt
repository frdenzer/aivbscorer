package com.example.aivbscorer

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color

data class Team(
    var teamColor: Color, var opponent: Team?, private val sendHasWonEvent: (ScoreEntry) -> Unit
) {
    private var _teamScore by mutableIntStateOf(0)
    var teamScore: Int = 0
        get() = _teamScore
        private set

    private var _teamSetsWon by mutableIntStateOf(0)
    var teamSetsWon: Int = 0
        get() = _teamSetsWon
        private set

    fun closeSetSavingScore() {
        _teamSetsWon += 1
        sendHasWonEvent(ScoreEntry(_teamScore, opponent?._teamScore ?: 0))
        resetScore()
        opponent?.resetScore()
    }

    fun score() {
        _teamScore += 1
        checkWinConditions()
    }

    private fun checkWinConditions() {
        val scoreLead = _teamScore - (opponent?._teamScore ?: 0)
        if (_teamScore >= 25 && scoreLead > 1) closeSetSavingScore()
    }

    fun decrementScore() {
        if (_teamScore < 1) return
        _teamScore -= 1
        opponent?.checkWinConditions()
    }

    private fun resetScore() {
        _teamScore = 0
    }

    fun resetAllCountersForBothTeams() {
        _teamScore = 0
        _teamSetsWon = 0
        opponent?.resetScore()
    }
}
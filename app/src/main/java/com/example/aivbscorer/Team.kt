package com.example.aivbscorer

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color

data class Team(
    var colorId: Color, var opponent: Team?, private val sendHasWonEvent: (ScoreEntry) -> Unit
) {
    private var _teamScore by mutableIntStateOf(0)
    val teamScore: Int
        get() = _teamScore

    private var _teamSetsWon by mutableIntStateOf(0)
    val teamSetsWon: Int
        get() = _teamSetsWon

    fun closeSetSavingScore() {
        opponent?.let {
            // if (teamScore + it.teamScore < 1) return // checked in UI
            sendHasWonEvent(ScoreEntry(colorId, teamScore, it.colorId, it.teamScore))
            it.resetScore()
        } ?: throw IllegalStateException("Opponent is not set")

        _teamSetsWon += 1
        resetScore()
    }

    fun score() {
        _teamScore += 1
        checkWinConditions()
    }

    private fun checkWinConditions() {
        opponent?.let {
            val scoreLead = _teamScore - (it._teamScore)
            if (_teamScore >= 25 && scoreLead > 1) closeSetSavingScore()
        }
    }

    fun decrementScore() {
        opponent?.let {
            _teamScore -= 1
            it.checkWinConditions()
        } ?: throw IllegalStateException("Opponent is not set")
    }

    private fun resetScore() {
        _teamScore = 0
    }

    fun resetAllCountersForBothTeams() {
        opponent?.resetScore() ?: throw IllegalStateException("Opponent is not set")
        _teamScore = 0
        _teamSetsWon = 0
    }
}
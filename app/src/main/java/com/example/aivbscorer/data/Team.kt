package com.example.aivbscorer.data

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color

data class Team(
    var colorId: Color,
) {
    private var _teamScore by mutableIntStateOf(0)
    var teamScore: Int
        get() = _teamScore
        set(value) = run { _teamScore = value }

    private var _teamSetsWon by mutableIntStateOf(0)
    var teamSetsWon: Int
        get() = _teamSetsWon
        set(value) = run { _teamSetsWon = value }
}

object Referee {
    private lateinit var homeTeam: Team
    private lateinit var opponent: Team
    lateinit var sendWinEvent: (ScoreEntry) -> Unit

    fun initialize(a: Team, b: Team, eventHandler: (ScoreEntry) -> Unit,) {
        homeTeam = a
        opponent = b
        sendWinEvent = eventHandler
    }

    fun score(who: Team): () -> Unit = {
        who.teamScore += 1
        checkWinConditions()
    }

    fun decrementScore(who: Team): () -> Unit = {
        who.teamScore -= 1
        checkWinConditions()
    }

    private fun checkWinConditions(a: Team = homeTeam, b: Team = opponent) {
        val scoreLeadA = a.teamScore - (b.teamScore)
        if (a.teamScore >= 25 && scoreLeadA > 1) closeSetSavingScore()() // actually call inner function

        // inverse
        val scoreLeadB = b.teamScore - opponent.teamScore
        if (b.teamScore >= 25 && scoreLeadB > 1) closeSetSavingScore(b)() // actually call inner function
    }

    internal fun closeSetSavingScore(
        who: Team = this.homeTeam,

    ): () -> Unit = {
        // no matter who won, we need to store the(nonsensical) results.
        sendWinEvent(
            ScoreEntry(
                homeTeam.colorId, homeTeam.teamScore, opponent.colorId, opponent.teamScore
            )
        )

        if (homeTeam == who) {
            homeTeam.teamScore = 0
            homeTeam.teamSetsWon += 1
            opponent.teamScore = 0
        } else {
            opponent.teamScore = 0
            opponent.teamSetsWon += 1
            homeTeam.teamScore = 0
        }
    }
}
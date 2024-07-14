package com.example.aivbscorer.data

import com.example.aivbscorer.GameViewModel

object Referee {
    val sendWinEvent: (ScoreEntry) -> Unit = GameViewModel::onSetWon

    fun score(who: Team): () -> Unit = {
        who.teamScore += 1
        checkWinConditions()
    }

    fun decrementScore(who: Team): () -> Unit = {
        who.teamScore -= 1
        checkWinConditions()
    }

    private fun checkWinConditions() {
        val scoreLeadA = RedTeam.teamScore - (BlueTeam.teamScore)
        if (RedTeam.teamScore >= 25 && scoreLeadA > 1) {
            closeSetSavingScore()()
            return
        }

        // inverse. happens on decrementing points.
        val scoreLeadB = BlueTeam.teamScore - RedTeam.teamScore
        if (BlueTeam.teamScore >= 25 && scoreLeadB > 1) closeSetSavingScore(BlueTeam)()
    }

    internal fun closeSetSavingScore(
        who: Team = RedTeam,

        ): () -> Unit = {
        // no matter who won, we need to store the results.
        sendWinEvent(
            ScoreEntry(
                RedTeam.colorId, RedTeam.teamScore, BlueTeam.colorId, BlueTeam.teamScore
            )
        )

        if (RedTeam == who) {
            RedTeam.teamScore = 0
            RedTeam.teamSetsWon += 1
            BlueTeam.teamScore = 0
        } else {
            BlueTeam.teamScore = 0
            BlueTeam.teamSetsWon += 1
            RedTeam.teamScore = 0
        }
    }
}
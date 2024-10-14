package com.example.aivbscorer.data

import com.example.aivbscorer.GameViewModel

object Referee {
    val sendWinEvent: (ScoreEntry) -> Unit = GameViewModel::onSetWon

    fun score(who: Team): () -> Unit = {
        who.teamScore += 1
    }

    fun decrementScore(who: Team): () -> Unit = {
        who.teamScore -= 1
    }

    internal fun closeSetSavingScore(
        ): () -> Unit = {
        // no matter who won, we need to store the results.
        sendWinEvent(
            ScoreEntry(
                RedTeam.colorId, RedTeam.teamScore, BlueTeam.colorId, BlueTeam.teamScore
            )
        )

            RedTeam.teamScore = 0
            BlueTeam.teamScore = 0
    }
}
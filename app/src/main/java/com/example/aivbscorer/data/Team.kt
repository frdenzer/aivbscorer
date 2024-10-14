package com.example.aivbscorer.data

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color

object RedTeam : Team(Color.Red)
object BlueTeam : Team(Color.Blue)

open class Team(
    val colorId: Color,
) {
    fun reset() {
        teamScore = 0
    }

    private var _teamScore by mutableIntStateOf(0)
    var teamScore: Int
        get() = _teamScore
        set(value) {
            _teamScore = value
        }
}


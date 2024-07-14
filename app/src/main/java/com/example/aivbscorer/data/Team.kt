package com.example.aivbscorer.data

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color

data class Team(
    val colorId: Color,
    val _teamScore: Int = 0,
    val _teamSetsWon: Int = 0,
) {
    private var __teamScore by mutableIntStateOf(_teamScore)
    var teamScore: Int
        get() = __teamScore
        set(value) {
            __teamScore = value
        }

    private var __teamSetsWon by mutableIntStateOf(_teamSetsWon)
    var teamSetsWon: Int
        get() = __teamSetsWon
        set(value) {
            __teamSetsWon = value
        }
}


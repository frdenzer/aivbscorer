package com.example.aivbscorer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.aivbscorer.components.TeamScoreColumn
import com.example.aivbscorer.data.BlueTeam
import com.example.aivbscorer.data.RedTeam

@Preview
@Composable
internal fun BigNumbers() {
    val wide = Modifier.fillMaxWidth()
    Row(
        modifier = wide,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        TeamScoreColumn(
            RedTeam,
            wide.background(RedTeam.colorId).weight(1f)
        )
        TeamScoreColumn(
            BlueTeam,
            wide.background(BlueTeam.colorId).weight(1f)
        )
    }
}

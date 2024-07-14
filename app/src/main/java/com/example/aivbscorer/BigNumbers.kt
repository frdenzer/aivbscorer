package com.example.aivbscorer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.aivbscorer.components.TeamScoreColumn
import com.example.aivbscorer.data.BlueTeam
import com.example.aivbscorer.data.RedTeam

@Preview(heightDp = 200, widthDp = 400)
@Preview(heightDp = 400, widthDp = 200)
@Composable
internal fun BigNumbers() {
    val wide = Modifier.fillMaxWidth()
    BoxWithConstraints {
        if (maxWidth > maxHeight) {
            Row(
                modifier = wide,
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                TeamScoreColumn(
                    RedTeam,
                    Modifier
                        .background(RedTeam.colorId)
                        .weight(1f)
                )
                TeamScoreColumn(
                    BlueTeam,
                    Modifier
                        .background(BlueTeam.colorId)
                        .weight(1f)
                )
            }
            return@BoxWithConstraints
        }
        Column(
            modifier = wide,
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            TeamScoreColumn(
                RedTeam,
                wide
                    .background(RedTeam.colorId)
                    .weight(1f)
            )
            TeamScoreColumn(
                BlueTeam,
                wide
                    .background(BlueTeam.colorId)
                    .weight(1f)
            )
        }
    }
}

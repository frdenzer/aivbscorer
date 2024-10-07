package com.example.aivbscorer

import android.content.res.Configuration
import android.view.Surface
import android.view.WindowManager
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat.getSystemService
import com.example.aivbscorer.components.TeamScoreColumn
import com.example.aivbscorer.data.BlueTeam
import com.example.aivbscorer.data.RedTeam

@Preview(heightDp = 200, widthDp = 400)
@Preview(heightDp = 400, widthDp = 200)
@Composable
fun BigNumbersPreview() {
    BigNumbers(modifier = Modifier.fillMaxWidth())
}

@Composable
internal fun BigNumbers(modifier: Modifier) {
    val context = LocalContext.current
    val configuration = LocalConfiguration.current
    val windowManager = getSystemService(context, WindowManager::class.java)
    val rotation = windowManager?.defaultDisplay?.rotation
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
    val isReverseLandscape = isLandscape && (rotation == Surface.ROTATION_270)

    val wide = Modifier.fillMaxWidth()
    BoxWithConstraints(modifier = modifier) {
        if (this.maxWidth > this.maxHeight) {
            Row(
                modifier = wide,
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                if (!isReverseLandscape) {
                    TeamScoreColumn(
                        RedTeam,
                        modifier
                            .background(RedTeam.colorId)
                            .weight(1f)
                    )
                }
                TeamScoreColumn(
                    BlueTeam,
                    modifier
                        .background(BlueTeam.colorId)
                        .weight(1f)
                )
                if (isReverseLandscape) {
                    TeamScoreColumn(
                        RedTeam,
                        modifier
                            .background(RedTeam.colorId)
                            .weight(1f)
                    )
                }
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
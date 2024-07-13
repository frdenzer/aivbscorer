package com.example.aivbscorer.navigation

import AbbreviatedSetLog
import ScoreLogScreen
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.aivbscorer.GameViewModel
import com.example.aivbscorer.ui.VolleyballScorerApp
import com.example.aivbscorer.ui.theme.VolleyballScorerTheme

@Composable
fun AppNavigation(viewModel: GameViewModel) {
    val navController = rememberNavController()
    VolleyballScorerTheme {
        NavHost(navController = navController, startDestination = "main") {
            composable("main") {
                VolleyballScorerApp(viewModel, navController)
            }
            composable("scoreLog") { ScoreLogScreen(viewModel, navController) }
        }
    }
}
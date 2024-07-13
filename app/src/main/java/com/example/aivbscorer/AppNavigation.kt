package com.example.aivbscorer

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.aivbscorer.theme.VolleyballScorerTheme

@Composable
fun AppNavigation(viewModel: GameViewModel) {
    val navController = rememberNavController()
    VolleyballScorerTheme {
        NavHost(navController = navController, startDestination = "main") {
            composable("main") {
                ScoringScreen(viewModel, navController)
            }
            composable("scoreLog") { ScoreLogScreen(viewModel, navController) }
        }
    }
}
package com.example.aivbscorer

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.aivbscorer.theme.VolleyballScorerTheme

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    VolleyballScorerTheme {
        NavHost(navController = navController, startDestination = Routes.ScoringScreen.name) {
            composable(Routes.ScoringScreen.name) { ScoringScreen(navController) }
            composable(Routes.SetLogBookScreen.name) { SetLogBookScreen(navController) }
        }
    }
}

enum class Routes(val deutsch: String) {
    ScoringScreen("Aktuelles Spiel"),
    SetLogBookScreen("Abgeschlossene Sätze")
}
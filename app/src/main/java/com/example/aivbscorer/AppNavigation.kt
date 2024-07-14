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
        NavHost(navController = navController, startDestination = Routes.LaufendesSpiel.name) {
            composable(Routes.LaufendesSpiel.name) { ScoringScreen(navController) }
            composable(Routes.Logbuch.name) { LogBookScreen(navController) }
        }
    }
}

enum class Routes(val deutsch: String) {
    LaufendesSpiel("Aktuelles Spiel"),
    Logbuch("Abgeschlossene Sätze")
}
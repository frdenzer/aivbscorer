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
            composable(Routes.ScoringScreen.name) { ScoringScreen() }
            composable(Routes.SetLogBookScreen.name) { SetLogBookScreen() }
        }
    }
}

enum class Routes {
    ScoringScreen,
    SetLogBookScreen
}
package com.example.aivbscorer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.aivbscorer.eventing.GameEventCollector
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private val viewModel: GameViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launch {
            viewModel.events.collect(GameEventCollector()::emit)
        }

        setContent {
            AppNavigation()
        }
    }
}

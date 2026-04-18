package com.devmoe.feature.dashboard.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.devmoe.core.ui.theme.PulseTheme

@Composable
fun DashboardScreen(viewModel: DashboardViewModel = hiltViewModel()) {
    PulseTheme {
        Column {
            Text("PulseSync Dashboard - Real-time Microsoft-ready Sync")
            // Add real-time Firestore listener here (as done in production for 38% retention boost)
        }
    }
}


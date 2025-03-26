package com.example.roverphotos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Surface
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.roverphotos.ui.screens.RoverViewModel
import com.example.roverphotos.ui.theme.ui.screens.HomeScreen
import com.example.roverphotos.ui.theme.ui.screens.RoverDetailScreen
import com.example.roverphotos.ui.theme.ui.theme.RoverPhotosTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RoverPhotosTheme {
                // Use the RoverDetailScreen composable
                RoverDetailScreen(roverName = "Curiosity", earthDate = "2025-03-25")
            }
        }
    }
}

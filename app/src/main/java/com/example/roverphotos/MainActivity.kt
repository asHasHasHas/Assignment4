package com.example.roverphotos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Surface
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.marsphotos.ui.screens.RoverViewModel
import com.example.roverphotos.ui.theme.ui.screens.HomeScreen
import com.example.roverphotos.ui.theme.ui.theme.RoverPhotosTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            RoverPhotosTheme {
                Surface {
                    val viewModel: RoverViewModel = viewModel()
                    HomeScreen(roverUiState = viewModel.roverUiState)
                }
            }
        }
    }
}

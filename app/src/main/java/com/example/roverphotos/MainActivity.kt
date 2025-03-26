package com.example.roverphotos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.roverphotos.ui.screens.RoverViewModel
import com.example.roverphotos.ui.theme.ui.screens.HomeScreen
import com.example.roverphotos.ui.theme.ui.screens.RoverDetailScreen
import com.example.roverphotos.ui.theme.ui.theme.RoverPhotosTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RoverPhotosTheme {
                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = "home") {
                    composable("home") {
                        val viewModel: RoverViewModel = viewModel()
                        HomeScreen(
                            viewModel = viewModel,
                            onRoverClick = { rover ->
                                navController.navigate("detail/${rover.name}/2025-03-25")
                            }
                            )
                            onRoverClick = { rover ->
                                navController.navigate("detail/${rover.name}/2025-03-25")
                            }
                    }

                    composable("detail/{roverName}/{earthDate}") { backStackEntry ->
                        val roverName = backStackEntry.arguments?.getString("roverName") ?: ""
                        val earthDate = backStackEntry.arguments?.getString("earthDate") ?: ""
                        RoverDetailScreen(navController, roverName, earthDate)
                    }
                }
            }
        }
    }
}

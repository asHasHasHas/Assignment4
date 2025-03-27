package com.example.roverphotos.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.roverphotos.ui.screens.RoverViewModel
import com.example.roverphotos.ui.theme.ui.screens.HomeScreen
import com.example.roverphotos.ui.theme.ui.screens.RoverDetailScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RoverAppBar(
    currentScreen: String,
    navController: NavController,
    navigateUp: () -> Unit,
    modifier: Modifier
) {
    val canNavigateBack = navController.previousBackStackEntry != null
    TopAppBar(
        title = { Text("RoverApp") },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.secondary
        ),
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowUp,
                        contentDescription = "Go Back"
                    )
                }
            }
        }
    )
}
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun RoverNavigation() {
    val navController = rememberNavController()
    val roverViewModel: RoverViewModel = viewModel()
    roverViewModel.getData()

    Scaffold(
        topBar = {
            RoverAppBar(
                currentScreen = "Rover Display App",
                navController = navController,

                navigateUp = { navController.navigateUp() },
                modifier = Modifier
            )
        }
    ) { innerPadding ->
        NavHost(navController = navController,
            startDestination = AppScreens.HomeScreen.name,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)) {
            composable(AppScreens.HomeScreen.name) {

                HomeScreen(roverViewModel, navController)
            }

            composable(AppScreens.RoverDetailScreen.name + "/{roverId}",
                arguments = listOf(navArgument("roverId") { type = NavType.StringType })
            ) { backStackEntry ->
                RoverDetailScreen(
                    navController = navController,roverViewModel,
                    backStackEntry.arguments?.getString("roverId"),
                )
            }
        }
    }
}
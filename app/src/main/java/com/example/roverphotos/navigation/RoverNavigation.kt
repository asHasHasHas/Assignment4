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
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.roverphotos.ui.theme.ui.screens.HomeScreen
import com.example.roverphotos.ui.theme.ui.screens.RoverDetailScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RoverAppBar(
    currentScreen: String,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
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

    Scaffold(
        topBar = {
            val currentScreen = AppScreens.fromRoute(
                navController.currentBackStackEntry?.destination?.route
            )
            RoverAppBar(
                currentScreen = currentScreen.name,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() }
            )
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = AppScreens.HomeScreen.name,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            composable(AppScreens.HomeScreen.name) {
                HomeScreen(navController = navController)
            }

            composable(
                route = AppScreens.RoverDetailScreen.name + "/{roverId}",
                arguments = listOf(navArgument("roverId") { type = NavType.StringType })
            ) { backStackEntry ->
                RoverDetailScreen(
                    navController = navController,
                    roverId = backStackEntry.arguments?.getString("roverId")
                )
            }
        }
    }
}
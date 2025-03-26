package com.example.roverphotos.ui.theme.ui.screens

import android.annotation.SuppressLint
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.navigation.NavController
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.CircularProgressIndicator
import coil.compose.rememberAsyncImagePainter
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.Image
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.roverphotos.ui.screens.RoverViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RoverDetailScreen(navController: NavController, roverName: String, earthDate: String) {
    val viewModel: RoverViewModel = viewModel()

    // Fetch rover data when the screen is first shown
    LaunchedEffect(roverName, earthDate) {
        viewModel.getRoverPhoto(roverName, earthDate)
    }

    // Observe state from the ViewModel
    val roverPhoto = viewModel.roverPhoto.value
    val isLoading = viewModel.isLoading.value
    val errorMessage = viewModel.errorMessage.value

    // Scaffold with TopAppBar and Back Button
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Rover Detail")
                },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
            )
        }
    ) {
        // Content of RoverDetailScreen
        if (isLoading) {
            CircularProgressIndicator(modifier = Modifier.fillMaxSize())
        } else if (errorMessage != null) {
            // Show error message
            Text(text = errorMessage, color = Color.Red)
        } else if (roverPhoto != null) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Image section
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = MaterialTheme.shapes.medium
                ) {
                    Image(
                        painter = rememberAsyncImagePainter(roverPhoto.imgSrc),
                        contentDescription = "Rover photo",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp)
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Rover details section
                Card(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(text = "Rover Name: ${roverPhoto.rover.name}", style = MaterialTheme.typography.titleMedium)
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(text = "Camera: ${roverPhoto.camera.fullName}", style = MaterialTheme.typography.bodyMedium)
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(text = "Launch Date: ${roverPhoto.rover.launchDate}", style = MaterialTheme.typography.bodyMedium)
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(text = "Photo Taken (Earth Date): ${roverPhoto.earthDate}", style = MaterialTheme.typography.bodyMedium)
                    }
                }
            }
        }
    }
}
package com.example.roverphotos.ui.theme.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.example.roverphotos.ui.screens.RoverViewModel

@Composable
fun RoverDetailScreen(roverName: String, earthDate: String) {
    // Using ViewModel in a Composable function
    val viewModel: RoverViewModel = viewModel() // This retrieves the ViewModel instance

    // Fetch rover data when the screen is first shown
    LaunchedEffect(roverName, earthDate) {
        viewModel.getRoverPhoto(roverName, earthDate)
    }

    // Observe state from the ViewModel
    val roverPhoto = viewModel.roverPhoto.value
    val isLoading = viewModel.isLoading.value
    val errorMessage = viewModel.errorMessage.value

    // Show loading indicator
    if (isLoading) {
        CircularProgressIndicator(modifier = Modifier.fillMaxSize())
    } else if (errorMessage != null) {
        // Show error message
        Text(text = errorMessage, color = Color.Red)
    } else if (roverPhoto != null) {
        // Show the rover photo and details
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
                    Text(text = "Camera: ${roverPhoto.camera.fullName}", style = MaterialTheme.typography.labelSmall)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = "Launch Date: ${roverPhoto.rover.launchDate}", style = MaterialTheme.typography.labelSmall)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = "Photo Taken (Earth Date): ${roverPhoto.earthDate}", style = MaterialTheme.typography.labelMedium)
                }
            }
        }
    }
}
package com.example.roverphotos.ui.theme.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.roverphotos.model.RoverPhoto
import com.example.roverphotos.ui.screens.RoverViewModel

@Composable
fun RoverDetailScreen(viewModel: RoverViewModel) {
    val roverPhotos by viewModel.roverPhotos.observeAsState(emptyList())

    // Call to get data when the screen is first displayed
    LaunchedEffect(Unit) {
        viewModel.getData()
    }

    // LazyColumn to display the rover photos in a list
    LazyColumn {
        items(roverPhotos) { photo ->
            RoverPhotoItem(photo)
        }
    }
}

@Composable
fun RoverPhotoItem(photo: RoverPhoto) {
    Row(modifier = Modifier.padding(8.dp)) {
        // Image for the rover photo
        Image(
            painter = rememberImagePainter(photo.imgSrc),
            contentDescription = null,
            modifier = Modifier.size(120.dp) // You can adjust size as needed
        )

        // Column for rover info (name, camera, earth date, rover launch date)
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Text(text = "Rover: ${photo.rover.name}", style = MaterialTheme.typography.titleLarge)
            Text(
                text = "Camera: ${photo.camera.fullName}",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "Earth Date: ${photo.earthDate}",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "Launch Date: ${photo.rover.launchDate}",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}
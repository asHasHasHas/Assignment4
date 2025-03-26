package com.example.roverphotos.ui.theme.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.roverphotos.R
import com.example.roverphotos.model.Rover
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.example.roverphotos.ui.screens.RoverViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: RoverViewModel,
    navController: NavController
){
    val roverList = viewModel.rovers.value
    val isLoading = viewModel.isLoading.value
    val errorMessage = viewModel.errorMessage.value

    LaunchedEffect(Unit) {
        viewModel.getRovers() //Grabs rover list when screen is loaded
    }

    when {
        isLoading -> CircularProgressIndicator()
        errorMessage != null -> Text(text = errorMessage, color = Color.Red)
        else -> LazyColumn(modifier = modifier) {
            items(roverList) { rover ->
                RoverCard(
                    modifier = Modifier.width(350.dp),
                    rover = rover
            }
        }
    }
}

@Composable
fun RoverCard(
    rover: Rover,
    itemClick: (String) -> Unit = {} ) {
    Column() {
        Divider(thickness = 1.dp)
        Row(
            modifier = Modifier
                .clickable {
                    itemClick(rover.name)
                }
        ) {
            Image(
                painter = painterResource(id = R.drawable.rover),
                contentDescription = "Picture of a rover",
            )
            Column() {
                Text(
                    text = rover.name
                )
                Text(
                    text = rover.launchDate
                )
            }
        }
        Divider(thickness = 1.dp)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHomeScreen() {
    val sampleRovers = listOf(
        Rover("Curiosity", "2011-11-26"),
        Rover("Opportunity", "2003-07-07"),
        Rover("Spirit", "2003-06-10")
    )

    HomeScreen(
        roverList = sampleRovers,
        onRoverClick = { rover -> println("Clicked on ${rover.name}") } // Example click action
    )
}
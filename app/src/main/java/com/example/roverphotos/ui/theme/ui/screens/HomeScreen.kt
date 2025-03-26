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
import androidx.compose.material3.Divider

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    roverList: List<Rover>,
    onRoverClick: (Rover) -> Unit
){
    LazyColumn(modifier = modifier) {
        items(roverList) { rover ->
            RoverCard(modifier = Modifier.width(350.dp), rover=rover, onClick = { onRoverClick(rover) })
        }
    }
}

@Composable
fun RoverCard(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
        .clickable{},
    rover: Rover) {
    Column() {
        Divider(thickness = 1.dp)
        Row(
            modifier = modifier,
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
package com.example.roverphotos.ui.theme.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.example.roverphotos.R
import com.example.roverphotos.model.Rover
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    roverList: List<Rover>
){
    LazyColumn(modifier = modifier) {
        items(roverList) { rover ->
            RoverCard(rover=rover)
        }
    }
}

@Composable
fun RoverCard(modifier: Modifier = Modifier, rover: Rover) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.rover),
            contentDescription = "Picture of a rover",
        )
        Row() {
            Text(
                text = rover.name
            )
            Text(
                text = rover.launchDate
            )
        }
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

    HomeScreen(roverList = sampleRovers)
}
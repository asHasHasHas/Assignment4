package com.example.roverphotos.ui.theme.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
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
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.example.roverphotos.navigation.AppScreens
import com.example.roverphotos.ui.RoverCard
import com.example.roverphotos.ui.screens.RoverViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    roverViewModel: RoverViewModel,
    navController: NavController
){
    val roverResult = roverViewModel.roverResult.observeAsState()
    val roverList = roverResult.value?.body()
    val roverListNonNullable = roverList?.filterNotNull() ?: emptyList()

    Column(modifier = Modifier.padding(12.dp)) {
       LazyColumn {
                items(roverListNonNullable) {
                    RoverCard(rover = it){ rover->
                        navController.navigate(route = AppScreens.RoverDetailScreen.name+"/$rover")
                    }}
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
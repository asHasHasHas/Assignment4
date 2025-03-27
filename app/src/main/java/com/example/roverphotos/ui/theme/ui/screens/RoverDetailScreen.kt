package com.example.roverphotos.ui.theme.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.roverphotos.ui.screens.RoverViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun RoverDetailScreen(
    navController: NavController, roverViewModel: RoverViewModel,
    roverName: String?
) {
    val roverResult = roverViewModel.roverResult.observeAsState()
    val roverList = roverResult.value?.body()
    val rover = roverList?.firstOrNull { it.id == roverName }
    val roverFiltered = roverList?.filter { rover ->
        rover.id == roverName

        Scaffold(
            topBar = {
                rover?.let {
                    AppBar(
                        currentScreen = AppScreens.DetailScreen.name,
                        navController = navController,
                        navigateUp = { navController.navigateUp() },
                        context = LocalContext.current,
                        textToShare = it.bio
                    )
                }
            }
        ) {

            Column(
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Top
            ) {
                Text(buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            color = Color.Blue,
                            fontSize = 24.sp
                        )
                    ) {
                        append("Bio: ")
                    }
                    withStyle(
                        style = SpanStyle(
                            color = Color.Blue,
                            fontSize = 20.sp
                        )
                    ) {
                        roverFiltered?.get(0)?.let { append(it.bio) }
                    }

                }, modifier = Modifier.padding(6.dp))

                HorizontalDivider(modifier = Modifier.padding(3.dp))
                Text(
                    text = "Director: ${roverFiltered?.get(0)?.team}",
                    style = MaterialTheme.typography.titleLarge
                )
                Text(
                    text = "Actors: ${roverFiltered?.get(0)?.publisher}",
                    style = MaterialTheme.typography.titleLarge
                )
                Text(
                    text = "Rating: ${roverFiltered?.get(0)?.createdby}",
                    style = MaterialTheme.typography.titleLarge
                )


            }
        }


    }
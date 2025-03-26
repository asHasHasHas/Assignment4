package com.example.roverphotos.navigation

enum class AppScreens {
    HomeScreen,
    RoverDetailScreen;
    companion object {
        fun fromRoute (route: String?): AppScreens
        = when(route?.substringBefore("/"))
        {
            HomeScreen.name -> HomeScreen
            RoverDetailScreen.name -> RoverDetailScreen
            null -> HomeScreen
            else -> throw IllegalArgumentException("Route $route is not recognized")
        }
    }
}
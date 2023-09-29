package com.example.lab07_networking.navigation

sealed class NavigationState(val route: String) {
    object Home: NavigationState("Home")
    object Events: NavigationState("Event")
    object Detail: NavigationState("Detail")
}
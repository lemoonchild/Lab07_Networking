package com.example.lab07_networking.navigation

sealed class NavigationState(val route: String) {
    object Categories: NavigationState("Categories")
    object Meals: NavigationState("meals/{categoryID}")
    object Detail: NavigationState("mealsDetail/{detailID}")
}

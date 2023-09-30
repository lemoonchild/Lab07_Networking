package com.example.lab07_networking.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.lab07_networking.categories.view.MealsCategoriesScreen
import com.example.lab07_networking.mealdetail.view.MealsDetailScreen
import com.example.lab07_networking.meals.view.MealsListScreen


@Composable
fun Navigation(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    NavHost(navController = navController,
        startDestination = NavigationState.Categories.route,
        modifier = modifier) {

        composable(route = NavigationState.Categories.route) {
            MealsCategoriesScreen(navController = navController)
        }


        composable(route = NavigationState.Meals.route) { backStackEntry ->
            val category = backStackEntry.arguments?.getString("categoryID")
            if(category != null){
                MealsListScreen(category = category,navController = navController )
            }
        }

        composable(route = NavigationState.Detail.route) { backStackEntry ->
            val category = backStackEntry.arguments?.getString("detailID")
            if(category != null){
                MealsDetailScreen(idMeal = category,navController = navController )
            }
        }
    }
}







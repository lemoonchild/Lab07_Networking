package com.example.lab07_networking.meals.viewmodel

import androidx.lifecycle.ViewModel
import com.example.lab07_networking.meals.repository.MealsRepository
import com.example.lab07_networking.networking.response.MealsCategoriesResponse


class MealsCategoriesViewModel (private val repository: MealsRepository = MealsRepository()): ViewModel() {
    fun getMeals(successCallback: (response: MealsCategoriesResponse?) -> Unit) {
        repository.getMeals { response ->
            successCallback(response)
        }
    }
}
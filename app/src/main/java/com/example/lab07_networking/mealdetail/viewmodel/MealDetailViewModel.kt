package com.example.lab07_networking.mealdetail.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lab07_networking.mealdetail.repository.MealDetailRepository
import com.example.lab07_networking.networking.response.MealInstructions
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class MealDetailViewModel(private val repository: MealDetailRepository = MealDetailRepository()) : ViewModel() {
    private val _mealDetail = MutableStateFlow<MealInstructions?>(null)
    val mealDetail: StateFlow<MealInstructions?> = _mealDetail.asStateFlow()

    fun loadMealDetail(idMeal: String) {
        viewModelScope.launch {
            repository.getMealDetail(idMeal) { mealInstructions ->
                _mealDetail.value = mealInstructions
            }
        }
    }
}

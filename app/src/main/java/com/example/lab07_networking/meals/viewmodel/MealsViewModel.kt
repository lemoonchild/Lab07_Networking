package com.example.lab07_networking.meals.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lab07_networking.meals.repository.MealsCategoryRepository
import com.example.lab07_networking.networking.response.CategoryResponse
import com.example.lab07_networking.networking.response.MealResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class MealsViewModel (private val repository: MealsCategoryRepository = MealsCategoryRepository()): ViewModel() {

    // MutableStateFlow para mantener la lista de comidas y observar cambios
    private val _meals = MutableStateFlow<List<CategoryResponse>>(emptyList())
    val meals: StateFlow<List<CategoryResponse>> = _meals.asStateFlow()

    // Función para obtener comidas por categoría y actualizar el MutableStateFlow
    fun getMealsByCategory(category: String) {
        viewModelScope.launch {
            repository.getMealsByCategory(category) { meals ->
                _meals.value = meals ?: emptyList()
            }
        }
    }

}


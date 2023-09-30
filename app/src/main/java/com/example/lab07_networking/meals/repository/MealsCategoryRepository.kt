package com.example.lab07_networking.meals.repository

import android.util.Log
import com.example.lab07_networking.networking.MealsWebService
import com.example.lab07_networking.networking.response.CategoriesResponse
import com.example.lab07_networking.networking.response.CategoryResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MealsCategoryRepository(private val webService: MealsWebService = MealsWebService()) {

    fun getMealsByCategory(
        category: String,
        successCallback: (response: List<CategoryResponse>?) -> Unit
    ) {
        webService.getMealsByCategory(category).enqueue(object : Callback<CategoriesResponse> {

            override fun onResponse(
                call: Call<CategoriesResponse>,
                response: Response<CategoriesResponse>
            ) {
                if (response.isSuccessful) {

                    val meals = response.body()?.categories?.map { it.name } ?: emptyList()

                    Log.d("MealsRepository", "Category: $category, Meals: $meals")
                    successCallback(response.body()?.categories)
                } else {
                    Log.e("MealsRepository", "Error: ${response.errorBody()}")
                }
            }

            override fun onFailure(call: Call<CategoriesResponse>, t: Throwable) {
                Log.e("MealsRepository", "Failure: ${t.message}")
            }
        })
    }
}

package com.example.lab07_networking.networking


import com.example.lab07_networking.networking.response.MealsCategoriesResponse
import retrofit2.Call
import retrofit2.http.GET

interface MealsApi {
    @GET("categories.php")
    fun getMeals(): Call<MealsCategoriesResponse>
}
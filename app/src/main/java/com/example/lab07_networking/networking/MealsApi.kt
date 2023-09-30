package com.example.lab07_networking.networking


import com.example.lab07_networking.networking.response.CategoriesResponse
import com.example.lab07_networking.networking.response.DetailResponse
import com.example.lab07_networking.networking.response.MealsCategoriesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MealsApi {
    @GET("categories.php")
    fun getMeals(): Call<MealsCategoriesResponse>

    @GET("filter.php")
    fun getMealsByCategory(@Query("c") category: String): Call<CategoriesResponse>

    @GET("lookup.php")
    fun getDetailsMeal(@Query("i") idMeal: String): Call<DetailResponse>
}

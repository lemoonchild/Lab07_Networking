package com.example.lab07_networking.networking

import com.example.lab07_networking.networking.response.CategoriesResponse
import com.example.lab07_networking.networking.response.DetailResponse
import com.example.lab07_networking.networking.response.MealsCategoriesResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.Call
import retrofit2.converter.gson.GsonConverterFactory

class MealsWebService {

    private lateinit var api: MealsApi

    init {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)

        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.themealdb.com/api/json/v1/1/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        api = retrofit.create(MealsApi::class.java)
    }

    fun getMeals(): Call<MealsCategoriesResponse> {
        return api.getMeals()
    }

    fun getMealsByCategory(category: String): Call<CategoriesResponse> {
        return api.getMealsByCategory(category)
    }

    fun getDetailsMeal(idMeal: String): Call<DetailResponse>{
        return api.getDetailsMeal(idMeal)
    }
}

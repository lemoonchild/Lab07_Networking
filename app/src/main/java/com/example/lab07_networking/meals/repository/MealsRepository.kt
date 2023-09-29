package com.example.lab07_networking.meals.repository


import android.util.Log
import com.example.lab07_networking.networking.MealsWebService
import com.example.lab07_networking.networking.response.MealsCategoriesResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MealsRepository(private val webService: MealsWebService = MealsWebService()) {
    fun getMeals(successCallback: (response: MealsCategoriesResponse?) -> Unit) {
        return webService.getMeals().enqueue(object : Callback<MealsCategoriesResponse> {
            override fun onResponse(

                call: Call<MealsCategoriesResponse>,
                response: Response<MealsCategoriesResponse>
            ) {
                if (response.isSuccessful) {
                    Log.d("MealsRepository", "Success: ${response.body()}")
                    successCallback(response.body())
                } else {
                    Log.e("MealsRepository", "Error: ${response.errorBody()}")
                }
            }

            override fun onFailure(call: Call<MealsCategoriesResponse>, t: Throwable) {
                // TODO treat failure
            }
        })
    }
}
package com.example.lab07_networking.mealdetail.repository

import android.util.Log
import com.example.lab07_networking.networking.MealsWebService
import com.example.lab07_networking.networking.response.DetailResponse
import com.example.lab07_networking.networking.response.MealInstructions
import com.example.lab07_networking.networking.response.MealsCategoriesResponse
import retrofit2.Response
import retrofit2.Call
import retrofit2.Callback

class MealDetailRepository(private val webService: MealsWebService = MealsWebService()) {

    fun getMealDetail(idMeal: String, successCallback: (response: MealInstructions?) -> Unit) {
        return webService.getDetailsMeal(idMeal).enqueue(object : Callback<DetailResponse> {

            override fun onResponse(call: Call<DetailResponse>, response: Response<DetailResponse>) {
                if (response.isSuccessful) {
                    val mealInstructions = response.body()?.meals?.firstOrNull()
                    Log.d("MealsRepository", "Response received: $response")
                    successCallback(mealInstructions)
                } else {
                    Log.e("MealDetailRepository", "Error: ${response.errorBody()}")
                }
            }

            override fun onFailure(call: Call<DetailResponse>, t: Throwable) {
                Log.e("MealDetailRepository", "Failure: ${t.message}")
            }
        })
    }
}

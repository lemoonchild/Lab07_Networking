package com.example.lab07_networking.networking.response

import com.google.gson.annotations.SerializedName


data class DetailResponse(
    @SerializedName("meals") val meals: List<MealInstructions>?
)

data class MealInstructions(
    @SerializedName("idMeal") val idMeal: String,
    @SerializedName("strMeal") val strMeal: String,
    @SerializedName("strArea") val strArea: String,
    @SerializedName("strInstructions") val strInstructions: String,
    @SerializedName("strMealThumb") val strMealThumb: String,
    @SerializedName("strYoutube") val strYoutube: String
)
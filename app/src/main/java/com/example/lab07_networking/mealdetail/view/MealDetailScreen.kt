package com.example.lab07_networking.mealdetail.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.lab07_networking.mealdetail.viewmodel.MealDetailViewModel
import com.example.lab07_networking.networking.response.MealInstructions

@Composable
fun MealsDetailScreen(idMeal: String, navController: NavController) {
    val viewModel: MealDetailViewModel = viewModel()
    val mealDetail by viewModel.mealDetail.collectAsState()

    LaunchedEffect(idMeal) {
        viewModel.loadMealDetail(idMeal)
    }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
    ) {
        // Título Superior
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFE8DAEF))
                .padding(vertical = 8.dp)
        ) {
            Text(
                text = "Details of the Meal",
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.align(Alignment.Center)
            )
        }

        // Detalles de la Comida
        mealDetail?.let { meal ->
            InfoCard(meal)
            InstructionsCard(meal)
        } ?: Text(text = "Loading...")
    }
}

@Composable
fun InfoCard(meal: MealInstructions) {
    Card(
        modifier = Modifier
            .fillMaxWidth(1f)
            .padding(8.dp)
            .border(6.dp, Color(0xFFC39BD3))
    ) {
        Column(
            modifier = Modifier
                .background(Color.White)
                .padding(8.dp)
        ) {
            Text(
                text = "Area: ${meal.strArea}",
                color = Color.Black,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            )
            Text(
                text = "Youtube: ${meal.strYoutube}",
                color = Color.Black,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            )
        }
    }
}

@Composable
fun InstructionsCard(meal: MealInstructions) {
    Card(
        modifier = Modifier
            .fillMaxWidth(1f)
            .padding(8.dp)
            .border(6.dp, Color(0xFFC39BD3))
    ) {
        Column(
            modifier = Modifier
                .background(Color.White)
                .padding(8.dp)
        ) {
            Text(
                text = "Instructions",
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            )
            Text(
                text = "${meal.strInstructions}",
                color = Color.Black,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            )
        }
    }
}




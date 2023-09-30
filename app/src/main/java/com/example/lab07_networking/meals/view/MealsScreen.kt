package com.example.lab07_networking.meals.view

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.lab07_networking.meals.viewmodel.MealsViewModel
import com.example.lab07_networking.networking.response.CategoryResponse

@Composable
fun MealsListScreen(category: String, navController: NavController) {
    val viewModel: MealsViewModel = viewModel()
    val meals by viewModel.meals.collectAsState()

    LaunchedEffect(category) {
        viewModel.getMealsByCategory(category)
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFE8DAEF))
                    .padding(vertical = 8.dp)
            ) {
                Text(
                    text = "Meals of $category",
                    style = TextStyle(
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }

        items(meals) { meal ->
            MealCard(meal, navController = navController)
        }
    }
}

@Composable
fun MealCard(meal: CategoryResponse, navController: NavController) {
    Card(
        modifier = Modifier
            .fillMaxWidth(1f)
            .height(200.dp)
            .padding(end = 8.dp)
            .border(6.dp, Color(0xFFC39BD3))
            .clickable {
                println("Card Clicked: ${meal.name}")
                navController.navigate("mealsDetail/${meal.idmeal}")

            },
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surface)
        ) {
            Image(
                painter = rememberAsyncImagePainter(model = meal.mealthumb),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
            ) {
                Text(
                    text = meal.name,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                )
            }
        }
    }
}

package com.example.lab07_networking.categories.view

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
import com.example.lab07_networking.categories.viewmodel.MealsCategoriesViewModel
import com.example.lab07_networking.meals.viewmodel.MealsViewModel
import com.example.lab07_networking.networking.response.MealResponse

@Composable
fun MealsCategoriesScreen(navController: NavController) {
    val viewModel: MealsCategoriesViewModel = viewModel()
    val rememberedMeals: MutableState<List<MealResponse>> = remember { mutableStateOf(emptyList<MealResponse>()) }

    viewModel.getMeals { response ->
        val mealsFromTheApi = response?.categories
        rememberedMeals.value = mealsFromTheApi.orEmpty()
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            // Título Superior
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFE8DAEF)) // Color de fondo para el título
                    .padding(vertical = 8.dp) // Padding vertical para dar espacio alrededor del texto
            ) {
                Text(
                    text = "Food Categories",
                    style = TextStyle(
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }

        items(rememberedMeals.value) { meal ->
            MealCard(meal, navController = navController)
        }
    }
}



@Composable
fun MealCard(meal: MealResponse, navController: NavController) {

    Card(
        modifier = Modifier
            .fillMaxWidth(1f)
            .height(200.dp)
            .padding(end = 8.dp)
            .border(6.dp, Color(0xFFC39BD3))
            .clickable {
                println("Card Clicked: ${meal.name}")
                navController.navigate("meals/${meal.name}")
            },
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surface)
        ) {
            Image(
                painter = rememberAsyncImagePainter(model = meal.imageUrl),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White) // Fondo blanco que se extiende a lo largo de toda la parte inferior
            ) {
                Text(
                    text = meal.name,
                    color = Color.Black, // Texto en color negro
                    fontWeight = FontWeight.Bold, // Texto en negrita
                    textAlign = TextAlign.Center, // Centrar el texto
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                )
            }
        }
    }
}











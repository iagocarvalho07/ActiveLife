package com.iagocarvalho.activelife.screens.workoutScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.iagocarvalho.activelife.screens.homeScreen.BottomNavigationScreen
import com.iagocarvalho.activelife.screens.homeScreen.CardTreiner
import com.iagocarvalho.activelife.screens.homeScreen.TopAppBarScren

@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WorkoutABCScreen(navController: NavController = NavController(LocalContext.current)) {
    Scaffold(topBar = {
        TopAppBarScren(
            navController = navController,
            PageTitle = "Seu Treino",
            isHomeScreen = false
        ) {
            navController.popBackStack()
        }
    }, bottomBar = { BottomNavigationScreen(navController = navController) }) { innerpadding ->
        Box(modifier = Modifier
            .padding(innerpadding)
            .fillMaxSize()
            .background(Color.Black)) {
            CardTreiner(navController = navController, isHomeScreen = false)
            Divider(thickness = 5.dp)

        }
    }

}
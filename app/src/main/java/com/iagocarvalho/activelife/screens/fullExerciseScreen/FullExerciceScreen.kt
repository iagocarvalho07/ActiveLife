package com.iagocarvalho.activelife.screens.fullExerciseScreen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.iagocarvalho.activelife.screens.homeScreen.BottomNavigationScreen
import com.iagocarvalho.activelife.screens.homeScreen.TopAppBarScren

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun FullExerciceScreen(
    navController: NavController = NavController(LocalContext.current),
    viewModel: FullExerciceScreenViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {

    val allexercises by viewModel.ExerciseDBview.observeAsState(null)
    Scaffold(topBar = {
        TopAppBarScren(
            navController = navController,
            PageTitle = "Full Exercise",
            isHomeScreen = false
        ) {
            navController.popBackStack()
        }
    }, bottomBar = { BottomNavigationScreen(navController = navController) }) { contentPadding ->
        Box(modifier = Modifier.padding(contentPadding)) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center

            ) {
                if (viewModel.ExerciseDBview.value != null) {
                    LazyColumn {
                        items(allexercises!!) {  allexercises ->
                            Log.d("TestApi", "FullExerciceScreen: chamando api  ${allexercises.name}")
                            Card() {
                            AsyncImage(model = allexercises.gifUrl, contentDescription = "")
                            Text(text = allexercises.name)
                            }
                        }
                    }
                }
            }
        }
    }
}
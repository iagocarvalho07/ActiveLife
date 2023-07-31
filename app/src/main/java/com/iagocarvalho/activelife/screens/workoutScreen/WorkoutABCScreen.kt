package com.iagocarvalho.activelife.screens.workoutScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
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
        Box(
            modifier = Modifier
                .padding(innerpadding)
                .fillMaxSize()
                .background(Color.Black)
        ) {
            Column() {
                CardTreiner(navController = navController, isHomeScreen = false)
                Divider(thickness = 2.dp)
                Card(modifier = Modifier.padding(16.dp)) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(80.dp)
                            .padding(8.dp),
                        horizontalArrangement = Arrangement.SpaceAround,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        val url =
                            "https://upload.wikimedia.org/wikipedia/commons/2/2c/Rotating_earth_%28large%29.gif"
                        AsyncImage(
                            model = url,
                            contentDescription = "",
                            modifier = Modifier
                                .height(80.dp)
                                .width(80.dp)
                        )
                        Column(
                            modifier = Modifier.fillMaxHeight(),
                            verticalArrangement = Arrangement.SpaceAround
                        ) {
                            Text(
                                text = "Nome do exercicio", style = TextStyle(
                                    fontSize = 12.sp,
                                    fontFamily = FontFamily.Serif,
                                    fontWeight = FontWeight(700),
                                    color = Color.Black,
                                )
                            )
                            Text(
                                text = "3 Repetições", style = TextStyle(
                                    fontSize = 15.sp,
                                    fontFamily = FontFamily.Serif,
                                    fontWeight = FontWeight(700),
                                    color = Color.Black,
                                )
                            )
                            Text(
                                text = "10 - 12 repetições", style = TextStyle(
                                    fontSize = 12.sp,
                                    fontFamily = FontFamily.Serif,
                                    fontWeight = FontWeight(700),
                                    color = Color.Black,
                                )
                            )

                        }
                        Column(
                            modifier = Modifier.fillMaxHeight(),
                            verticalArrangement = Arrangement.SpaceAround) {
                            Row() {
                                Text(text = "Previw")
                                Text(text = "Add New")
                            }

                            Row() {
                                Text(text = "25 Kg")
                                Text(text = "?")
                            }

                        }

                    }

                }

            }
        }
    }

}
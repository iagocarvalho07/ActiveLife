package com.iagocarvalho.activelife.screens.homeScreen

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.iagocarvalho.activelife.R
import com.iagocarvalho.activelife.constants.BannerAdView
import com.iagocarvalho.activelife.navigation.NagitaionScreens

@Preview
@Composable
fun HomeScreen(navController: NavController = NavController(LocalContext.current)) {
    ScaffoldScreen(navController = navController)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffoldScreen(
    navController: NavController,
    viewModel: HomeScreenViewModel = viewModel()
) {

    val context = LocalContext.current
    val getData = viewModel.state.value
    Log.d("dataname", "ScaffoldScreen: ${getData.display_name}")




    Scaffold(topBar = {
        TopAppBarScren(navController = navController, PageTitle = "Home") {
            viewModel.sigOut()
                .run {
                    navController.navigate(NagitaionScreens.ActiveLifeLoginScreenWithEmail.name)
                    Toast.makeText(context, "Até Breve", Toast.LENGTH_LONG).show()
                }
        }
    }, bottomBar = {
        BottomNavigationScreen(navController = navController)
    }) { contentPadding ->
        Box(modifier = Modifier.padding(contentPadding)) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top

            ) {
                BannerAdView()
                Card(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    colors = CardDefaults.cardColors(Color(0xFFE47C0E))
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {


                        Column(modifier = Modifier) {

                            Text(
                                text ="Usuario:  ${getData.display_name}", style = TextStyle(
                                    fontSize = 15.sp,
                                    fontFamily = FontFamily.Serif,
                                    fontWeight = FontWeight(700),
                                    color = Color.Black,
                                )
                            )
                            Text(
                                text = "Altura: ${getData.altura}", style = TextStyle(
                                    fontSize = 15.sp,
                                    fontFamily = FontFamily.Serif,
                                    fontWeight = FontWeight(700),
                                    color = Color.Black,
                                )
                            )
                            Text(
                                text = "Peso: ${getData.peso}", style = TextStyle(
                                    fontSize = 15.sp,
                                    fontFamily = FontFamily.Serif,
                                    fontWeight = FontWeight(700),
                                    color = Color.Black,
                                )
                            )
                        }
                        Text(
                            text = "Categoria: Disciplina", style = TextStyle(
                                fontSize = 15.sp,
                                fontFamily = FontFamily.Serif,
                                fontWeight = FontWeight(700),
                                color = Color.Black,
                            )
                        )
                        Image(imageVector = Icons.Default.Person, contentDescription = "")
                    }

                }
                CardTreiner(navController = navController)
            }
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarScren(
    navController: NavController,
    PageTitle: String,
    isHomeScreen: Boolean = true,
    action: () -> Unit
) {
    TopAppBar(title = {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            if (!isHomeScreen) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Seta Retornar Pg Anterior",
                    modifier = Modifier.clickable { action() }
                )
            }
            Text(text = PageTitle)
            if (isHomeScreen) {
                Icon(
                    imageVector = Icons.Default.ExitToApp,
                    contentDescription = "Exit To App",
                    modifier = Modifier.clickable {
                        action()
                    })
            }
        }
    }, colors = TopAppBarDefaults.largeTopAppBarColors(containerColor = Color(0xFFE47C0E)))
}

@Composable
fun BottomNavigationScreen(navController: NavController) {
    BottomNavigation(backgroundColor = Color(0xFFE47C0E)) {
        BottomNavigationItem(label = { Text(text = "Home") },
            icon = {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = ""
                )
            },
            selected = true,
            onClick = { navController.navigate(NagitaionScreens.HomeScreen.name) })
        BottomNavigationItem(label = { Text(text = "Treino") },
            icon = {
                Icon(
                    imageVector = Icons.Default.Create,
                    contentDescription = ""
                )
            },
            selected = true,
            onClick = { navController.navigate(NagitaionScreens.FullExerciceScreen.name) })
        BottomNavigationItem(label = { Text(text = "Perfil") },
            icon = {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = ""
                )
            },
            selected = true,
            onClick = { navController.navigate(NagitaionScreens.ProfileScreen.name) })
    }
}

@Composable
fun CardTreiner(navController: NavController, isHomeScreen: Boolean = true) {
    Column() {
        Card(
            modifier = Modifier
                .padding(24.dp)
                .fillMaxWidth()
                .height(200.dp),
            shape = RoundedCornerShape(40.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 40.dp),
            border = BorderStroke(1.dp, color = Color.White)
        ) {


            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Black),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top
                ) {
                    Column(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.Start,
                        verticalArrangement = Arrangement.Top
                    ) {
                        Text(
                            text = "A persistência é o caminho do êxito",
                            style = TextStyle(
                                fontSize = 15.sp,
                                fontFamily = FontFamily.Serif,
                                fontWeight = FontWeight(700),
                                color = Color.White,
                            )
                        )
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Bottom
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Column {
                                    Text(
                                        text = "Treino A / B / C / D",
                                        style = TextStyle(
                                            fontSize = 10.sp,
                                            fontFamily = FontFamily.Serif,
                                            fontWeight = FontWeight(700),
                                            color = Color.White,
                                        )
                                    )
                                    Text(
                                        text = "Total Workouts", style = TextStyle(
                                            fontSize = 10.sp,
                                            fontFamily = FontFamily.Serif,
                                            fontWeight = FontWeight(700),
                                            color = Color.White,
                                        )
                                    )
                                    Text(
                                        text = "Tempo Recomendado: 45Min - 1H 20Min", style = TextStyle(
                                            fontSize = 10.sp,
                                            fontFamily = FontFamily.Serif,
                                            fontWeight = FontWeight(700),
                                            color = Color.White,
                                        )
                                    )
                                }
                                if (isHomeScreen) {
                                    Button(
                                        onClick = { navController.navigate(NagitaionScreens.WorkoutABCScreen.name) },
                                        modifier = Modifier,
                                        colors = ButtonDefaults.buttonColors(
                                            Color(0xFFE47C0E)
                                        )
                                    ) {
                                        Text(
                                            text = "Start",
                                            style = TextStyle(
                                                fontSize = 15.sp,
                                                fontFamily = FontFamily.Serif,
                                                fontWeight = FontWeight(700),
                                                color = Color.Black,
                                            )
                                        )

                                    }
                                }
                            }
                        }
                    }
                }
                Image(
                    painter = painterResource(id = R.drawable.backgroundsrainersplash),
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    alpha = 0.4f
                )
            }

        }

    }


}
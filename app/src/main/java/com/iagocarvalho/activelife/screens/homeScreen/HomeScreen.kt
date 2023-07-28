package com.iagocarvalho.activelife.screens.homeScreen

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Person
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.iagocarvalho.activelife.navigation.NagitaionScreens

@Composable
fun HomeScreen(navController: NavController) {
    ScaffoldScreen(navController = navController)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffoldScreen(
    navController: NavController,
    viewModel: HomeScreenViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    Scaffold(topBar = {
        TopAppBarScren(navController = navController, PageTitle = "Home") {
            viewModel.sigOut()
                .run { navController.navigate(NagitaionScreens.ActiveLifeLoginScreenWithEmail.name) }
        }
    }, bottomBar = {
        BottomNavigationScreen(navController = navController)
    }) { contentPadding ->
        // Screen content
        Box(modifier = Modifier.padding(contentPadding)) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center

            ) {
                Text(
                    text = "Active Life",
                    style = TextStyle(
                        fontSize = 45.sp,
                        fontFamily = FontFamily.Serif,
                        fontWeight = FontWeight(700),
                        color = Color(0xFFE47C0E),
                    )
                )
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
                    imageVector = Icons.Default.Person,
                    contentDescription = ""
                )
            },
            selected = true,
            onClick = { navController.navigate(NagitaionScreens.HomeScreen.name) })
        BottomNavigationItem(label = { Text(text = "Treino") },
            icon = {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = ""
                )
            },
            selected = true,
            onClick = { navController.navigate(NagitaionScreens.WorkoutScreen.name) })
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

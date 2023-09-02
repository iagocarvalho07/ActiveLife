package com.iagocarvalho.activelife.screens.profileScreen

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.iagocarvalho.activelife.constants.GenericTextFild
import com.iagocarvalho.activelife.constants.SubmitButton
import com.iagocarvalho.activelife.screens.homeScreen.BottomNavigationScreen
import com.iagocarvalho.activelife.screens.homeScreen.TopAppBarScren

@Preview(showBackground = true)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(navController: NavController = NavController(LocalContext.current)) {
    Scaffold(topBar = {
        TopAppBarScren(
            navController = navController,
            PageTitle = "Profile",
            isHomeScreen = false
        ) {
            navController.popBackStack()
        }
    },
        bottomBar = { BottomNavigationScreen(navController = navController) }
    ) { contentPadding ->
        // Screen content
        Box(modifier = Modifier.padding(contentPadding)) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black),
                horizontalAlignment = Alignment.CenterHorizontally,

                ) {
                ShowProfile()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ShowProfile(
    navController: NavController = NavController(LocalContext.current),
    viewModel: ProfileScreenViewModel = viewModel()
) {
    val expande = remember { mutableStateOf(false) }
    val getDataUserInfo = viewModel.state.value
    val styleNumbers = TextStyle(
        fontSize = 20.sp,
        fontFamily = FontFamily.Serif,
        fontWeight = FontWeight(700),
        color = Color(0xFFE47C0E),
    )
    val styleString = TextStyle(
        fontSize = 15.sp,
        fontFamily = FontFamily.Serif,
        fontWeight = FontWeight(700),
    )

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Card(
            modifier = Modifier,
            shape = CircleShape,
            elevation = CardDefaults.cardElevation(defaultElevation = 20.dp)
        ) {
            Image(
                imageVector = Icons.Default.Person,
                contentDescription = "",
                modifier = Modifier.size(200.dp)
            )
        }
        Text(
            text = getDataUserInfo.display_name,
            modifier = Modifier
                .padding(top = 16.dp)
                .clickable { },
            style = styleNumbers
        )
        Column {
            Card(modifier = Modifier
                .padding(16.dp)
                .clickable { expande.value != expande.value }) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(text = "${getDataUserInfo.peso}kg", style = styleNumbers)
                        Text(text = "Peso", style = styleString)
                    }
                    Column {
                        Text(text = "${getDataUserInfo.altura}cm", style = styleNumbers)
                        Text(text = "altura", style = styleString)
                    }
                    Column {
                        Text(text = getDataUserInfo.idade, style = styleNumbers)
                        Text(text = "idade", style = styleString)
                    }
                }
            }
            Card {

            }
        }
    }
}
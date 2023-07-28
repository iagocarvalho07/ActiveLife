package com.iagocarvalho.activelife.screens.loginScreens.EmailLogin

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
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.iagocarvalho.activelife.R
import com.iagocarvalho.activelife.constants.UserForm
import com.iagocarvalho.activelife.navigation.NagitaionScreens

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun ActiveLifeLoginAndCreateAccScreen(navController: NavController = NavController(LocalContext.current)) {

    val showLoginForm = remember { mutableStateOf(true) }
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.linearGradient(
                    listOf(
                        Color(0xFFE47C0E),
                        Color.Black,
                        Color.Black,
                        Color.Black,
                        Color.Black,
                        Color.Black,
                        Color(0xFFE47C0E)
                    )
                )
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box() {
            Image(
                painter = painterResource(id = R.drawable.pexelsanastasiashuraeva),
                contentDescription = "",
                alpha = 0.2f,
                contentScale = ContentScale.Crop
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Card {
                    Column() {
                        if (showLoginForm.value) {
                            UserForm() { email, password ->

                            }
                        } else {
                            UserForm(iscreateAcount = true) { email, password ->

                            }
                        }
                        Row(
                            modifier = Modifier
                                .padding(8.dp)
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            Text(text = "Esqueceu a senha?", modifier = Modifier
                                .padding(3.dp)
                                .clickable { navController.navigate(NagitaionScreens.ResetPasswordScreen.name) })
                            Text(text = "Criar Uma Conta", modifier = Modifier
                                .padding(3.dp)
                                .clickable {
                                    showLoginForm.value = !showLoginForm.value
                                })
                        }
                    }
                }
            }
        }
    }
}

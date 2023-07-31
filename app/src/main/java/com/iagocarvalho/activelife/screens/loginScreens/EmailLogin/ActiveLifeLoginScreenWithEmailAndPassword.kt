package com.iagocarvalho.activelife.screens.loginScreens.EmailLogin

import android.util.Log
import android.widget.Toast
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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.iagocarvalho.activelife.R
import com.iagocarvalho.activelife.constants.UseFormeCreateUser
import com.iagocarvalho.activelife.constants.UserForm
import com.iagocarvalho.activelife.navigation.NagitaionScreens
import dagger.hilt.android.scopes.ViewModelScoped

@Composable
fun ActiveLifeLoginAndCreateAccScreen(
    navController: NavController,
    viewModel: FlashCardLoginAndCreateAccViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {

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
                    Column {
                        if (showLoginForm.value) {
                            UserForm() { email, password ->
                                viewModel.FirebaseSignInWithEmailAndPasswordViewModel(
                                    email,
                                    password,
                                    home = {
                                        navController.navigate(NagitaionScreens.EmailVerification.name)
                                            .run {
                                                Toast.makeText(
                                                    context,
                                                    "Login Com Sucesso",
                                                    Toast.LENGTH_SHORT
                                                ).show()
                                            }
                                    },
                                    errors = {
                                        Toast.makeText(
                                            context,
                                            "Erro na senha ou email",
                                            Toast.LENGTH_LONG
                                        ).show()
                                    })

                            }
                        } else {
                            UseFormeCreateUser() { nome, idade, peso, altura, email, senha ->
                                viewModel.createUserWithEmailAndPassword(
                                    nome,
                                    idade,
                                    peso,
                                    altura,
                                    email,
                                    senha,
                                    home = {
                                        navController.navigate(NagitaionScreens.EmailVerification.name)
                                            .run {
                                                Toast.makeText(
                                                    context,
                                                    "Verifique o email",
                                                    Toast.LENGTH_LONG
                                                ).show()
                                            }
                                    }, errors = { task ->
                                        val exception = task!!.message.toString()
                                        if (exception == "The email address is already in use by another account.") {
                                            Log.d(
                                                "FBTask",
                                                "oque aconteceu: ${task.localizedMessage}"
                                            )
                                            Toast.makeText(
                                                context,
                                                "Usuario Já cadastrado",
                                                Toast.LENGTH_LONG
                                            ).show()
                                        } else {
                                            Toast.makeText(
                                                context,
                                                "A senha deve Conter 6 ou + Caracteres",
                                                Toast.LENGTH_LONG
                                            ).show()
                                        }
                                    })
                            }
                        }
//                            UserForm(iscreateAcount = true) { email, password ->
//                                viewModel.createUserWithEmailAndPassword(email, password, home = {
//                                    navController.navigate(NagitaionScreens.EmailVerification.name)
//                                        .run {
//                                            Toast.makeText(
//                                                context,
//                                                "Verifique o email",
//                                                Toast.LENGTH_LONG
//                                            ).show()
//                                        }
//                                }, errors = { task ->
//                                    val exception = task!!.message.toString()
//                                    if (exception == "The email address is already in use by another account.") {
//                                        Log.d("FBTask", "oque aconteceu: ${task.localizedMessage}")
//                                        Toast.makeText(
//                                            context,
//                                            "Usuario Já cadastrado",
//                                            Toast.LENGTH_LONG
//                                        ).show()
//                                    } else {
//                                        Toast.makeText(
//                                            context,
//                                            "A senha deve Conter 6 ou + Caracteres",
//                                            Toast.LENGTH_LONG
//                                        ).show()
//                                    }
//                                })
//                            }
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
                        Text(text = if (showLoginForm.value) "Criar Conta" else "Login",
                            modifier = Modifier
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


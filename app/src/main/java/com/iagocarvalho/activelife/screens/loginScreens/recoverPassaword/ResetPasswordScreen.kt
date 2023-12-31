package com.iagocarvalho.activelife.screens.loginScreens.recoverPassaword

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
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
import com.iagocarvalho.activelife.constants.InputField
import com.iagocarvalho.activelife.navigation.NagitaionScreens

@Composable
fun ResetPasswordScreen(
    navController: NavController,
    viewModel: ResetPassworScreenViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    val context = LocalContext.current
    val email = remember { mutableStateOf("") }


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
        Box {
            Image(
                painter = painterResource(id = R.drawable.gymwoman),
                contentDescription = "",
                alpha = 0.3f,
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Card(modifier = Modifier.padding(16.dp)) {
                    InputField(
                        valueState = email,
                        labelId = "Digite Email Para Recuperar de senha",
                        enabled = true
                    )
                    Button(onClick = {
                        viewModel.Resetpassword(email.value, home = {
                            navController.navigate(NagitaionScreens.ActiveLifeLoginScreenWithEmail.name)
                            Toast.makeText(
                                context,
                                "Email de Recuperação Enviado",
                                Toast.LENGTH_LONG
                            )
                                .show()
                        }, errors = { task ->
                            val messenger = task!!.message.toString()
                            Log.d("Error", "ResetPasswordScreen: $messenger")
                            Toast.makeText(
                                context,
                                "Email Invalido, ou não encontrado",
                                Toast.LENGTH_LONG
                            ).show()
                        })


                    }, modifier = Modifier.padding(16.dp), colors = ButtonDefaults.buttonColors()) {
                        Text(text = "Enviar Email")
                    }
                }
            }
        }
    }
}
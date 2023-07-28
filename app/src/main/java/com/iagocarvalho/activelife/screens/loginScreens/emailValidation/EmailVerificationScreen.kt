package com.iagocarvalho.activelife.screens.loginScreens.emailValidation

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.iagocarvalho.activelife.R

@Preview
@Composable
fun EmailVerificationScreen(navController: NavController = NavController(LocalContext.current)) {

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

                Text(
                    text = "Realize a Verificação de email para acessar acessar o Ap", style = TextStyle(
                        fontSize = 20.sp,
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight(590),
                        color = Color(0xFFFFFFFF),
                    )
                )
                Button(onClick = {
                    Toast.makeText(context, "Email de Verificação Enviado", Toast.LENGTH_LONG)
                        .show()
                }, modifier = Modifier.padding(16.dp)) {
                    Text(text = "Envie O Email de confirmação")
                }
            }
        }
    }
}
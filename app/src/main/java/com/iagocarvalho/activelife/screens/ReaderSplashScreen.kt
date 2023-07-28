package com.iagocarvalho.activelife.screens

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
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
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.iagocarvalho.activelife.R
import com.iagocarvalho.activelife.navigation.NagitaionScreens
import kotlinx.coroutines.delay

@Preview(showBackground = true)
@Composable
fun ReaderSplashScreen(navController: NavController = NavController(LocalContext.current)) {
    val scale = remember {
        Animatable(0f)
    }
    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = 0.9f,
            animationSpec = tween(
                durationMillis = 1200,
                easing = { OvershootInterpolator(8f).getInterpolation(it) })
        )
        delay(1000L)
        if (FirebaseAuth.getInstance().currentUser?.email.isNullOrEmpty()) {
            navController.navigate(NagitaionScreens.ActiveLifeLoginScreenWithEmail.name)
        } else {
            navController.navigate(NagitaionScreens.HomeScreen.name)
        }
    }
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
                painter = painterResource(id = R.drawable.backgroundsrainersplash),
                contentDescription = "",
                alpha = 0.2f,
                contentScale = ContentScale.Crop
            )
            Column(modifier = Modifier
                .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Welcome to",
                    modifier = Modifier
                        .width(277.dp)
                        .height(47.dp),
                    style = TextStyle(
                        fontSize = 45.sp,
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight(590),
                        color = Color(0xFFFFFFFF),
                    )
                )
                Text(
                    text = "Active Life",
                    style = TextStyle(
                        fontSize = 45.sp,
                        fontFamily = FontFamily.Serif,
                        fontWeight = FontWeight(700),
                        color = Color(0xFFE47C0E),
                    )
                )
                Text(
                    text = "Discover the best shape of your body.Click below to continue.",
                    modifier = Modifier
                        .width(341.dp)
                        .height(105.dp)
                        .padding(start = 16.dp, bottom = 5.dp),
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontFamily = FontFamily.Cursive,
                        fontWeight = FontWeight(510),
                        color = Color(0xFFFFFDFD),
                    )
                )

            }
        }

    }

}
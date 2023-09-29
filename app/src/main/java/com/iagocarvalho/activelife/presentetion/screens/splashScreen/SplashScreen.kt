package com.iagocarvalho.activelife.presentetion.screens.splashScreen

import android.view.animation.OvershootInterpolator
import android.window.SplashScreen
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode.Companion.Screen
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.iagocarvalho.activelife.R
import com.iagocarvalho.activelife.navigation.AppGraph
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    onLoginScreen: () -> Unit = {},
    onWelcomeScreen: () -> Unit = {},
    splashScreenViewModel: SplashScreenViewModel = hiltViewModel()
) {
    Text(text = "splashScreen")

    val onBoardingCompleted by splashScreenViewModel.onboardingCompleted.collectAsState()

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
//        if (FirebaseAuth.getInstance().currentUser?.email.isNullOrEmpty()) {
//            onLoginScreen()
//        } else if (FirebaseAuth.getInstance().currentUser?.isEmailVerified == false) {
//            onLoginScreen()
//        } else {
        if (onBoardingCompleted) {
            onLoginScreen()
        } else {
            onWelcomeScreen()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 32.dp, end = 32.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            androidx.compose.material3.Text(
                text = stringResource(id = R.string.LogoApp),
                style = TextStyle(
                    fontSize = 26.sp,
                    fontWeight = FontWeight(700),
                    color = Color.DarkGray,
                )
            )
            androidx.compose.material3.Text(
                text = stringResource(id = R.string.textSplashScreen),
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight(400),
                    color = Color.Blue,
                    textAlign = TextAlign.Center,
                )
            )
        }
    }
}
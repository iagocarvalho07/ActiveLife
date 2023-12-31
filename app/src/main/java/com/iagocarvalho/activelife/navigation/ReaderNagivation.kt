package com.iagocarvalho.activelife.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.iagocarvalho.activelife.screens.ReaderSplashScreen
import com.iagocarvalho.activelife.screens.fullExerciseScreen.FullExerciceScreen
import com.iagocarvalho.activelife.screens.homeScreen.HomeScreen
import com.iagocarvalho.activelife.screens.loginScreens.emailLogin.ActiveLifeLoginAndCreateAccScreen
import com.iagocarvalho.activelife.screens.loginScreens.emailValidation.EmailVerificationScreen
import com.iagocarvalho.activelife.screens.loginScreens.recoverPassaword.ResetPasswordScreen
import com.iagocarvalho.activelife.screens.profileScreen.ProfileScreen
import com.iagocarvalho.activelife.screens.workoutScreen.WorkoutABCScreen

@Composable
fun ReaderNagivation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = NagitaionScreens.readerSplashScreen.name
    ) {
        composable(route = NagitaionScreens.readerSplashScreen.name){
            ReaderSplashScreen(navController = navController)
        }
        composable(route = NagitaionScreens.ActiveLifeLoginScreenWithEmail.name){
            ActiveLifeLoginAndCreateAccScreen(navController = navController)
        }
        composable(route = NagitaionScreens.EmailVerification.name){
            EmailVerificationScreen(navController = navController)
        }
        composable(route = NagitaionScreens.ResetPasswordScreen.name){
            ResetPasswordScreen(navController = navController)
        }
        composable(NagitaionScreens.HomeScreen.name){
            HomeScreen(navController = navController)
        }
        composable(NagitaionScreens.ProfileScreen.name){
            ProfileScreen(navController = navController)
        }
        composable(NagitaionScreens.FullExerciceScreen.name){
            FullExerciceScreen(navController = navController)
        }
        composable(NagitaionScreens.WorkoutABCScreen.name){
            WorkoutABCScreen(navController = navController)
        }
    }
}
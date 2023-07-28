package com.iagocarvalho.activelife.navigation

enum class NagitaionScreens {
    readerSplashScreen,
    ActiveLifeLoginScreenWithEmail,
    EmailVerification,
    ResetPasswordScreen,
    HomeScreen,
    ProfileScreen,
    WorkoutScreen;

    companion object {
        fun FromRoute(route: String): NagitaionScreens = when (route?.substringBefore("/")) {
            readerSplashScreen.name -> readerSplashScreen
            ActiveLifeLoginScreenWithEmail.name -> ActiveLifeLoginScreenWithEmail
            EmailVerification.name -> EmailVerification
            ResetPasswordScreen.name -> ResetPasswordScreen
            HomeScreen.name -> HomeScreen
            ProfileScreen.name -> ProfileScreen
            WorkoutScreen.name -> WorkoutScreen
            null -> readerSplashScreen
            else -> throw IllegalArgumentException("Route $route is not recognized")
        }


    }
}
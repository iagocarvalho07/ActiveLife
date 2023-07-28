package com.iagocarvalho.activelife.navigation

enum class NagitaionScreens {
    readerSplashScreen,
    ActiveLifeLoginScreenWithEmail,
    EmailVerification,
    ResetPasswordScreen,
    HomeScreen;

    companion object {
        fun FromRoute(route: String): NagitaionScreens = when (route?.substringBefore("/")) {
            readerSplashScreen.name -> readerSplashScreen
            ActiveLifeLoginScreenWithEmail.name -> ActiveLifeLoginScreenWithEmail
            EmailVerification.name -> EmailVerification
            ResetPasswordScreen.name -> ResetPasswordScreen
            HomeScreen.name -> HomeScreen

            null -> readerSplashScreen
            else -> throw IllegalArgumentException("Route $route is not recognized")
        }


    }
}
package com.iagocarvalho.activelife.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.google.accompanist.pager.ExperimentalPagerApi
import com.iagocarvalho.activelife.navigation.navigationViewContentScreen.NavigationViewContent
import com.iagocarvalho.activelife.presentetion.screens.autenticationScreens.createAccScreen.CreateAccScreen
import com.iagocarvalho.activelife.presentetion.screens.autenticationScreens.emailVerificationScreen.EmailVerificationScreen
import com.iagocarvalho.activelife.presentetion.screens.autenticationScreens.forgotPasswordScreen.ForgotPasswordScreen
import com.iagocarvalho.activelife.presentetion.screens.autenticationScreens.loginScreen.LoginScreen
import com.iagocarvalho.activelife.presentetion.screens.bottomBarScreens.home.HomeScreen
import com.iagocarvalho.activelife.presentetion.screens.bottomBarScreens.perfil.ProfileScreen
import com.iagocarvalho.activelife.presentetion.screens.splashScreen.SplashScreen
import com.iagocarvalho.activelife.presentetion.screens.bottomBarScreens.treino.TreinosScreen
import com.iagocarvalho.activelife.presentetion.screens.bottomBarScreens.treinosProntos.TreinosProntosScreen
import com.iagocarvalho.activelife.presentetion.screens.drawerScreens.details.DetailsScreen
import com.iagocarvalho.activelife.presentetion.screens.drawerScreens.disclamer.DisclamerScreen
import com.iagocarvalho.activelife.presentetion.screens.drawerScreens.faq.FaqScreen
import com.iagocarvalho.activelife.presentetion.screens.drawerScreens.help.HelpScreen
import com.iagocarvalho.activelife.presentetion.screens.splashScreen.WelcomeScree

@ExperimentalPagerApi
@Composable
fun RootNagivationGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = AppGraph.initial.ROOT,
        startDestination = AppGraph.auth.ROOT
    ) {
        authNavGraph(navController = navController)
        composable(route = AppGraph.home.ROOT) {
            NavigationViewContent()

        }
    }
}

@ExperimentalPagerApi
fun NavGraphBuilder.authNavGraph(navController: NavHostController) {
    navigation(
        route = AppGraph.auth.ROOT,
        startDestination = AppGraph.auth.SPLASHSCREE
    ) {
        composable(route = AppGraph.auth.SPLASHSCREE) {
            SplashScreen(
                onLoginScreen = { navController.navigate(AppGraph.home.ROOT) },
                onWelcomeScreen = { navController.navigate(AppGraph.auth.WELCOME) }
            )
        }
        composable(route = AppGraph.auth.WELCOME) {
            WelcomeScree(navController = navController, goHomeScreen = { navController.navigate(AppGraph.auth.LOGIN) })
        }
        composable(route = AppGraph.auth.LOGIN) {
            LoginScreen(navController = navController)
        }
        composable(route = AppGraph.auth.SIGN_UP) {
            CreateAccScreen(navController = navController)
        }
        composable(route = AppGraph.auth.FORGOT_PASSWORD) {
            ForgotPasswordScreen(navController = navController)
        }
        composable(route = AppGraph.auth.EMAILVERIFICATION) {
            EmailVerificationScreen(navController = navController)
        }
    }
}

@Composable
fun HomeNavGraph(navController: NavHostController) {
    NavHost(navController = navController, route = AppGraph.home.ROOT, startDestination = AppGraph.home.HOME) {
        composable(route = AppGraph.home.HOME) {
            HomeScreen(navController = navController)
        }
        composable(route = AppGraph.home.TREINOS) {
            TreinosScreen(navController = navController)
        }
        composable(route = AppGraph.home.TREINOSPRONTOS) {
            TreinosProntosScreen(navController = navController)
        }
        composable(route = AppGraph.home.PROFILE) {
            ProfileScreen(navController = navController)
        }
        detailsNavGraph(navController = navController)
    }
}

fun NavGraphBuilder.detailsNavGraph(navController: NavHostController) {
    navigation(route = AppGraph.details.ROOT, startDestination = AppGraph.details.HELP) {
        composable(route = AppGraph.details.HELP) {
            HelpScreen(navController = navController)
        }
        composable(route = AppGraph.details.DETAILS) {
            DetailsScreen(navController = navController)
        }
        composable(route = AppGraph.details.FAQ) {
            FaqScreen(navController = navController)
        }
        composable(route = AppGraph.details.DISCLAMER) {
            DisclamerScreen(navController = navController)
        }
    }
}
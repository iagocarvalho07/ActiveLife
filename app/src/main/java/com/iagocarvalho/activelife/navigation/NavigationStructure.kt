package com.iagocarvalho.activelife.navigation

object AuthGraph {
    const val ROOT = "auth_graph"
    const val WELCOME = "welcome"
    const val LOGIN = "login"
    const val SIGN_UP = "sign_up"
    const val FORGOT_PASSWORD = "forgot_password"
    const val SPLASHSCREE = "Splash_SCreen"
    const val EMAILVERIFICATION = "email_verification"
}

object DetailsGraph {
    const val ROOT = "details_graph"
    const val HELP= "help"
    const val FAQ = "faq"
    const val DETAILS = "details"
    const val DISCLAMER = "disclaimer"
}

object HomeGraph {
    const val ROOT = "home_graph"
    const val HOME = "home"
    const val PROFILE = "profile"
    const val TREINOSPRONTOS = "Favorites"
    const val TREINOS = "books"
    const val WRITEBOOK = "Write_Book"
    const val SETTINGS = "settings"
}

object RootGraph {
    const val ROOT = "root_graph"
}

object AppGraph {
    val auth = AuthGraph
    val details = DetailsGraph
    val home = HomeGraph
    val initial = RootGraph
}
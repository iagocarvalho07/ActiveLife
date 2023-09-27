package com.iagocarvalho.activelife.constants

import com.iagocarvalho.activelife.R
import com.iagocarvalho.activelife.navigation.AppGraph

sealed class ItemsMenu(
    val icon: Int,
    val title: String,
    val route: String,
) {
    object RotaBottomBar1 : ItemsMenu((R.drawable.baseline_home_24), "Home", AppGraph.home.HOME)
    object RotaBottomBar2 :
        ItemsMenu((R.drawable.baseline_menu_book_24), "Books", AppGraph.home.TREINOS)

    object RotaBottomBar3 :
        ItemsMenu((R.drawable.baseline_favorite_24), "", AppGraph.home.TREINOSPRONTOS)

    object RotaBottomBar4 :
        ItemsMenu((R.drawable.baseline_person_24), "Perfil", AppGraph.home.PROFILE)
}

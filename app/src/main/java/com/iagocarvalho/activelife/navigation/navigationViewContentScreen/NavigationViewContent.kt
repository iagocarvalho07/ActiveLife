package com.iagocarvalho.activelife.navigation.navigationViewContentScreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.iagocarvalho.activelife.constants.ItemsMenu
import com.iagocarvalho.activelife.navigation.HomeNavGraph

@Preview
@Composable
fun NavigationViewContent(navController: NavHostController = rememberNavController()) {


    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    val navigationItem = listOf(
        ItemsMenu.RotaBottomBar1,
        ItemsMenu.RotaBottomBar2,
        ItemsMenu.RotaBottomBar3,
        ItemsMenu.RotaBottomBar4
    )
    androidx.compose.material.Scaffold(
        topBar = { HomeTopAppBar(navController = navController) },
        scaffoldState = scaffoldState,
        drawerContent = { DrawableNavigationBar(navController = navController, drawerState = scaffoldState.drawerState) },
        bottomBar = { BootombarapNavigation(navController = navController, menu = navigationItem) },
        floatingActionButton = { Fab(scope = scope, scaffoldState = scaffoldState) },
        isFloatingActionButtonDocked = true

    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            HomeNavGraph(navController = navController)
        }
    }
}
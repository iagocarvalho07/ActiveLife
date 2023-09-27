package com.iagocarvalho.activelife.navigation.navigationViewContentScreen

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.BottomAppBar
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.iagocarvalho.activelife.constants.ItemsMenu


@Composable
fun currentRoute(navController: NavHostController): String? {
    val entrad by navController.currentBackStackEntryAsState()
    return entrad?.destination?.route
}

@Composable
fun BootombarapNavigation(
    navController: NavHostController,
    menu: List<ItemsMenu>
) {
    BottomAppBar(
        backgroundColor = Color.Blue,
        cutoutShape = MaterialTheme.shapes.small.copy(
            CornerSize(percent = 50)
        )
    ) {
        val currentRoute = currentRoute(navController = navController)
        BottomNavigation(
            modifier = Modifier.padding(0.dp, 0.dp, 60.dp, 0.dp),
            backgroundColor = Color.Blue
        ) {
            menu.forEach { itemsMenu ->
                BottomNavigationItem(
                    selected = currentRoute == itemsMenu.route,
                    onClick = { navController.navigate(itemsMenu.route) },
                    icon = {
                        Icon(
                            painter = painterResource(id = itemsMenu.icon),
                            contentDescription = itemsMenu.title,
                            tint = if (currentRoute == itemsMenu.route) {
                                Color.White
                            } else {
                                Color.Gray
                            }
                        )
                    },
                    label = {
                        Text(
                            text = itemsMenu.title,
                            color = if (currentRoute == itemsMenu.route) {
                                Color.White
                            } else {
                                Color.Gray
                            }
                        )
                    },
                    selectedContentColor = Color.White,
                    unselectedContentColor = Color.DarkGray
                )
            }
        }
    }
}
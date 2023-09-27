package com.iagocarvalho.activelife.navigation.navigationViewContentScreen

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ScaffoldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun Fab(scope: CoroutineScope, scaffoldState: ScaffoldState) {
    FloatingActionButton(
        onClick = {
            scope.launch {
                scaffoldState.drawerState.open()
            }
        },
        contentColor = Color.White,
        containerColor = Color.DarkGray,
        shape = CircleShape
    ) {
        Icon(
            imageVector = Icons.Default.Menu, contentDescription = ""
        )
    }
}
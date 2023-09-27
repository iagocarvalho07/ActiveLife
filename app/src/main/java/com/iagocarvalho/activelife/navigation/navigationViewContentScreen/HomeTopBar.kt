package com.iagocarvalho.activelife.navigation.navigationViewContentScreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
import androidx.compose.material.TopAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.iagocarvalho.activelife.R

@Composable
fun HomeTopAppBar(
    navController: NavHostController,
    onSingOut: () -> Unit = {},
) {
    TopAppBar(title = {
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "Top Ap Bar")
        }
    }, modifier = Modifier
        .padding(10.dp)
        .clip(RoundedCornerShape(50)),
        navigationIcon = {
            IconButton(
                onClick = { navController.popBackStack() }) {
                Icon(
                    painter = painterResource(id = (R.drawable.baseline_arrow_back_24)),
                    contentDescription = ""
                )
            }
        }, actions = {
            IconButton(
                onClick = {
                    onSingOut().run { }
                }) {
                Icon(
                    painter = painterResource(id = (R.drawable.baseline_exit_to_app_24)),
                    contentDescription = ""
                )
            }
        }, backgroundColor = Color.Blue
    )
}
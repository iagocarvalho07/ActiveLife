package com.iagocarvalho.activelife.navigation.navigationViewContentScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.DrawerState
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.iagocarvalho.activelife.navigation.AppGraph
import kotlinx.coroutines.launch

@Composable
fun DrawableNavigationBar(
    navController: NavHostController = rememberNavController(),
    drawerState: DrawerState,
    changeIconFlotingButton: () -> Unit = {}
) {
    val modifierTextButton = Modifier.fillMaxWidth()
    val scope = rememberCoroutineScope()
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        DataUser()
        Divider(modifier = Modifier.padding(8.dp), thickness = 2.dp)
        TextButton(
            modifier = modifierTextButton,
            onClick = {
                navController.navigate(AppGraph.details.FAQ)
                changeIconFlotingButton()
                scope.launch { drawerState.close() }
            }) {
            Text(text = "Faq")
        }
        TextButton(
            modifier = modifierTextButton,
            onClick = {
                navController.navigate(AppGraph.details.DETAILS)
                changeIconFlotingButton()
                scope.launch { drawerState.close() }
            }) {
            Text(text = "Details")
        }
        TextButton(
            modifier = modifierTextButton,
            onClick = {
                navController.navigate(AppGraph.details.HELP)
                changeIconFlotingButton()
                scope.launch { drawerState.close() }
            }) {
            Text(text = "HELP")
        }
        TextButton(
            modifier = modifierTextButton,
            onClick = {
                navController.navigate(AppGraph.details.DISCLAMER)
                changeIconFlotingButton()
                scope.launch { drawerState.close() }
            }) {
            Text(text = "DISCLAIMER")
        }
    }
}

@Composable
fun DataUser() {
    val textStyleVariavel = TextStyle(
        fontSize = 21.sp,
        fontWeight = FontWeight(600),
        color = Color.DarkGray,
        textAlign = TextAlign.Center,
    )
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "User", style = textStyleVariavel
        )
        Text(
            text = "IagoCarvalho", style = textStyleVariavel
        )
    }
}
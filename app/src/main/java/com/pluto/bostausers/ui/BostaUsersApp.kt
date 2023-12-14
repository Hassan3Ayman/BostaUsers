package com.pluto.bostausers.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.pluto.bostausers.ui.navigation.AppNavGraph

val LocalNavController =
    compositionLocalOf<NavHostController> { error("No NavController found!") }
@Composable
fun BostaUsersApp() {
    val navController = rememberNavController()
    CompositionLocalProvider(LocalNavController provides navController){
        AppNavGraph()
    }
}
package com.pluto.bostausers.ui.screens.profile

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.composable
import com.pluto.bostausers.ui.navigation.ScreenDestination

fun NavGraphBuilder.profileRoute(){
    composable(route = ScreenDestination.Profile) {
        ProfileScreen()
    }
}

fun NavController.backToProfile(builder: NavOptionsBuilder.() -> Unit = {}){
    navigate(ScreenDestination.Profile, builder = builder)
}
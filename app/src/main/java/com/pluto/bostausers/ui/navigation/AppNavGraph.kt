package com.pluto.bostausers.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import com.pluto.bostausers.ui.LocalNavController
import com.pluto.bostausers.ui.screens.album_photos.albumPhotosRoute
import com.pluto.bostausers.ui.screens.image_viewer.imageViewerRoute
import com.pluto.bostausers.ui.screens.profile.profileRoute

@Composable
fun AppNavGraph() {
    NavHost(
        navController = LocalNavController.current,
        startDestination = ScreenDestination.Profile
    ){
        profileRoute()
        albumPhotosRoute()
        imageViewerRoute()
    }
}
package com.pluto.bostausers.ui.screens.album_photos

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.pluto.bostausers.ui.navigation.ScreenDestination

fun NavGraphBuilder.albumPhotosRoute() {
    composable(
        route = "${ScreenDestination.AlbumPhotos}/{${AlbumPhotosArgs.ID}}/{${AlbumPhotosArgs.NAME}}",
        arguments = listOf(
            navArgument(AlbumPhotosArgs.ID) { NavType.StringType },
            navArgument(AlbumPhotosArgs.NAME) { NavType.StringType }
        )
    ) {
        AlbumPhotosScreen()
    }
}

fun NavController.navigateToAlbumPhotosScreen(
    id: String,
    title: String,
    builder: NavOptionsBuilder.() -> Unit = {}
) {
    navigate(route = "${ScreenDestination.AlbumPhotos}/${id}/${title}", builder = builder)
}

class AlbumPhotosArgs(savedStateHandle: SavedStateHandle) {
    val id: String = savedStateHandle[ID] ?: ""
    val title: String = savedStateHandle[NAME] ?: ""

    companion object {
        const val ID = "id"
        const val NAME = "name"
    }
}
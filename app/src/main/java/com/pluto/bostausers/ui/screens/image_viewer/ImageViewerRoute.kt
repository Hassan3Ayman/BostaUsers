package com.pluto.bostausers.ui.screens.image_viewer

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.pluto.bostausers.ui.navigation.ScreenDestination

fun NavGraphBuilder.imageViewerRoute() {
    composable(
        route = "${ScreenDestination.ImageViewer}/{${ImageViewerArgs.URL}}",
        arguments = listOf(
            navArgument(ImageViewerArgs.URL) { NavType.StringType }
        )
    ) {
        ImageViewerScreen()
    }
}

fun NavController.navigateToImageViewerScreen(
    urls: String,
    builder: NavOptionsBuilder.() -> Unit = {}
) {
    navigate(route = "${ScreenDestination.ImageViewer}/${urls}", builder = builder)
}


class ImageViewerArgs(savedStateHandle: SavedStateHandle) {
    val url: String = savedStateHandle[URL] ?: ""

    companion object {
        const val URL = "url"
    }
}

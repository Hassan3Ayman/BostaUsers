package com.pluto.bostausers.ui.screens.image_viewer

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter

@Composable
fun ImageViewerScreen(
    viewModel: ImageViewerViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    ImageViewerContent(state)
}

@Composable
private fun ImageViewerContent(state: ImageViewerUiState) {

    var scale by remember { mutableFloatStateOf(1f) }
    var offset by remember { mutableStateOf(Offset(0f, 0f)) }
    val context = LocalContext.current
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(vertical = 16.dp)
    ) {

        val painter = rememberAsyncImagePainter(model = state.url)
        Image(
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxWidth()
                .fillMaxHeight(.5f)
                .pointerInput(Unit) {
                    detectTransformGestures { _, pan, zoom, _ ->
                        scale *= zoom
                        scale = scale.coerceIn(1f, 2f)

                        // Calculate the offset to keep the image centered
                        val maxOffsetX = (scale - 1) * size.width / (2 * scale)
                        val maxOffsetY = (scale - 1) * size.height / (2 * scale)
                        offset = Offset(
                            (offset.x + pan.x).coerceIn(-maxOffsetX, maxOffsetX),
                            (offset.y + pan.y).coerceIn(-maxOffsetY, maxOffsetY)
                        )
                    }
                }
                .graphicsLayer(
                    scaleX = scale,
                    scaleY = scale,
                    translationX = offset.x,
                    translationY = offset.y
                ),
            painter = painter,
            contentDescription = "",
            contentScale = ContentScale.Crop
        )

        Icon(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(16.dp)
                .clickable {
                    val painterState = painter.state as? AsyncImagePainter.State.Success
                    val drawable = painterState?.result?.drawable
                    if (drawable != null) {
                        context.shareImage(
                            "Share image via",
                            drawable,
                            "filename"
                        )
                    }
                },
            imageVector = Icons.Default.Share,
            contentDescription = "",
            tint = Color.White
        )
    }
}
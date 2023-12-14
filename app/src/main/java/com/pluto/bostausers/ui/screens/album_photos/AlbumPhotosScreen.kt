package com.pluto.bostausers.ui.screens.album_photos

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.pluto.bostausers.ui.LocalNavController
import com.pluto.bostausers.ui.screens.composable.ScreenState
import com.pluto.bostausers.ui.screens.image_viewer.navigateToImageViewerScreen

@Composable
fun AlbumPhotosScreen(
    viewModel: AlbumPhotosViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    val navController = LocalNavController.current

    AlbumPhotosContent(
        onClickImage = {url -> navController.navigateToImageViewerScreen(url.split("/").last())},
        onTextChange = viewModel::onSearchInputChange,
        state = state,
    )
}

@Composable
private fun AlbumPhotosContent(
    onClickImage: (String) -> Unit,
    onTextChange: (String) -> Unit,
    state: AlbumPhotosUiState
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            modifier = Modifier.padding(bottom = 16.dp),
            text = state.title,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

        TextField(
            modifier = Modifier
                .padding(bottom = 16.dp)
                .fillMaxWidth(),
            value = state.searchInput,
            onValueChange = onTextChange,
            singleLine = true,
            leadingIcon = {
                Icon(Icons.Default.Search, contentDescription = "")
            },
            placeholder = {
                Text(
                    text = "Search in images",
                )
            },
            shape = RoundedCornerShape(16.dp),
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
            ),
        )

        ScreenState(isLoading = state.isLoading, errorMessage = state.errorMessage) {
            LazyVerticalGrid(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(vertical = 16.dp),
                columns = GridCells.Fixed(3),
            ) {
                items(state.photosUrl) {
                    Image(
                        modifier = Modifier
                            .size(100.dp)
                            .clickable { onClickImage(it) },
                        painter = rememberAsyncImagePainter(model = it),
                        contentDescription = "",
                        contentScale = ContentScale.Crop
                    )
                }
            }
        }
    }
}
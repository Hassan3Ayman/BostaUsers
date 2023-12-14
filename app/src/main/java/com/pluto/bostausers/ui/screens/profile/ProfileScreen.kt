package com.pluto.bostausers.ui.screens.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.pluto.bostausers.ui.LocalNavController
import com.pluto.bostausers.ui.screens.album_photos.navigateToAlbumPhotosScreen
import com.pluto.bostausers.ui.screens.composable.ScreenState

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    val navController = LocalNavController.current
    ScreenState(isLoading = state.isLoading, errorMessage = state.errorMessage) {
        ProfileContent(
            onClickAlbum = { id: String, name: String ->
                navController.navigateToAlbumPhotosScreen(
                    id,
                    name
                )
            },
            state = state
        )
    }
}

@Composable
private fun ProfileContent(state: ProfileUiState, onClickAlbum: (String, String) -> Unit) {
    val navController = LocalNavController.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 32.dp)
    ) {
        Text(
            modifier = Modifier.padding(bottom = 16.dp),
            text = state.userInfo.name,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = state.userInfo.address,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium
        )

        Text(
            modifier = Modifier.padding(bottom = 32.dp),
            text = state.userInfo.zipCode,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium
        )

        Text(
            modifier = Modifier.padding(bottom = 16.dp),
            text = "My Albums",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )

        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(state.userAlbums) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(4.dp))
                        .background(Color(0xFFF7F7F7))
                        .clickable { onClickAlbum(it.id, it.title) }
                        .padding(horizontal = 8.dp, vertical = 12.dp),
                    text = it.title,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Light,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}
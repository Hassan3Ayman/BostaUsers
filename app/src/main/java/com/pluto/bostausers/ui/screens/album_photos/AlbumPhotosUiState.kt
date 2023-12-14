package com.pluto.bostausers.ui.screens.album_photos

import com.pluto.bostausers.ui.screens.base.BaseUiState

data class AlbumPhotosUiState(
    val isLoading: Boolean = true,
    val errorMessage: String? = null,
    val title: String = "",
    val searchInput: String = "",
    val photosUrl: List<String> = emptyList()
): BaseUiState
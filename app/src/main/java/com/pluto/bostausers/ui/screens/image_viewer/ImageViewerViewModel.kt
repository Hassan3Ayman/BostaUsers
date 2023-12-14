package com.pluto.bostausers.ui.screens.image_viewer

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import com.pluto.bostausers.ui.screens.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ImageViewerViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle
):  BaseViewModel<ImageViewerUiState>(ImageViewerUiState()){

    private val args = ImageViewerArgs(savedStateHandle)
    init {
        Log.v("hass", args.url)
        _state.update { it.copy(url = "https://via.placeholder.com/600/" + args.url) }
    }
}
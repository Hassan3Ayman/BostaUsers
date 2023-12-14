package com.pluto.bostausers.ui.screens.album_photos

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.pluto.bostausers.domain.models.AlbumPhoto
import com.pluto.bostausers.domain.usecase.GetAlbumPhotosUseCase
import com.pluto.bostausers.ui.screens.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlbumPhotosViewModel @Inject constructor(
    private val getAlbumPhotosUseCase: GetAlbumPhotosUseCase,
    private val savedStateHandle: SavedStateHandle
): BaseViewModel<AlbumPhotosUiState>(AlbumPhotosUiState()){

    private val args = AlbumPhotosArgs(savedStateHandle)

    init {
        _state.update { it.copy(title = args.title) }
        getAlbumPhotos()
        viewModelScope.launch(Dispatchers.IO) {
            _state.update { it.copy(isLoading = true) }
            _state.debounce(1000).collect{
                tryToExecute(
                    function = { getAlbumPhotosUseCase.searchAlbumPhotos(it.searchInput) },
                    onSuccess = ::onSuccess,
                    onError = ::onGetError
                )
            }
        }
    }

    private fun getAlbumPhotos(){
        tryToExecute(
            function = {getAlbumPhotosUseCase.getAlbumPhotos(args.id)},
            onSuccess = ::onSuccess,
            onError = ::onGetError
        )
    }

    fun onSearchInputChange(input: String){
        _state.update { it.copy(searchInput = input) }
    }

    private fun onSuccess(result: List<AlbumPhoto>){
        _state.update { photosUiState ->
            photosUiState.copy(
                isLoading = false,
                errorMessage = null,
                photosUrl = result.map { it.url }
            )
        }
    }

    private fun onGetError(message: String) {
        _state.update { it.copy(isLoading = false, errorMessage = message) }
    }
}
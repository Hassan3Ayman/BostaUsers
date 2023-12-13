package com.pluto.bostausers.ui.screens.profile

import android.util.Log
import com.pluto.bostausers.domain.models.UserWithAlbums
import com.pluto.bostausers.domain.usecase.GetUserAlbumsUseCase
import com.pluto.bostausers.ui.screens.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getUserAlbums: GetUserAlbumsUseCase
) : BaseViewModel<ProfileUiState>(ProfileUiState()) {


    init {
        getUserInfoWithAlbums()
    }

    private fun getUserInfoWithAlbums() {
        tryToExecute(
            function = getUserAlbums::getRandomUser,
            onSuccess = ::onGetUserWithAlbumsSuccess,
            onError = ::onGetError
        )
    }

    private fun onGetUserWithAlbumsSuccess(result: UserWithAlbums) {
        _state.update { uiState ->
            uiState.copy(
                isLoading = false,
                errorMessage = null,
                userInfo = result.userInfo.toUserInfoUiState(),
                userAlbums = result.albums.map { it.toAlbumUiState() })
        }
        Log.v("hass", state.value.toString())
    }

    private fun onGetError(message: String) {
        _state.update { it.copy(isLoading = false, errorMessage = message) }
    }
}
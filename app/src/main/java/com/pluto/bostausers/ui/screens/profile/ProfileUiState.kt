package com.pluto.bostausers.ui.screens.profile

import com.pluto.bostausers.domain.models.Album
import com.pluto.bostausers.domain.models.UserInfo

data class ProfileUiState(
    val isLoading: Boolean = true,
    val errorMessage: String? = null,
    val userInfo: UserInfoUiState = UserInfoUiState(),
    val userAlbums: List<AlbumUiState> = emptyList()
)

data class UserInfoUiState(
    val name: String = "",
    val address: String = "",
    val zipCode: String = ""
)

data class AlbumUiState(
    val id: String = "",
    val title: String = ""
)

fun UserInfo.toUserInfoUiState() = UserInfoUiState(
    name = this.name,
    zipCode = this.address?.zipcode ?: "",
    address = this.address?.run { "$city, $street, $suite" } ?: ""
)

fun Album.toAlbumUiState() = AlbumUiState(
    id = this.id,
    title = this.title
)
package com.pluto.bostausers.domain.models

data class UserWithAlbums(
    val userInfo: UserInfo,
    val albums: List<Album>
)
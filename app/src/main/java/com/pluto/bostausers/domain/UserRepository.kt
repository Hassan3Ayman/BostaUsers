package com.pluto.bostausers.domain

import com.pluto.bostausers.domain.models.Album
import com.pluto.bostausers.domain.models.AlbumPhoto
import com.pluto.bostausers.domain.models.UserInfo

interface UserRepository {
    suspend fun getUsers(): List<UserInfo>
    suspend fun getAllUserAlbums(userId: String): List<Album>
    suspend fun getAlbumPhotos(albumId: String): List<AlbumPhoto>
}
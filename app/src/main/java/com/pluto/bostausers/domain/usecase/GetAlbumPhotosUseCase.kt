package com.pluto.bostausers.domain.usecase

import com.pluto.bostausers.domain.UserRepository
import com.pluto.bostausers.domain.models.AlbumPhoto
import javax.inject.Inject

class GetAlbumPhotosUseCase @Inject constructor(
    private val repository: UserRepository
) {

    private var albumPhotos: List<AlbumPhoto> = emptyList()
    suspend fun getAlbumPhotos(albumId: String): List<AlbumPhoto> {
        albumPhotos = repository.getAlbumPhotos(albumId)
        return albumPhotos
    }

    suspend fun searchAlbumPhotos(text: String): List<AlbumPhoto> {
        return albumPhotos.filter { it.title.startsWith(text) }
            .ifEmpty { throw Exception("Not Found") }
    }
}
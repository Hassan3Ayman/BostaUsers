package com.pluto.bostausers.domain.mapper

import com.pluto.bostausers.data.dto.AlbumDto
import com.pluto.bostausers.data.dto.AlbumPhotoDto
import com.pluto.bostausers.domain.models.Album
import com.pluto.bostausers.domain.models.AlbumPhoto

fun AlbumDto.toAlbum() = Album(
    id = this.id ?: "",
    title = this.title ?: ""
)

fun AlbumPhotoDto.toAlbumPhoto() = AlbumPhoto(
    id = this.albumId ?: "",
    title = this.title ?: "",
    url = this.url ?: ""
)
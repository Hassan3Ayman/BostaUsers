package com.pluto.bostausers.domain.mapper

import com.pluto.bostausers.data.dto.AlbumDto
import com.pluto.bostausers.domain.models.Album

fun AlbumDto.toAlbum() = Album(
    id = this.id ?: "",
    title = this.title ?: ""
)
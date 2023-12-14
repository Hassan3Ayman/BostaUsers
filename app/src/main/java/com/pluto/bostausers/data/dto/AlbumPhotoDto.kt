package com.pluto.bostausers.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AlbumPhotoDto(
    @SerialName("albumId")
    val albumId: String?,
    @SerialName("id")
    val id: String?,
    @SerialName("thumbnailUrl")
    val thumbnailUrl: String?,
    @SerialName("title")
    val title: String?,
    @SerialName("url")
    val url: String?
)
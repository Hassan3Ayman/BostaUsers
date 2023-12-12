package com.pluto.bostausers.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AlbumDto(
    @SerialName("userId")
    val userId: String?,
    @SerialName("id")
    val id: String?,
    @SerialName("title")
    val title: String?
)
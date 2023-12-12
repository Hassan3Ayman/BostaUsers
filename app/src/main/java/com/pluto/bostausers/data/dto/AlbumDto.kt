package com.pluto.bostausers.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AlbumDto(
    @SerialName("userId")
    val userId: Int?,
    @SerialName("id")
    val id: Int?,
    @SerialName("title")
    val title: String?
)
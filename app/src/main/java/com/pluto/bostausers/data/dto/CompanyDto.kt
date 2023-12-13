package com.pluto.bostausers.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CompanyDto(
    @SerialName("bs")
    val bs: String?,
    @SerialName("catchPhrase")
    val catchPhrase: String?,
    @SerialName("name")
    val name: String?
)
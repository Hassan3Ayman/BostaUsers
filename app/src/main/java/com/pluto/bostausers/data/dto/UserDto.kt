package com.pluto.bostausers.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserDto(
    @SerialName("id")
    val id: Int?,
    @SerialName("name")
    val name: String?,
    @SerialName("username")
    val username: String?,
    @SerialName("address")
    val address: AddressDto?,
    @SerialName("company")
    val company: CompanyDto?,
    @SerialName("email")
    val email: String?,
    @SerialName("phone")
    val phone: String?,
    @SerialName("website")
    val website: String?
)
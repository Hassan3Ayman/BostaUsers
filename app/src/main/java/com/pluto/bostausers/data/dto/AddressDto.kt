package com.pluto.bostausers.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AddressDto(
    @SerialName("city")
    val city: String?,
    @SerialName("geo")
    val geo: GeoDto?,
    @SerialName("street")
    val street: String?,
    @SerialName("suite")
    val suite: String?,
    @SerialName("zipcode")
    val zipcode: String?
)
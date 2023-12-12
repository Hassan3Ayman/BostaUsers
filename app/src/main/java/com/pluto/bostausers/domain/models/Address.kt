package com.pluto.bostausers.domain.models

import com.pluto.bostausers.data.dto.GeoDto
import kotlinx.serialization.SerialName

data class Address(
    val city: String,
    val street: String,
    val suite: String,
    val zipcode: String
)

package com.pluto.bostausers.domain.mapper

import com.pluto.bostausers.data.dto.AddressDto
import com.pluto.bostausers.data.dto.UserInfoDto
import com.pluto.bostausers.domain.models.Address
import com.pluto.bostausers.domain.models.UserInfo

fun UserInfoDto.toUserInfo() = UserInfo(
    id = this.id ?: "",
    name = this.name ?: "",
    address = this.address?.toAddress()
)

fun AddressDto.toAddress() = Address(
    city = this.city ?: "",
    street = this.street ?: "",
    suite = this.suite ?: "",
    zipcode = this.zipcode ?: ""
)
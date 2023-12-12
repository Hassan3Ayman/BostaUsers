package com.pluto.bostausers.data

import com.pluto.bostausers.domain.UserRepository
import javax.inject.Inject

class UserRepositoryImp @Inject constructor(
    private val userRepository: UserRepository
): UserRepository{
}
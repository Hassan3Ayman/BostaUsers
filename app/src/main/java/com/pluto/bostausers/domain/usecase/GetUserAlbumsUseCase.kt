package com.pluto.bostausers.domain.usecase

import com.pluto.bostausers.domain.UserRepository
import com.pluto.bostausers.domain.models.UserWithAlbums
import javax.inject.Inject

class GetUserAlbumsUseCase @Inject constructor(
    private val repository: UserRepository
) {
    suspend fun getRandomUser(): UserWithAlbums {
        val userInfo = repository.getUsers().random()
        val albums = repository.getAllUserAlbums(userInfo.id)
        return UserWithAlbums(userInfo, albums)
    }
}
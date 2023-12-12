package com.pluto.bostausers.data

import com.pluto.bostausers.domain.UserRepository
import com.pluto.bostausers.domain.mapper.toAlbum
import com.pluto.bostausers.domain.mapper.toUserInfo
import com.pluto.bostausers.domain.models.Album
import com.pluto.bostausers.domain.models.UserInfo
import retrofit2.Response
import java.io.IOException
import java.net.UnknownHostException
import javax.inject.Inject

class UserRepositoryImp @Inject constructor(
    private val userService: UserService
): UserRepository{
    override suspend fun getUsers(): List<UserInfo> {
        return wrapResponse { userService.getAllUsers() }.map { it.toUserInfo() }
    }

    override suspend fun getAllUserAlbums(userId: String): List<Album> {
        return wrapResponse { userService.getAllUserAlbums(userId) }.map { it.toAlbum() }
    }

    private suspend fun <T> wrapResponse(
        function: suspend () -> Response<T>
    ): T{
        try {
            val response = function()
            if (response.isSuccessful) {
                return response.body() ?: throw Exception("Null data")
            } else {
                throw Exception("Something is wrong")
            }
        } catch (e: UnknownHostException) {
            throw Exception("No internet")
        }
    }
}
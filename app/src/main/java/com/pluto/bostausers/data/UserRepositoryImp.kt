package com.pluto.bostausers.data

import com.pluto.bostausers.domain.UserRepository
import com.pluto.bostausers.domain.mapper.toAlbum
import com.pluto.bostausers.domain.mapper.toAlbumPhoto
import com.pluto.bostausers.domain.mapper.toUserInfo
import com.pluto.bostausers.domain.models.Album
import com.pluto.bostausers.domain.models.AlbumPhoto
import com.pluto.bostausers.domain.models.UserInfo
import retrofit2.Response
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

    override suspend fun getAlbumPhotos(albumId: String): List<AlbumPhoto> {
        return wrapResponse { userService.getAlbumPhotos(albumId) }.map { it.toAlbumPhoto() }
    }

    private suspend fun <T> wrapResponse(
        function: suspend () -> Response<T>
    ): T{
        try {
            val response = function()
            if (response.isSuccessful) {
                return response.body() ?: throw Exception("Null data")
            } else {
                throw Exception("Something went wrong")
            }
        } catch (e: UnknownHostException) {
            throw Exception("No internet")
        }
    }
}
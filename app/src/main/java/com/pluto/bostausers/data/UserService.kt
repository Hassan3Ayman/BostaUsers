package com.pluto.bostausers.data

import com.pluto.bostausers.data.dto.AlbumDto
import com.pluto.bostausers.data.dto.AlbumPhotoDto
import com.pluto.bostausers.data.dto.UserInfoDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface UserService {

    @GET("users")
    suspend fun getAllUsers(): Response<List<UserInfoDto>>

    @GET("/albums")
    suspend fun getAllUserAlbums(
        @Query("userId") userId: String
    ): Response<List<AlbumDto>>

    @GET("/photos")
    suspend fun getAlbumPhotos(
        @Query("albumId") albumId: String
    ): Response<List<AlbumPhotoDto>>
}
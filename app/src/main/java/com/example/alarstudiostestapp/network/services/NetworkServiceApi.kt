package com.example.alarstudiostestapp.network.services

import com.example.alarstudiostestapp.network.entitys.LoginResponse
import com.example.alarstudiostestapp.network.entitys.PlacesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkServiceApi {
    @GET("test/auth.cgi")
    suspend fun login(
        @Query("username") username: String,
        @Query("password") password: String
    ): LoginResponse

    @GET("test/data.cgi")
    suspend fun getPlaces(
        @Query("code") code: String,
        @Query("p") page: Int
    ): PlacesResponse
}
package com.example.base_app.data.remote.api

import com.example.base_app.data.remote.dto.UserDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST("register")
    suspend fun register(@Body request: Map<String, String>): Response<UserDto>

    @POST("login")
    suspend fun login(@Body request: Map<String, String>): Response<UserDto>
}
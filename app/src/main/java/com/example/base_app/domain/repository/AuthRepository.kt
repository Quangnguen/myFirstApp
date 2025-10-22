package com.example.base_app.domain.repository

import com.example.base_app.domain.model.User

interface AuthRepository {
    suspend fun registerUser(email: String, name: String, password: String): Result<User>
    suspend fun loginUser(email: String, password: String): Result<User>
    suspend fun logout()
    suspend fun getCurrentUser(): User?
}
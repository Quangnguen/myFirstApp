package com.example.base_app.domain.usecase

import com.example.base_app.domain.model.User
import com.example.base_app.domain.repository.AuthRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(email: String, password: String): Result<User> {
        if (email.isBlank() || password.isBlank()) {
            return Result.failure(Exception("Thông tin không được để trống"))
        }
        return authRepository.loginUser(email, password)
    }
}
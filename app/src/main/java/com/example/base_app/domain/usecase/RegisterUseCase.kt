package com.example.base_app.domain.usecase

import com.example.base_app.domain.model.User
import com.example.base_app.domain.repository.AuthRepository
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(email: String, name: String, password: String): Result<User> {
        if (email.isBlank() || name.isBlank() || password.isBlank()) {
            return Result.failure(Exception("Thông tin không được để trống"))
        }
        if (!email.contains("@")) {
            return Result.failure(Exception("Email không hợp lệ"))
        }
        if (password.length < 6) {
            return Result.failure(Exception("Mật khẩu phải có ít nhất 6 ký tự"))
        }
        return authRepository.registerUser(email, name, password)
    }
}
package com.example.base_app.data.repository.impl

import com.example.base_app.data.local.dao.UserDao
import com.example.base_app.domain.model.User
import com.example.base_app.domain.repository.AuthRepository
import javax.inject.Inject
import java.util.UUID

//class AuthRepositoryImpl @Inject constructor(
//    private val userDao: UserDao
//) : AuthRepository {
//
//    override suspend fun registerUser(email: String, name: String, password: String): Result<User> {
//        return try {
//            val request = mapOf("email" to email, "name" to name, "password" to password)
//            val response = ApiClient.authApi.register(request)
//            if (response.isSuccessful) {
//                response.body()?.let { dto ->
//                    val user = User(id = dto.id, email = dto.email, name = dto.name)
//                    userDao.insertUser(user) // Lưu local nếu cần
//                    Result.success(user)
//                } ?: Result.failure(Exception("No response body"))
//            } else {
//                Result.failure(Exception("Register failed: ${response.errorBody()?.string()}"))
//            }
//        } catch (e: Exception) {
//            Result.failure(e)
//        }
//    }
//
//    override suspend fun loginUser(email: String, password: String): Result<User> {
//        return try {
//            val request = mapOf("email" to email, "password" to password)
//            val response = ApiClient.authApi.login(request)
//            if (response.isSuccessful) {
//                response.body()?.let { dto ->
//                    val user = User(id = dto.id, email = dto.email, name = dto.name)
//                    // Lưu token nếu API trả về (ví dụ: lưu SharedPreferences hoặc Room)
//                    userDao.insertUser(user.copy(isLoggedIn = true))
//                    Result.success(user)
//                } ?: Result.failure(Exception("No response body"))
//            } else {
//                Result.failure(Exception("Login failed: ${response.errorBody()?.string()}"))
//            }
//        } catch (e: Exception) {
//            Result.failure(e)
//        }
//    }
//
//    override suspend fun logout() {
//        // Clear token và login status
//    }
//
//    override suspend fun getCurrentUser(): User? {
//        return userDao.getCurrentUser()
//    }
//}

class AuthRepositoryImpl @Inject constructor(
    private val userDao: UserDao
) : AuthRepository {

    // Dữ liệu giả để kiểm tra đăng nhập
    private val mockUsers = listOf(
        Pair("test@example.com", "password123")
    )

    override suspend fun registerUser(email: String, name: String, password: String): Result<User> {
        // Giữ nguyên logic đăng ký nếu cần gọi API
        return Result.failure(Exception("Đăng ký chưa được triển khai với fake data"))
    }

    override suspend fun loginUser(email: String, password: String): Result<User> {
        return try {
            // Kiểm tra email/password với dữ liệu giả
            if (mockUsers.contains(Pair(email, password))) {
                val user = User(
                    id = UUID.randomUUID().toString(),
                    email = email,
                    name = "Test User",
                    token = "fake-token-${System.currentTimeMillis()}",
                    isLoggedIn = true
                )
                userDao.insertUser(user)
                Result.success(user)
            } else {
                Result.failure(Exception("Email hoặc mật khẩu không đúng"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun logout() {
        userDao.getCurrentUser()?.let {
            userDao.insertUser(it.copy(isLoggedIn = false, token = null))
        }
    }

    override suspend fun getCurrentUser(): User? {
        return userDao.getCurrentUser()
    }
}
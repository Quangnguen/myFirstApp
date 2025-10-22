package com.example.base_app.data.local.dao


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.base_app.domain.model.User

@Dao
interface UserDao {
    @Insert
    suspend fun insertUser(user: User)

    @Query("SELECT * FROM user WHERE isLoggedIn = 1 LIMIT 1")
    suspend fun getCurrentUser(): User?
}
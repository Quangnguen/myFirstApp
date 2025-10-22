package com.example.base_app.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User(
    @PrimaryKey val id: String,
    val email: String,
    val name: String,
    val isLoggedIn: Boolean = false,
    val token: String?
)
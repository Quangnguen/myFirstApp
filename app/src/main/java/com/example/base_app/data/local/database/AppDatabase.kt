package com.example.base_app.data.local.database


import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.base_app.domain.model.User
import com.example.base_app.data.local.dao.UserDao

@Database(entities = [User::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}
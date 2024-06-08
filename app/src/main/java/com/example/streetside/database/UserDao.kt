package com.example.streetside.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {

    @Insert
    suspend fun insert(user: User)

    @Query("SELECT * FROM Registration_table WHERE user_name LIKE :userName")
    suspend fun getUsername(userName: String): User?

    @Query("SELECT COUNT(*) FROM Registration_table WHERE user_name = :userName")
    suspend fun checkUsernameExists(userName: String): Int

    @Query("SELECT * FROM Registration_table WHERE user_name = :userName LIMIT 1")
    suspend fun getUserByUsername(userName: String): User?

}
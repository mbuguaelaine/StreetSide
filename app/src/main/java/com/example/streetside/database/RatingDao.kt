package com.example.streetside.database

import androidx.room.Dao
import androidx.room.Insert

@Dao
interface RatingDao {
    @Insert
    suspend fun insertRating(ratings: Ratings)
}
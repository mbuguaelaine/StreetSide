package com.example.streetside.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Ratings_table")
data class Ratings(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(name = "vendorName")
    val vendorName: String,

    @ColumnInfo(name = "rating")
    val rating: Int
)

package com.example.streetside.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Registration_table")
data class User (

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,

    @ColumnInfo(name = "user_name")
    var userName: String,

    @ColumnInfo(name = "first_name")
    var firstname: String? = null,

    @ColumnInfo(name = "last_name")
    var surname: String? = null,

    @ColumnInfo(name = "password_text")
    var pass: String,

    )
package com.example.streetside.model

import android.content.Context
import android.content.SharedPreferences

object Utilities {
    private const val PREF_NAME = "user_prefs"
    private const val KEY_USERNAME = "username"

    fun saveUsername(context: Context, username: String) {
        val sharedPreferences: SharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString(KEY_USERNAME, username)
        editor.apply()
    }

    fun getUsername(context: Context): String? {
        val sharedPreferences: SharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        return sharedPreferences.getString(KEY_USERNAME, null)
    }
}
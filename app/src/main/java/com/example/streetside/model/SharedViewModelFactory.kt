package com.example.streetside.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.streetside.database.UserRepository

//class SharedViewModelFactory(private val userRepository: UserRepository) : ViewModelProvider.Factory {
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        if (modelClass.isAssignableFrom(SharedViewModel::class.java)) {
//            @Suppress("UNCHECKED_CAST")
//            return SharedViewModel() as T
//        }
//        throw IllegalArgumentException("Unknown ViewModel class")
//    }
//}
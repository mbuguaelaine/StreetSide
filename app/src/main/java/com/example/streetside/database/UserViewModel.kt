package com.example.streetside.database

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

open class UserViewModel(application: Application): AndroidViewModel(application) {

    private val repository: UserRepository
    private val _user = MutableLiveData<User?>()
    val user: LiveData<User?> get() = _user

    init {
        val userDao = UserDatabase.getDatabase(application).userDao()
        val ratingDao = UserDatabase.getDatabase(application).ratingDao()
        repository = UserRepository(userDao, ratingDao)
    }

    fun insert(user: User, onSuccess: (String) -> Unit, onError: (String) -> Unit){
        viewModelScope.launch {
            if (repository.checkUsernameExists(user.userName)) {
                withContext(Dispatchers.Main) {
                    onError("Username already exists")
                }
            } else {
                repository.insert(user)
                withContext(Dispatchers.Main) {
                    onSuccess("Registration Successful")
                }
            }
        }
    }

    open fun getUserName(userName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val user = repository.getUserName(userName)
            _user.postValue(user)
        }
    }

    fun insertRating(ratings: Ratings, onSuccess: (String) -> Unit, onError: (String) -> Unit) {
        viewModelScope.launch {
            try {
                repository.insertRating(ratings)
                withContext(Dispatchers.Main) {
                    onSuccess("Rating recorded!")
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    onError("Error recording rating!")
                }
            }
        }
    }
}
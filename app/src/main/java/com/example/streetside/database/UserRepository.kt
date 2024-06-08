package com.example.streetside.database

class UserRepository(private val userDao: UserDao, private val ratingDao: RatingDao) {

    suspend fun insert(user: User) {
        return userDao.insert(user)
    }

    suspend fun getUserName(userName: String):User?{
        return userDao.getUsername(userName)
    }

    suspend fun checkUsernameExists(userName: String): Boolean {
        return userDao.checkUsernameExists(userName) > 0
    }

    suspend fun getUserByUsername(username: String): User? {
        return userDao.getUserByUsername(username)
    }

    suspend fun insertRating(ratings: Ratings){
        ratingDao.insertRating(ratings)
    }

}
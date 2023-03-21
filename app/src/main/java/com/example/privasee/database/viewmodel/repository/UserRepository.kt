package com.example.privasee.database.viewmodel.repository

import androidx.lifecycle.LiveData
import com.example.privasee.database.viewmodel.repository.dao.UserDao
import com.example.privasee.database.model.User

class UserRepository(private val userDao: UserDao) {

    val readAllDataLive: LiveData<List<User>> = userDao.readAllDataLive()

    fun getOwnerId(isOwner: Boolean): Int {
        return userDao.getOwnerId(isOwner)
    }

    fun readAllData(): List<User> {
        return userDao.readAllData()
    }

    fun readAllUserId(): List<Int> {
        return userDao.readAllUserId()
    }

    suspend fun addUser(user: User) {
        userDao.addUser(user)
    }

    suspend fun updateUser(user: User) {
        userDao.updateUser(user)
    }

    suspend fun deleteUser(user: User) {
        userDao.deleteUser(user)
    }

}
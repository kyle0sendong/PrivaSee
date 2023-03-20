package com.example.privasee.database.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.privasee.database.PrivaSeeDatabase
import com.example.privasee.database.model.User
import com.example.privasee.database.viewmodel.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: UserRepository

    val readAllDataLive: LiveData<List<User>>
    val readAllData: List<User>

    init {
        val userDao = PrivaSeeDatabase.getDatabase(application).userDao()
        repository = UserRepository(userDao)
        readAllDataLive = repository.readAllDataLive
        readAllData = repository.readAllData
    }


    fun addUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addUser(user)
        }
    }


    fun updateUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateUser(user)
        }
    }


    fun deleteUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteUser(user)
        }
    }

}
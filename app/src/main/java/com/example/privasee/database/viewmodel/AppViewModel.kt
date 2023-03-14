package com.example.privasee.database.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.privasee.database.PrivaSeeDatabase
import com.example.privasee.database.model.App
import com.example.privasee.database.viewmodel.repository.AppRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AppViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: AppRepository

    init {
        val appDao = PrivaSeeDatabase.getDatabase(application).appDao()
        repository = AppRepository(appDao)
    }

    fun addApp(app: App) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addApp(app)
        }
    }

}
package com.example.privasee.database.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
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

    val readAllDataLive: LiveData<List<App>> = repository.readAllDataLive

    fun readAllData(): List<App> {
        return repository.readAllData()
    }

    fun readAllAppId(): List<Int> {
        return repository.readAllAppId()
    }

    fun addApp(app: App) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addApp(app)
        }
    }

}
package com.example.privasee.database.viewmodel.repository

import androidx.lifecycle.LiveData
import com.example.privasee.database.model.App
import com.example.privasee.database.viewmodel.repository.dao.AppDao

class AppRepository(private val appDao: AppDao) {

    val readAllDataLive: LiveData<List<App>> = appDao.readAllDataLive()

    suspend fun addApp(app: App) {
        appDao.addApp(app)
    }

    fun readAllData(): List<App> {
        return appDao.readAllData()
    }

    fun readAllAppId(): List<Int> {
        return appDao.readAllAppId()
    }

}
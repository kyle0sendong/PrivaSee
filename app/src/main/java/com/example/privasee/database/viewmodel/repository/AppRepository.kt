package com.example.privasee.database.viewmodel.repository

import androidx.lifecycle.LiveData
import androidx.room.Query
import com.example.privasee.database.model.App
import com.example.privasee.database.viewmodel.repository.dao.AppDao

class AppRepository(private val appDao: AppDao) {

    fun getAllDataLive(): LiveData<List<App>> {
        return appDao.getAllDataLive()
    }

    suspend fun addApp(app: App) {
        appDao.addApp(app)
    }

    fun getAllData(): List<App> {
        return appDao.getAllData()
    }

    fun getPackageName(appId: Int): String {
        return appDao.getPackageName(appId)
    }

}
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

    fun getAllAppId(): List<Int> {
        return appDao.getAllAppId()
    }

    fun getAllData(): List<App> {
        return appDao.getAllData()
    }

    fun getAppInfo(appId: Int): App {
        return appDao.getAppInfo(appId)
    }

    fun getAppName(appId: Int): String {
        return appDao.getAppName(appId)
    }

    fun getPackageName(appId: Int): String {
        return appDao.getPackageName(appId)
    }

}
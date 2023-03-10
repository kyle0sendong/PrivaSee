package com.example.privasee.database.viewmodel.repository

import androidx.lifecycle.LiveData
import com.example.privasee.database.model.App
import com.example.privasee.database.viewmodel.repository.dao.AppDao

class AppRepository(private val appDao: AppDao) {

    val readAllData: LiveData<List<App>> = appDao.readAllData()

    suspend fun addApp(app: App) {
        appDao.addApp(app)
    }

}
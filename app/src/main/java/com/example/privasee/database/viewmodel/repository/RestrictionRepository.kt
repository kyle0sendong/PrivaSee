package com.example.privasee.database.viewmodel.repository

import androidx.lifecycle.LiveData
import com.example.privasee.database.model.Restriction
import com.example.privasee.database.viewmodel.repository.dao.RestrictionDao

class RestrictionRepository(private val restrictionDao: RestrictionDao) {

    val getAllDataLive: LiveData<List<Restriction>> = restrictionDao.getAllDataLive()

    suspend fun addRestriction(restriction: Restriction) {
        restrictionDao.addRestriction(restriction)
    }

    fun getAllData(): List<Restriction> {
        return restrictionDao.getAllData()
    }

    fun getAllMonitoredApps(userId: Int): LiveData<List<Restriction>> {
        return restrictionDao.getAllMonitoredApps(userId)
    }

    fun getAllUnmonitoredApps(userId: Int): LiveData<List<Restriction>> {
        return restrictionDao.getAllUnmonitoredApps(userId)
    }

    fun updateMonitored(restrictionId: Int, isMonitored: Boolean) {
        return restrictionDao.updateMonitored(restrictionId, isMonitored)
    }

}
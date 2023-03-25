package com.example.privasee.database.viewmodel.repository

import androidx.lifecycle.LiveData
import com.example.privasee.database.model.Restriction
import com.example.privasee.database.viewmodel.repository.dao.RestrictionDao

class RestrictionRepository(private val restrictionDao: RestrictionDao) {

    suspend fun addRestriction(restriction: Restriction) {
        restrictionDao.addRestriction(restriction)
    }

    fun getAllMonitoredApps(userId: Int): LiveData<List<Restriction>> {
        return restrictionDao.getAllMonitoredApps(userId)
    }

    fun getAllUnmonitoredApps(userId: Int): LiveData<List<Restriction>> {
        return restrictionDao.getAllUnmonitoredApps(userId)
    }

    fun updateMonitoredApps(restrictionId: Int, isMonitored: Boolean) {
        return restrictionDao.updateMonitoredApps(restrictionId, isMonitored)
    }

}
package com.example.privasee.database.viewmodel.repository

import androidx.lifecycle.LiveData
import com.example.privasee.database.model.Restriction
import com.example.privasee.database.viewmodel.repository.dao.RestrictionDao

class RestrictionRepository(private val restrictionDao: RestrictionDao) {

    val readAllDataLive: LiveData<List<Restriction>> = restrictionDao.readAllDataLive()
    val readAllData: List<Restriction> = restrictionDao.readAllData()

    suspend fun addRestriction(restriction: Restriction) {
        restrictionDao.addRestriction(restriction)
    }

}
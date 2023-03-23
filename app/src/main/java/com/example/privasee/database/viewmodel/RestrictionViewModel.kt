package com.example.privasee.database.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.privasee.database.PrivaSeeDatabase
import com.example.privasee.database.model.Restriction
import com.example.privasee.database.viewmodel.repository.RestrictionRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RestrictionViewModel(application: Application): AndroidViewModel(application) {

    private val repository: RestrictionRepository

    init {
        val restrictionDao = PrivaSeeDatabase.getDatabase(application).restrictionDao()
        repository = RestrictionRepository(restrictionDao)
    }

    val getAllDataLive: LiveData<List<Restriction>> = repository.getAllDataLive

    fun addRestriction(restriction: Restriction) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addRestriction(restriction)
        }
    }

    fun getAllMonitoredApps(userId: Int): LiveData<List<Restriction>> {
        return repository.getAllMonitoredApps(userId)
    }

    fun getAllUnmonitoredApps(userId: Int): LiveData<List<Restriction>> {
        return repository.getAllUnmonitoredApps(userId)
    }

    fun updateMonitored(restrictionId: Int, isMonitored: Boolean) {
        return repository.updateMonitored(restrictionId, isMonitored)
    }
}
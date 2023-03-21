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

    val readAllDataLive: LiveData<List<Restriction>> = repository.readAllDataLive

    fun addRestriction(restriction: Restriction) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addRestriction(restriction)
        }
    }

    fun readAllData(): List<Restriction> {
        return readAllData()
    }
}
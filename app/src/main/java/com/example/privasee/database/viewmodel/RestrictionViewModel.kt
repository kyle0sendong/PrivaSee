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

    val readAllData: List<Restriction>
    val readAllDataLive: LiveData<List<Restriction>>

    init {
        val restrictionDao = PrivaSeeDatabase.getDatabase(application).restrictionDao()
        repository = RestrictionRepository(restrictionDao)
        readAllData = repository.readAllData
        readAllDataLive = repository.readAllDataLive
    }

    fun addRestriction(restriction: Restriction) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addRestriction(restriction)
        }
    }
}
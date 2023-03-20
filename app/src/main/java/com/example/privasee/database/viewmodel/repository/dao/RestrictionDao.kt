package com.example.privasee.database.viewmodel.repository.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.privasee.database.model.Restriction

@Dao
interface RestrictionDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun addRestriction(restriction: Restriction)

    @Query("SELECT * from restriction")
    fun readAllData(): List<Restriction>

    @Query("SELECT * from restriction")
    fun readAllDataLive(): LiveData<List<Restriction>>

}
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

    @Query("SELECT * FROM restriction")
    fun getAllData(): List<Restriction>

    @Query("SELECT * FROM restriction")
    fun getAllDataLive(): LiveData<List<Restriction>>

    @Query("SELECT * FROM restriction " +
            "WHERE monitored = true " +
            "AND userId = :userId")
    fun getAllMonitoredApps(userId: Int): LiveData<List<Restriction>>

    @Query("SELECT * FROM restriction " +
            "WHERE monitored = false " +
            "AND userId = :userId")
    fun getAllUnmonitoredApps(userId: Int): LiveData<List<Restriction>>

    @Query("UPDATE restriction SET monitored = :isMonitored " +
            "WHERE id = :restrictionId")
    fun updateMonitored(restrictionId: Int, isMonitored: Boolean)


}
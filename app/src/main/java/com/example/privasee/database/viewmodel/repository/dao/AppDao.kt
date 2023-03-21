package com.example.privasee.database.viewmodel.repository.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.privasee.database.model.App

@Dao
interface AppDao {

    @Query("SELECT * FROM app")
    fun readAllDataLive(): LiveData<List<App>>

    @Query("SELECT * FROM app")
    fun readAllData(): List<App>

    @Query("SELECT id FROM app")
    fun readAllAppId(): List<Int>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun addApp(app: App)

}
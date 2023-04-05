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
    fun getAllDataLive(): LiveData<List<App>>

    @Query("SELECT * FROM app")
    fun getAllData(): List<App>

    @Query("SELECT packageName FROM app WHERE id = :appId")
    fun getPackageName(appId: Int): String

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun addApp(app: App)

}
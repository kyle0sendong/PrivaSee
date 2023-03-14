package com.example.privasee.database.viewmodel.repository.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.privasee.database.model.App

@Dao
interface AppDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun addApp(app: App)

    @Query ("SELECT * from app")
    fun readAllData() : LiveData<List<App>>

}
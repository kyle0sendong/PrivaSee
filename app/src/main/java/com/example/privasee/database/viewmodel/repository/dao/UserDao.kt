package com.example.privasee.database.viewmodel.repository.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.privasee.database.model.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user: User)

    @Query("SELECT * FROM user ORDER BY id ASC")
    fun readAllDataLive(): LiveData<List<User>>

    @Query("SELECT * FROM user ORDER BY id ASC")
    fun readAllData(): List<User>

    @Query("SELECT id FROM user")
    fun readAllUserId(): List<Int>

    @Delete
    suspend fun deleteUser(user: User)

    @Update
    suspend fun updateUser(user: User)

}
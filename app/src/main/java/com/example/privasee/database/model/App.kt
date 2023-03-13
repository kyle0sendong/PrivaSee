package com.example.privasee.database.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity (tableName = "app")
data class App(
    @PrimaryKey val packageName : String,
    val appName : String,
)

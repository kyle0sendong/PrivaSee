package com.example.privasee.database.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity (tableName = "app",
         foreignKeys = [
             ForeignKey(
                entity = User::class,
                parentColumns = ["id"],
                childColumns = ["userId"],
                onDelete = ForeignKey.CASCADE
            )
         ],
        indices = [
            Index(value = ["userId"])
        ]
)

data class App(
    @PrimaryKey val packageName : String,
    val appName : String,
    val restrict : Boolean,
    val userId : Int
)

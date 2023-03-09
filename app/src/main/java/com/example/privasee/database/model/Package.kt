package com.example.privasee.database.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity (tableName = "package",
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

data class Package(
    @PrimaryKey (autoGenerate = true) val id : Int,
    val packageName : String,
    val appName : String,
    val restrict : Boolean,
    val userId : Int
)

package com.example.privasee.database.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity (tableName = "monitor",
        foreignKeys = [
            ForeignKey(
                entity = User::class,
                parentColumns = ["id"],
                childColumns = ["userId"],
                onDelete = ForeignKey.NO_ACTION // Don't delete any records
            ),
            ForeignKey(
                entity = App::class,
                parentColumns = ["packageName"],
                childColumns = ["packageName"],
                onDelete = ForeignKey.NO_ACTION // Don't delete any records
            )
        ],
        indices = [
            Index(value = ["userId"]),
            Index(value = ["packageName"])
        ]
)

data class Monitor(
    @PrimaryKey (autoGenerate = true) val id : Int,
    val dateAccess : Long,
    val timeAccess : Long,
    val userId : Int,
    val packageName : String
)

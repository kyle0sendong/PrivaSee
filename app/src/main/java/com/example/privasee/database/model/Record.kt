package com.example.privasee.database.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity (tableName = "record",
        foreignKeys = [
            ForeignKey(
                entity = App::class,
                parentColumns = ["id"],
                childColumns = ["packageId"],
                onDelete = ForeignKey.NO_ACTION // Don't delete any records
            )
        ],
        indices = [
            Index(value = ["packageId"])
        ]
)

data class Record(
    @PrimaryKey (autoGenerate = true) val id : Int,
    val dateAccess : Long,
    val timeAccess : Long,
    val packageId : Int
)

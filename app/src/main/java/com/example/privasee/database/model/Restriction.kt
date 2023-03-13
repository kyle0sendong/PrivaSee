package com.example.privasee.database.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity( tableName = "restriction",
        foreignKeys = [
            ForeignKey(
                entity = User::class,
                parentColumns = ["id"],
                childColumns = ["user_id"],
                onDelete = ForeignKey.CASCADE
            ),
            ForeignKey(
                entity = App::class,
                parentColumns = ["packageName"],
                childColumns = ["packageName"],
                onDelete = ForeignKey.CASCADE
            )
        ],
        indices = [
            Index(value = ["userId"]),
            Index(value = ["packageName"])
        ]
)

data class Restriction(
    @PrimaryKey (autoGenerate = true) val id : Int,
    val restriction : Boolean,
    val user_id : Int,
    val packageName : String
)

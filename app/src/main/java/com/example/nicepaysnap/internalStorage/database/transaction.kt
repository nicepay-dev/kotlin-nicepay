package com.example.nicepaysnap.internalStorage.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transaction")
data class transaction(
    @PrimaryKey
    val txId : String,
    val amount : Int,
    val vaNo : Int
)

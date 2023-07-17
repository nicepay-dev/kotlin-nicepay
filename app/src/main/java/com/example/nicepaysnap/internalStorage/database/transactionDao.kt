package com.example.nicepaysnap.internalStorage.database

import androidx.room.Insert
import androidx.room.OnConflictStrategy

interface transactionDao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addTransaction(trxDB: transaction)

}

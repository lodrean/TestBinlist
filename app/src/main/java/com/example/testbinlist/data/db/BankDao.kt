package com.example.testbinlist.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface BankDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBank(bank: BankDb)

    @Delete
    suspend fun deleteBank(bank: BankDb)

    @Query("SELECT * FROM banks")
    fun getAll(): Flow<List<BankDb>>

    @Query("SELECT name FROM banks")
    fun getAllBankNames(): List<String>
}
package com.example.testbinlist.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CardDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCard(card: CardDb)

    @Delete
    suspend fun deleteCard(card: CardDb)

    @Query("SELECT * FROM cards")
    suspend fun getAll(): List<CardDb>

    @Query("SELECT cardNumber FROM cards")
    suspend fun getAllNumbers(): List<String>
}
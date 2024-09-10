package com.example.testbinlist.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CardDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCard(card: CardDb)

    @Delete
    suspend fun deleteCard(card: CardDb)

    @Query("SELECT * FROM cards")
    fun getAll(): Flow<List<CardDb>>

    @Query("SELECT cardId FROM cards")
    fun getAllIds(): List<String>
}
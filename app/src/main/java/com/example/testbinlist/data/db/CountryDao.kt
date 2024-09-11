package com.example.testbinlist.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CountryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCountry(countryDb: CountryDb)

    @Query("SELECT * FROM countries")
    fun getAll(): Flow<List<CountryDb>>

    @Query("SELECT name FROM countries")
    suspend fun getAllCountryNames(): List<String>

    @Query("SELECT * FROM countries WHERE name = :name")
    suspend fun findCountryByName(name: String): CountryDb
}
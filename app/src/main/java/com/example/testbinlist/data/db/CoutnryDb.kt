package com.example.testbinlist.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "countries")
class CountryDb(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "countryId")
    val countryId: Int,
    @ColumnInfo(name = "alpha2")
    val alpha2: String,
    @ColumnInfo(name = "currency")
    val currency: String,
    @ColumnInfo(name = "emoji")
    val emoji: String,
    @ColumnInfo(name = "latitude")
    val latitude: Int,
    @ColumnInfo(name = "longitude")
    val longitude: Int,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "numeric")
    val numeric: String
)


package com.example.testbinlist.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "countries")
class CountryDb(
    @PrimaryKey @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "latitude") val latitude: Int,
    @ColumnInfo(name = "longitude") val longitude: Int,
)


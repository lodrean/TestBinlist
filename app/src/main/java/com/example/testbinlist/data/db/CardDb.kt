package com.example.testbinlist.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "Cards")
class CardDb(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "cardId")
    val id: Int,
    @ColumnInfo(name = "bankName")
    val bankName: String?,
    @ColumnInfo(name = "brand")
    val brand: String,
    @ColumnInfo(name = "countryName")
    val countryName: String?,
    @ColumnInfo(name = "scheme")
    val scheme: String,
    @ColumnInfo(name = "type")
    val type: String
)
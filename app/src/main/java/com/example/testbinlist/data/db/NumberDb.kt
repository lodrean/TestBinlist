package com.example.testbinlist.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "numbers")
class Number(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "numberId")
    val id: Int,
    @ColumnInfo(name = "length")
    val length: Int,
    @ColumnInfo(name = "luhn")
    val luhn: Boolean
)
package com.example.testbinlist.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "banks")
class BankDb (
    @PrimaryKey
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "city")
    val city: String,
    @ColumnInfo(name = "phone")
    val phone: String,
    @ColumnInfo(name = "url")
    val url: String
)
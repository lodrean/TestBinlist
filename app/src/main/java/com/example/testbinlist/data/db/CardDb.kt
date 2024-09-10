package com.example.testbinlist.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.testbinlist.domain.Bank
import com.example.testbinlist.domain.Country

@Entity (tableName = "Cards")
class CardDb (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "cardId")
    val id: Int,
    @ColumnInfo(name = "bankId")
    val bankId: Int,
    @ColumnInfo(name = "brand")
    val brand: String,
    @ColumnInfo(name = "countryId")
    val countryId: String,
    @ColumnInfo(name = "numberId")
    val numberId: Int,
    @ColumnInfo(name = "prepaid")
    val prepaid: Boolean = false,
    @ColumnInfo(name = "scheme")
    val scheme: String,
    @ColumnInfo(name = "type")
    val type: String
)
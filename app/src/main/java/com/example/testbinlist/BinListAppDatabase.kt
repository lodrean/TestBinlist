package com.example.testbinlist

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.testbinlist.data.db.BankDao
import com.example.testbinlist.data.db.BankDb
import com.example.testbinlist.data.db.CardDao
import com.example.testbinlist.data.db.CardDb
import com.example.testbinlist.data.db.CountryDao
import com.example.testbinlist.data.db.CountryDb


@Database(
    version = 1,
    entities = [
        BankDb::class,
        CardDb::class,
        CountryDb::class,
    ]
)
abstract class BinListAppDatabase : RoomDatabase() {

    abstract fun bankDao(): BankDao

    abstract fun cardDao(): CardDao

    abstract fun countryDao(): CountryDao

}


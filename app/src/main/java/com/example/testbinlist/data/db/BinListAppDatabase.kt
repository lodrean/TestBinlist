package com.example.testbinlist.data.db

import androidx.room.Database
import androidx.room.RoomDatabase


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


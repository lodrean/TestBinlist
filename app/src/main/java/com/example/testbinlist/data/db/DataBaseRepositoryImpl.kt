package com.example.testbinlist.data.db

import com.example.testbinlist.BinListAppDatabase
import com.example.testbinlist.domain.Bank
import com.example.testbinlist.domain.CardInfo
import com.example.testbinlist.domain.Country
import com.example.testbinlist.domain.DataBaseRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class DataBaseRepositoryImpl(private val database: BinListAppDatabase) : DataBaseRepository {
    override suspend fun getHistory(): Flow<List<CardInfo>> = flow {
        database.cardDao().getAll().map {
            it.map {
                val bankDb = database.bankDao().findBankByName(it.bankName)
                val countryDb = database.countryDao().findCountryByName(it.countryName)
                it.toCardInfo(bankDb, countryDb)
            }
        }
    }

    override suspend fun putCardIntoDb(cardInfo: CardInfo) {
        if (!isBankInDb(cardInfo.bank.name)) {
            val bank = cardInfo.bank
            database.bankDao().insertBank(bank.toBankDb())
        }
        if (!isCountryInDb(cardInfo.country.name)) {
            val country = cardInfo.country
            database.countryDao().insertCountry(country.toCountryDb())
        }
        database.cardDao().insertCard(cardInfo.toCardDb())
    }

    private suspend fun isBankInDb(bankName: String?): Boolean {
        val banks = database.bankDao().getAllBankNames()
        return bankName in banks
    }

    private suspend fun isCountryInDb(countryName: String?): Boolean {
        val countries = database.countryDao().getAllCountryNames()
        return countryName in countries
    }

    private fun Bank.toBankDb(): BankDb {
        return BankDb(this.name, this.city, this.phone, this.url)
    }

    private fun BankDb.toBank(): Bank {
        return Bank(this.name, this.city, this.phone, this.url)
    }

    private fun Country.toCountryDb(): CountryDb {
        return CountryDb(this.name, this.latitude, this.longitude)
    }

    private fun CountryDb.toCountry(): Country {
        return Country(this.name, this.latitude, this.longitude)
    }

    private fun CardInfo.toCardDb(): CardDb {
        return CardDb(
            0,
            this.cardNumber,
            this.bank.name,
            this.brand,
            this.country.name,
            this.scheme,
            this.type
        )
    }

    private fun CardDb.toCardInfo(bankDb: BankDb, countryDb: CountryDb): CardInfo {
        return CardInfo(
            this.cardNumber,
            bankDb.toBank(),
            this.brand,
            countryDb.toCountry(),
            this.scheme,
            this.type
        )
    }
}
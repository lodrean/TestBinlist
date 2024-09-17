package com.example.testbinlist.data

import com.example.testbinlist.BinListAppDatabase
import com.example.testbinlist.data.db.BankDb
import com.example.testbinlist.data.db.CardDb
import com.example.testbinlist.data.db.CountryDb
import com.example.testbinlist.domain.Bank
import com.example.testbinlist.domain.CardInfo
import com.example.testbinlist.domain.Country
import com.example.testbinlist.domain.DataBaseRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class DataBaseRepositoryImpl(private val database: BinListAppDatabase) : DataBaseRepository {
    override suspend fun getHistory(): Flow<List<CardInfo>> = flow {
        val data = mutableListOf<CardInfo>()
        database.cardDao().getAll().map { cardDb ->
            val bankDb = database.bankDao().findBankByName(cardDb.bankName)
            val countryDb = database.countryDao().findCountryByName(cardDb.countryName)
            data.add(cardDb.toCardInfo(bankDb ?: BankDb("", "", "", ""), countryDb))
        }
        emit(data)
    }

    override suspend fun putCardIntoDb(cardInfo: CardInfo) {
        if (!isBankInDb(cardInfo.bank.name) && cardInfo.bank.name.isNotBlank()) {
            val bank = cardInfo.bank
            database.bankDao().insertBank(bank.toBankDb())
        }
        if (!isCountryInDb(cardInfo.country.name) && cardInfo.country.name.isNotBlank()) {
            val country = cardInfo.country
            database.countryDao().insertCountry(country.toCountryDb())
        }
        if (!isCardInDb(cardInfo) && cardInfo.cardNumber.isNotBlank()) {
            database.cardDao().insertCard(cardInfo.toCardDb())
        }
    }

    private suspend fun isCardInDb(cardInfo: CardInfo): Boolean {
        val cards = database.cardDao().getAllNumbers()
        return cardInfo.cardNumber in cards
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
        return Bank(this.city, this.name, this.phone, this.url)
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
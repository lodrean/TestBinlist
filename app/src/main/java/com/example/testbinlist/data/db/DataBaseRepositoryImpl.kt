package com.example.testbinlist.data.db

import com.example.testbinlist.BinListAppDatabase
import com.example.testbinlist.domain.Bank
import com.example.testbinlist.domain.CardInfo
import com.example.testbinlist.domain.DataBaseRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataBaseRepositoryImpl(private val database: BinListAppDatabase) : DataBaseRepository {
    override suspend fun getHistory(): Flow<List<CardInfo>> {
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
        database.cardDao().insertCard(cardInfo.toCardDb())
    }

    private suspend fun isBankInDb(bankName: String?): Boolean {
        val banks = database.bankDao().getAllBankNames()
        return bankName in banks
    }

    private fun Bank.toBankDb(): BankDb {

        return BankDb(this.name, this.city, this.phone, this.url)

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

    private fun CardDb.toCardInfo(bank: BankDb, country: CountryDb): CardInfo {
        return CardInfo(
            this.cardNumber,
            bank,
            this.brand,
            this.country,
            this.scheme,
            this.type
        )
    }
}
package com.example.testbinlist.data.db

import com.example.testbinlist.BinListAppDatabase
import com.example.testbinlist.domain.Bank
import com.example.testbinlist.domain.CardInfo
import com.example.testbinlist.domain.DataBaseRepository
import kotlinx.coroutines.flow.Flow

class DataBaseRepositoryImpl(private val database: BinListAppDatabase) : DataBaseRepository {
    override suspend fun getHistory(): Flow<List<CardInfo>> {
        TODO("Not yet implemented")
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
}
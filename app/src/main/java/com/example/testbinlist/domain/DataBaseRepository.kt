package com.example.testbinlist.domain

import kotlinx.coroutines.flow.Flow

interface DataBaseRepository {
    fun getHistory(): Flow<List<CardInfo>>
    suspend fun putCardIntoDb(cardInfo: CardInfo)
}
package com.example.testbinlist.domain

import kotlinx.coroutines.flow.Flow

interface GetCardInfoUseCase {
    suspend fun execute(query: String): Flow<Pair<CardInfo, String>>
}
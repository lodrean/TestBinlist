package com.example.testbinlist.domain

import kotlinx.coroutines.flow.Flow

interface GetCardInfoUseCase {
    suspend fun execute(): Flow<CardInfo>
}
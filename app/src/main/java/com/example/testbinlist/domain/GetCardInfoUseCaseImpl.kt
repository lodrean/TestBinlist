package com.example.testbinlist.domain

import kotlinx.coroutines.flow.Flow

class GetCardInfoUseCaseImpl(private val repository: SearchRepository, ): GetCardInfoUseCase{
    override suspend fun execute(): Flow<CardInfo> {
        TODO("Not yet implemented")
    }
}
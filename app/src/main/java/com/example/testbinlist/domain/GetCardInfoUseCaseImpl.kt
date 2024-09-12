package com.example.testbinlist.domain

import kotlinx.coroutines.flow.Flow

class GetCardInfoUseCaseImpl(private val repository: SearchRepository): GetCardInfoUseCase{
    override suspend fun execute(query: String): Flow<Pair<CardInfo, String>> {
       return repository.searchCardInfo(query)
    }
}
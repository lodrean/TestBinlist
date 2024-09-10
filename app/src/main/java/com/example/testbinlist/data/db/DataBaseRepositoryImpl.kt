package com.example.testbinlist.data.db

import com.example.testbinlist.domain.CardInfo
import com.example.testbinlist.domain.DataBaseRepository
import kotlinx.coroutines.flow.Flow

class DataBaseRepositoryImpl: DataBaseRepository {
    override suspend fun getHistory(): Flow<List<CardInfo>> {
        TODO("Not yet implemented")
    }

}
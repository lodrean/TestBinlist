package com.example.testbinlist.domain

import kotlinx.coroutines.flow.Flow

interface DataBaseRepository {
    suspend fun getHistory(): Flow<List<CardInfo>>


}
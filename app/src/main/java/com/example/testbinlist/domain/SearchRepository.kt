package com.example.testbinlist.domain

import kotlinx.coroutines.flow.Flow

interface SearchRepository {
    fun fetchCardInfo(query: String): Flow<Pair<CardInfo, String>>
}
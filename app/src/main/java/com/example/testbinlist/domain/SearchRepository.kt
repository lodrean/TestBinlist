package com.example.testbinlist.domain

import kotlinx.coroutines.flow.Flow

interface SearchRepository {
    fun searchCardInfo(query: String): Flow<CardInfo>
    fun getCountryCoordinate(country: String)
}
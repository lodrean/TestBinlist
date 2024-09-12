package com.example.testbinlist.domain

import com.example.testbinlist.util.ApiOperation
import kotlinx.coroutines.flow.Flow

interface SearchRepository {
    fun searchCardInfo(query: String): Flow<Pair<CardInfo, String>>
    fun getCountryCoordinate(country: String)
}
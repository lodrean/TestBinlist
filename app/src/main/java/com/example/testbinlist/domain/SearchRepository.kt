package com.example.testbinlist.domain

interface SearchRepository {
    fun searchCardInfo(query: String) : CardInfo
    fun getCountryCoordinate(country: String)
}
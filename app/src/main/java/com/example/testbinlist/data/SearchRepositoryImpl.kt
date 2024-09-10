package com.example.testbinlist.data

import com.example.testbinlist.data.network.NetworkClient
import com.example.testbinlist.domain.CardInfo
import com.example.testbinlist.domain.SearchRepository
import kotlinx.coroutines.flow.flow

class SearchRepositoryImpl(private val networkClient: NetworkClient) : SearchRepository {

    override fun searchCardInfo(query: String) = flow<CardInfo> {
        emit(networkClient.doRequest(query))
    }

    override fun getCountryCoordinate(country: String) {
        TODO("Not yet implemented")
    }

    private fun saveCountryToDB() {
        //ToDo
    }

    private fun saveBankToDB() {
        //ToDo
    }

    private fun saveNumberToDB() {
        //ToDo
    }
}
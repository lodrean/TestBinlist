package com.example.testbinlist.data

import com.example.testbinlist.data.network.NetworkClient
import com.example.testbinlist.domain.CardInfo
import com.example.testbinlist.domain.SearchRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

class SearchRepositoryImpl(private val networkClient: NetworkClient) : SearchRepository {

    override fun fetchCardInfo(query: String): Flow<Pair<CardInfo, String>> = flow {
        lateinit var result: Pair<CardInfo, String>
        withContext(Dispatchers.IO) {
            networkClient.doRequest(query).onSuccess {
                result = Pair(it, "")
            }.onFailure {
                result = Pair(CardInfo(), it.message ?: "Неизвестная ошибка")
            }
        }
        emit(result)
    }
}

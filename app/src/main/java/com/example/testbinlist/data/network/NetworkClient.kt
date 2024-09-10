package com.example.testbinlist.data.network

import com.example.testbinlist.domain.CardInfo


interface NetworkClient {
    suspend fun doRequest(id: String) : CardInfo
}

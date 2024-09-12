package com.example.testbinlist.data.network

import com.example.testbinlist.domain.CardInfo
import com.example.testbinlist.util.ApiOperation


interface NetworkClient {
    suspend fun doRequest(id: String) : ApiOperation<CardInfo>
}

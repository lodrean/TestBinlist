package com.example.testbinlist.data.network

import com.example.testbinlist.data.dto.CardDto

interface NetworkClient {
    suspend fun getBinInfo(id: String): CardDto
}
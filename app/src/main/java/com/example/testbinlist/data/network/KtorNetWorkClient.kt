package com.example.testbinlist.data.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.example.testbinlist.data.dto.CardDto
import com.example.testbinlist.data.dto.toDomain
import com.example.testbinlist.domain.CardInfo
import com.example.testbinlist.util.ApiOperation
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class KtorNetworkClient(
    private val apiClient: HttpClient,
    private val context: Context
) : NetworkClient {

    override suspend fun doRequest(id: String): ApiOperation<CardInfo> {
        return safeApiCall {
            apiClient.get(id).body<CardDto>()
        }.mapSuccess {
            it.toDomain(id)
        }
    }

    private fun isConnected(): Boolean {
        val connectivityManager = context.getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager
        val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        if (capabilities != null) {
            when {
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> return true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> return true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> return true
            }
        }
        return false
    }

    private inline fun <T> safeApiCall(apiCall: () -> T): ApiOperation<T> {
        return try {
            if (isConnected()) {
                ApiOperation.Success(data = apiCall())
            } else {
                ApiOperation.Failure(exception = Exception("No internet connection"))
            }
        } catch (e: Exception) {
            ApiOperation.Failure(exception = e)
        }
    }
}

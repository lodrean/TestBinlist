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
    /*private val сlient = HttpClient(OkHttp) {
        defaultRequest { url("https://lookup.binlist.net/") }
        install(Logging) {
            logger = Logger.SIMPLE
        }
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
            })
        }id
    }*/

    override suspend fun doRequest(id: String): ApiOperation<CardInfo> {
        return safeApiCall {
                apiClient.get(id).body<CardDto>().toDomain()
        }
    }
    /*return apiClient.get("id")*/

    /*private suspend fun getVacancies(request: VacanciesSearchRequest): Response {
        return withContext(defaultDispatcher) {
            try {
                hhService.getVacancies(request.toMap())
                    .apply { resultCode = NetworkClient.HTTP_SUCCESS }
            } catch (e: HttpException) {
                Response().apply { resultCode = e.code() }
            } catch (_: SocketTimeoutException) {
                Response().apply { resultCode = NetworkClient.HTTP_SERVER_ERROR }
            }
        }*/


    /*  override suspend fun doRequest(dto: Any): Response {

          if (!isConnected()) {
              return Response().apply { resultCode = NO_CONNECTION }
          }
          if (dto !is TracksSearchRequest) {
              return Response().apply { resultCode = BAD_REQUEST }
          }
          return withContext(Dispatchers.IO) {
              try {
                  val response = itunesService.searchTracks(dto.expression)
                  response.apply { resultCode = SUCCESS }
              } catch (e: Throwable) {
                  Response().apply { resultCode = BAD_REQUEST }
              }
          }
      }*/


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
            ApiOperation.Success(data = apiCall())
        } catch (e: Exception) {
            ApiOperation.Failure(exception = e)
        }
    }
}

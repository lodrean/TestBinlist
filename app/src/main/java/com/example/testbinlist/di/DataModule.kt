package com.example.testbinlist.di

import com.example.testbinlist.data.network.KtorNetworkClient
import com.example.testbinlist.data.network.NetworkClient
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

private const val baseUrl = "https://lookup.binlist.net/"

val dataModule = module {

    single<HttpClient> {
        HttpClient(OkHttp) {
            defaultRequest {
                url(baseUrl)
                headers.append("Accept", "Accept-Version: 3")
            }
            install(Logging) {
                logger = Logger.SIMPLE
            }
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                })
            }
        }
    }
    single<NetworkClient> {
        KtorNetworkClient(get(), androidContext())
    }
}

package com.example.testbinlist.data.dto

import com.example.testbinlist.domain.Bank
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BankDto(
    @SerialName("city") val city: String,
    @SerialName("name") val name: String,
    @SerialName("phone") val phone: String,
    @SerialName("url") val url: String
)

fun BankDto.toDomain(): Bank {
    return Bank(city, name, phone, url)
}
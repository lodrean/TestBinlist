package com.example.testbinlist.data.dto

import com.example.testbinlist.domain.CardInfo
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class CardDto(
    @SerialName("bank") val bank: BankDto,
    @SerialName("brand") val brand: String,
    @SerialName("country") val country: CountryDto,
    @SerialName("number") val number: NumberDto,
    @SerialName("prepaid") val prepaid: Boolean,
    @SerialName("scheme") val scheme: String,
    @SerialName("type") val type: String,
)

fun CardDto.toDomain() =
    CardInfo(bank.toDomain(), brand, country.toDomain(), number.toDomain(), prepaid, scheme, type)

package com.example.testbinlist.data.dto

import com.example.testbinlist.domain.Country
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CountryDto(
    @SerialName("alpha2") val alpha2: String = "",
    @SerialName("currency") val currency: String = "",
    @SerialName("emoji") val emoji: String = "",
    @SerialName("latitude") val latitude: Int = 0,
    @SerialName("longitude") val longitude: Int = 0,
    @SerialName("name") val name: String = "",
    @SerialName("numeric") val numeric: String= ""
)

fun CountryDto.toDomain() = Country(alpha2, currency, emoji, latitude, longitude, name, numeric)
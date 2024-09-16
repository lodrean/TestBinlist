package com.example.testbinlist.data.dto

import com.example.testbinlist.domain.Country
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CountryDto(
    @SerialName("latitude") val latitude: Int = 0,
    @SerialName("longitude") val longitude: Int = 0,
    @SerialName("name") val name: String = "",
)

fun CountryDto.toDomain() = Country(name, latitude, longitude)
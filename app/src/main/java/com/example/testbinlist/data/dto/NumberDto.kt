package com.example.testbinlist.data.dto

import com.example.testbinlist.domain.Number
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NumberDto(
    @SerialName("length") val length: Int = 0,
    @SerialName("luhn") val luhn: Boolean = false
)

fun NumberDto.toDomain() = Number(length, luhn)
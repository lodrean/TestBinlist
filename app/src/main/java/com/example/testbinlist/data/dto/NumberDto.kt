package com.example.testbinlist.data.dto

import com.example.testbinlist.domain.Number
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NumberDto(
    @SerialName("length") val length: Int,
    @SerialName("luhn") val luhn: Boolean
)

fun NumberDto.toDomain() = Number(length, luhn)
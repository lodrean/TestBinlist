package com.example.testbinlist.domain

data class CardInfo(
    val bank: Bank? = null,
    val brand: String = "",
    val country: Country? = null,
    val prepaid: Boolean = false,
    val scheme: String = "",
    val type: String = ""
)

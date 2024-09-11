package com.example.testbinlist.domain

data class CardInfo(
    val cardNumber: String = "",
    val bank: Bank = Bank(
        "",
        "",
        "",
        ""
    ),
    val brand: String = "",
    val country: Country = Country(
        0,
        0,
        ""
    ),
    val prepaid: Boolean = false,
    val scheme: String = "",
    val type: String = ""
)

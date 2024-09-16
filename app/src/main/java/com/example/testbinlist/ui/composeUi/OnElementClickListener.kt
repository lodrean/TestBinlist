package com.example.testbinlist.ui.composeUi

fun interface OnElementClickListener {
    fun onItemClick(cardElement: CardElement)
}

sealed class CardElement {
    class Site(
        val value: String
    ) : CardElement()

    class Phone(
        val value: String
    ) : CardElement()

    class CountryCoordinates(
        val value: String
    ) : CardElement()
}
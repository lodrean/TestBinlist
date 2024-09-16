package com.example.testbinlist.viewmodels
import com.example.testbinlist.domain.CardInfo

data class SearchViewState(
    val isLoading: Boolean,
    val cardInfo: CardInfo,
    val errorMessage: String?
)

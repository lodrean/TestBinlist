package com.example.testbinlist.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testbinlist.domain.CardInfo
import com.example.testbinlist.domain.DataBaseRepository
import com.example.testbinlist.domain.GetCardInfoUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SearchViewModel(
    private val getCardInfoUseCase: GetCardInfoUseCase, private val dataBase: DataBaseRepository
) : ViewModel() {
    private val _cardInfo = MutableStateFlow(CardInfo())
    val cardInfo: StateFlow<CardInfo> = _cardInfo

    fun getCardInfo(value: String) {
        viewModelScope.launch {
            getCardInfoUseCase.execute(value).collect {
                _cardInfo.value = it.first.copy(cardNumber = value)
                dataBase.putCardIntoDb(it.first)
                if (it.first.country.name.isNotEmpty()) {
                    dataBase.putCardIntoDb(_cardInfo.value)
                }
            }
        }
    }
}
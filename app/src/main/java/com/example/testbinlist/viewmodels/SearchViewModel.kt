package com.example.testbinlist.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testbinlist.domain.CardInfo
import com.example.testbinlist.domain.GetCardInfoUseCaseImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.koin.core.KoinApplication.Companion.init

class SearchViewModel(private val getCardInfoUseCase: GetCardInfoUseCaseImpl) : ViewModel() {
    private val _cardInfo = MutableStateFlow<CardInfo>(CardInfo())
    val cardInfo: StateFlow<CardInfo> = _cardInfo
    fun getCardInfo(value: String) {
        viewModelScope.launch {
            getCardInfoUseCase.execute().collect {
                _cardInfo.value = it
            }
        }
    }
}
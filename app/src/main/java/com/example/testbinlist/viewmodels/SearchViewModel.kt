package com.example.testbinlist.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testbinlist.domain.CardInfo
import com.example.testbinlist.domain.DataBaseRepository
import com.example.testbinlist.domain.GetCardInfoUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.flow.updateAndGet
import kotlinx.coroutines.launch

class SearchViewModel(
    private val getCardInfoUseCase: GetCardInfoUseCase, private val dataBase: DataBaseRepository
) : ViewModel() {
    private var currentCardInfo = CardInfo()
    private val _internalStorageFlow =
        MutableStateFlow(
            value = SearchViewState(
                isLoading = false,
                cardInfo = currentCardInfo,
                errorMessage = null
            )
        )
    val stateFlow = _internalStorageFlow.asStateFlow()

    fun fetchCardInfo(value: String) {
        viewModelScope.launch {
            _internalStorageFlow.update { it.copy(isLoading = true) }
            getCardInfoUseCase.execute(value).collect { (cardInfo, errorMessage) ->
                currentCardInfo = cardInfo
                if (cardInfo.country.name.isNotEmpty()) {
                    dataBase.putCardIntoDb(cardInfo)
                    _internalStorageFlow.update {
                        it.copy(isLoading = false, cardInfo = currentCardInfo, errorMessage = null)
                    }
                } else {
                    _internalStorageFlow.updateAndGet {
                        it.copy(
                            isLoading = false,
                            cardInfo = CardInfo(),
                            errorMessage = errorMessage
                        )
                    }
                }
            }

        }

    }

    fun userMessageShown() {
        _internalStorageFlow.value = _internalStorageFlow.value.copy(errorMessage = null)
    }
}
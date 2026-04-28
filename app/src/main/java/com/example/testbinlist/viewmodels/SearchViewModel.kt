package com.example.testbinlist.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testbinlist.domain.BinValidator
import com.example.testbinlist.domain.CardInfo
import com.example.testbinlist.domain.DataBaseRepository
import com.example.testbinlist.domain.GetCardInfoUseCase
import com.example.testbinlist.domain.SharingRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.flow.updateAndGet
import kotlinx.coroutines.launch

class SearchViewModel(
    private val getCardInfoUseCase: GetCardInfoUseCase,
    private val dataBase: DataBaseRepository,
    private val sharingRepository: SharingRepository
) : ViewModel() {
    private var currentCardInfo = CardInfo()
    private val _internalStorageFlow = MutableStateFlow(
        value = SearchViewState(
            isLoading = false, cardInfo = currentCardInfo, errorMessage = null
        )
    )
    val stateFlow = _internalStorageFlow.asStateFlow()

    private val _snackbarEvent = Channel<String>()
    val snackbarEvent = _snackbarEvent.receiveAsFlow()

    fun fetchCardInfo(value: String) {
        val validation = BinValidator.validate(value)

        when (validation) {
            is BinValidator.ValidationResult.Error -> {
                _snackbarEvent.trySend(validation.message)
                return
            }
            is BinValidator.ValidationResult.Success -> {
                // Continue with API call
            }
        }

        viewModelScope.launch {
            _internalStorageFlow.update { it.copy(isLoading = true) }
            getCardInfoUseCase.execute(value).collect { (cardInfo, errorMessage) ->
                currentCardInfo = cardInfo
                if (cardInfo.country.name.isNotEmpty()) {
                    dataBase.putCardIntoDb(cardInfo)
                    _internalStorageFlow.update {
                        it.copy(cardInfo = currentCardInfo, errorMessage = null)
                    }
                } else {
                    _snackbarEvent.trySend(errorMessage ?: "Unknown error")
                    _internalStorageFlow.update {
                        it.copy(cardInfo = CardInfo())
                    }
                }
                _internalStorageFlow.update { it.copy(isLoading = false) }
            }
        }
    }

    fun openSite(value: String) {
        sharingRepository.openLink(value)
    }

    fun openCountryCoordinates(value: String) {
        sharingRepository.openMap(value)
    }

    fun openPhone(value: String) {
        sharingRepository.openDialer(value)
    }
}
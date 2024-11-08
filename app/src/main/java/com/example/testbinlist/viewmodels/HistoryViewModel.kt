package com.example.testbinlist.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testbinlist.domain.CardInfo
import com.example.testbinlist.domain.DataBaseRepository
import com.example.testbinlist.domain.SharingRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HistoryViewModel(
    private val dataBaseRepository: DataBaseRepository,
    private val sharingRepository: SharingRepository
) : ViewModel() {
    private val _stateFlow = MutableStateFlow(HistoryViewState(emptyList()))
    val stateFlow = _stateFlow.asStateFlow()
    private var historyList = listOf<CardInfo>()

    fun fetchHistory() {
        viewModelScope.launch {
            dataBaseRepository.getHistory().collect { cardList ->
                if (cardList != historyList) {
                    historyList = cardList
                    _stateFlow.update {
                        it.copy(historyList = cardList)
                    }
                }
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

data class HistoryViewState(val historyList: List<CardInfo>)
package com.example.testbinlist.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testbinlist.domain.CardInfo
import com.example.testbinlist.domain.DataBaseRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HistoryViewModel(private val dataBaseRepository: DataBaseRepository) : ViewModel() {
    private val historyList = MutableStateFlow<List<CardInfo>>(emptyList())

    fun getHistoryList(): StateFlow<List<CardInfo>> {
        return historyList
    }

    init {
        viewModelScope.launch {
            dataBaseRepository.getHistory().collect {
                historyList.value = it
            }
        }
    }
}
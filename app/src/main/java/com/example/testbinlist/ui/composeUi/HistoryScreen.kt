package com.example.testbinlist.ui.composeUi

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.testbinlist.viewmodels.HistoryViewModel

@Composable
fun HistoryScreen(historyViewModel: HistoryViewModel) {
    Column {
        val historyLIst by historyViewModel.getHistoryList().collectAsState()
        LazyColumn {
            items(historyLIst) { cardInfo ->
                BankInfoCard(cardInfo)
            }
        }
    }
}
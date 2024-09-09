package com.example.testbinlist.ui.composeUi

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.testbinlist.domain.CardInfo
import com.example.testbinlist.viewmodels.HistoryViewModel

@Composable
fun HistoryScreen(historyViewModel: HistoryViewModel) {
    Column {
        val historyLIst by historyViewModel.getHistoryList().collectAsState()
        LazyColumn {
            items(historyLIst) { cardInfo ->
            }
        }
    }
}
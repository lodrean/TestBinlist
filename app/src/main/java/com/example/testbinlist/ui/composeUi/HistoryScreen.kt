package com.example.testbinlist.ui.composeUi

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.testbinlist.viewmodels.HistoryViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun HistoryScreen(historyViewModel: HistoryViewModel = koinViewModel()) {
    Column {
        val historyLIst by historyViewModel.getHistoryList().collectAsState()
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(historyLIst) { cardInfo ->
                Spacer(modifier = Modifier.height(16.dp))
                BankInfoCard(cardInfo)
            }
        }
    }
}
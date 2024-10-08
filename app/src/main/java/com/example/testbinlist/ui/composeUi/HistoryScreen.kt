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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.testbinlist.viewmodels.HistoryViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun HistoryScreen(
    viewModel: HistoryViewModel = koinViewModel(),
    triggerHistory: Boolean,
) {
    val onElementClickListener = OnElementClickListener { cardElement ->
        when (cardElement) {
            is CardElement.Site -> {
                viewModel.openSite(cardElement.value)
            }

            is CardElement.CountryCoordinates -> {
                viewModel.openCountryCoordinates(cardElement.value)
            }

            is CardElement.Phone -> {
                viewModel.openPhone(cardElement.value)
            }
        }
    }
    Column {
        val state by viewModel.stateFlow.collectAsState()
        var trigger = triggerHistory
        val restoreHistory by remember { mutableStateOf(trigger) }
        if (restoreHistory) {
            viewModel.fetchHistory()
            trigger = false
        }
        SimpleToolBar("История")
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            val historyList = state.historyList
            items(historyList) { cardInfo ->
                Spacer(modifier = Modifier.height(16.dp))
                BankInfoCard(cardInfo, onElementClickListener = onElementClickListener)
            }
        }
    }
}



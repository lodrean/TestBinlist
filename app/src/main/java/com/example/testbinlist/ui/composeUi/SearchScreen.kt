package com.example.testbinlist.ui.composeUi

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.testbinlist.domain.CardInfo
import com.example.testbinlist.viewmodels.SearchViewModel

@Composable
fun SearchScteen(searchViewModel: SearchViewModel = viewModel()) {
    Column() {
        val cardNumber = remember { mutableStateOf("") }
        val card by searchViewModel.cardInfo.collectAsState()
        OutlinedTextField(
            value = "text",
            onValueChange = { cardNumber.value = it },
            label = { Text("Введите номер карты") },
        )
        searchViewModel.getCardInfo(cardNumber.value)
        BankInfoCard(card)
    }
}

@Preview
@Composable
fun SearchScteenPreview() {
    SearchScteen(viewModel())
}

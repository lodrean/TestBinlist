package com.example.testbinlist.ui.composeUi

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.testbinlist.viewmodels.SearchViewModel
import org.koin.androidx.compose.koinViewModel


@Composable
fun SearchScreen(viewModel: SearchViewModel = koinViewModel<SearchViewModel>()) {
    val card by viewModel.cardInfo.collectAsState()
    Column() {
        val cardNumber = remember { mutableStateOf("") }

        OutlinedTextField(
            value = cardNumber.value,
            onValueChange = { cardNumber.value = it },
            label = { Text("Введите номер карты") },
        )
        Button(
            onClick = { viewModel.getCardInfo(cardNumber.value) },
            content = { Text("Получить информацию") })
        BankInfoCard(card)
    }
}

@Preview
@Composable
fun SearchScteenPreview() {
    SearchScreen(viewModel())
}

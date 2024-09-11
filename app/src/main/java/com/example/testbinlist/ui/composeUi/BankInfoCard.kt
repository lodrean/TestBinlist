package com.example.testbinlist.ui.composeUi

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.testbinlist.domain.CardInfo

@Composable
fun BankInfoCard(cardNumber: String, card: CardInfo) {
    Column(
    ) {
        ElevatedCard(
            elevation = CardDefaults.cardElevation(
                defaultElevation = 6.dp
            ),
            modifier = Modifier.align(alignment = Alignment.Start),

            ) {
            Text(
                text = "Bank Info Card",
                modifier = Modifier
                    .fillMaxWidth(),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyMedium
            )
            Row(
                modifier = Modifier
                    .align(alignment = Alignment.Start)
                    .padding(12.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.SpaceEvenly,
            ) {
                Column() {
                    CardNumber(cardNumber = cardNumber)
                    CountryInfo(country = card.country?.name)
                    BankInfo(bankName = card.bank?.name)
                }

                Column() {
                    PhoneInfo(phone = card.bank?.phone)
                    CityInfo(city = card.bank?.city)
                    CardType(type = card.type)
                }
            }


        }
    }

}

@Composable
private fun CountryInfo(country: String?) {
    Row {
        Text(
            text = "Country: ",
            style = MaterialTheme.typography.bodySmall
        )
        Text(
            text = country ?: "",
            modifier = Modifier
                .clickable { },
            style = MaterialTheme.typography.bodySmall
        )
    }

}

@Composable
private fun CardNumber(cardNumber: String) {
    Row {
        Text(
            text = "Card Number: ",
            style = MaterialTheme.typography.bodySmall
        )
        Text(
            text = cardNumber,
            style = MaterialTheme.typography.bodySmall
        )
    }
}

@Composable
private fun BankInfo(bankName: String?) {
    Row() {
        Text(
            text = "Bank: ",
            style = MaterialTheme.typography.bodySmall
        )
        Text(
            text = bankName ?: "",
            style = MaterialTheme.typography.bodySmall
        )
    }
}

@Composable
private fun PhoneInfo(phone: String?) {
    Row() {
        Text(
            text = "Телефон: ",
            style = MaterialTheme.typography.bodySmall
        )
        Text(
            text = phone ?: "",
            style = MaterialTheme.typography.bodySmall
        )
    }
}


@Composable
private fun CityInfo(city: String?) {
    Row() {
        Text(
            text = "Город: ",
            style = MaterialTheme.typography.bodySmall
        )
        Text(
            text = city ?: "",
            style = MaterialTheme.typography.bodySmall
        )
    }
}

@Composable
private fun CardType(type: String) {
    Row() {
        Text(
            text = "Тип карты: ",
            style = MaterialTheme.typography.bodySmall
        )
        Text(
            text = type ?: "",
            style = MaterialTheme.typography.bodySmall
        )
    }
}


@Preview
@Composable
private fun BankInfoCardPreview() {
    BankInfoCard("", CardInfo())
}
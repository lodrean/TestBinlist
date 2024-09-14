package com.example.testbinlist.ui.composeUi

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Cyan
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose.tertiaryLight
import com.example.testbinlist.domain.CardInfo

@Composable
fun BankInfoCard(card: CardInfo) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp,
        ),
        modifier = Modifier.border(
            width = 1.dp,
            brush = Brush.verticalGradient(listOf(Color.Transparent, tertiaryLight)),
            shape = RoundedCornerShape(12.dp)
        )
    ) {
        Column(
            modifier = Modifier

        ) {
            Text(
                text = "Bank Info Card",
                modifier = Modifier.fillMaxWidth(),
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
                    CardNumber(cardNumber = card.cardNumber)
                    CountryInfo(
                        country = card.country.name,
                        latitude = card.country.latitude.toString(),
                        longitude = card.country.longitude.toString()
                    )
                    CardType(type = card.type)
                    Brand(brand = card.brand)
                }

                Column() {
                    BankInfo(bankName = card.bank.name)
                    PhoneInfo(phone = card.bank.phone)
                    CityInfo(city = card.bank.city)
                    BankUrl(bankUrl = card.bank.url)
                }
            }
        }
    }
}

@Composable
private fun CountryInfo(country: String?, latitude: String?, longitude: String?) {
    Column() {
        Row {
            Text(
                text = "Country: ", style = MaterialTheme.typography.bodySmall
            )
            Text(
                text = country ?: "",
                modifier = Modifier.clickable { },
                style = MaterialTheme.typography.bodySmall
            )
        }
        Row {
            Text(
                text = "(latitude: ", style = MaterialTheme.typography.bodySmall
            )
            Text(
                text = if (latitude != null) "$latitude ," else "",
                modifier = Modifier.clickable { },
                style = MaterialTheme.typography.bodySmall
            )
            Text(
                text = "longitude: ", style = MaterialTheme.typography.bodySmall
            )
            Text(
                text = if (longitude != null) "$longitude)" else ")",
                modifier = Modifier.clickable { },
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

@Composable
private fun CardNumber(cardNumber: String) {
    Row {
        Text(
            text = "Card Number: ", style = MaterialTheme.typography.bodySmall
        )
        Text(
            text = cardNumber, style = MaterialTheme.typography.bodySmall
        )
    }

}

@Composable
private fun BankInfo(bankName: String?) {
    Row() {
        Text(
            text = "Bank: ", style = MaterialTheme.typography.bodySmall
        )
        Text(
            text = bankName ?: "", style = MaterialTheme.typography.bodySmall
        )
    }
}


@Composable
private fun BankUrl(bankUrl: String?) {
    Row() {
        Text(
            text = "Сайт: ", style = MaterialTheme.typography.bodySmall
        )
        Text(
            text = bankUrl ?: "", style = MaterialTheme.typography.bodySmall
        )
    }
}

@Composable
private fun PhoneInfo(phone: String?) {
    Row() {
        Text(
            text = "Телефон: ", style = MaterialTheme.typography.bodySmall
        )
        Text(
            text = phone ?: "", style = MaterialTheme.typography.bodySmall
        )
    }
}


@Composable
private fun CityInfo(city: String?) {
    Row() {
        Text(
            text = "Город: ", style = MaterialTheme.typography.bodySmall
        )
        Text(
            text = city ?: "", style = MaterialTheme.typography.bodySmall
        )
    }
}

@Composable
private fun CardType(type: String) {
    Row() {
        Text(
            text = "Тип карты: ", style = MaterialTheme.typography.bodySmall
        )
        Text(
            text = type, style = MaterialTheme.typography.bodySmall
        )
    }
}

@Composable
private fun Brand(brand: String) {
    Row() {
        Text(
            text = "Brand: ", style = MaterialTheme.typography.bodySmall
        )
        Text(
            text = brand, style = MaterialTheme.typography.bodySmall
        )
    }
}

@Preview
@Composable
private fun BankInfoCardPreview() {
    BankInfoCard(CardInfo())
}
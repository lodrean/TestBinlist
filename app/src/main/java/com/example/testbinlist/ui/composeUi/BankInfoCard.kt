package com.example.testbinlist.ui.composeUi

import androidx.compose.foundation.clickable
import androidx.compose.foundation.content.contentReceiver
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.testbinlist.domain.CardInfo

@Composable
fun BankInfoCard(card: CardInfo) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = Modifier
            .fillMaxWidth()
            .size(width = 240.dp, height = 100.dp),

    ) {
        Text(
            text = "Bank Info Card",
            modifier = Modifier
                .fillMaxWidth(),
            textAlign = TextAlign.Center,
        )
    }
}

@Composable
private fun CountryInfo(country: String) {
    Text(
        text = "Country: ${
            Text(
                text = country,
                modifier = Modifier
                    .clickable { })
        }",
        modifier = Modifier
            .size(16.dp)
    )
}

@Composable
private fun BankInfo(bankName: String) {
    Text(
        text = "Bank: ${
            Text(
                text = bankName
            )
        }",
        modifier = Modifier
            .size(16.dp)
    )
}

@Composable
private fun PhoneInfo(phone: String) {
    Text(
        text = "Phone: ${
            Text(
                text = phone
            )
        }",
        modifier = Modifier
            .size(16.dp)
    )
}

@Composable
private fun CityInfo(city: String) {
    Text(
        text = "Город: ${
            Text(
                text = city
            )
        }",
        modifier = Modifier
            .size(16.dp)
    )
}
@Composable
private fun CardType(type: String) {
    Text(
        text = "Тип: ${
            Text(
                text = type
            )
        }",
        modifier = Modifier
            .size(16.dp)
    )
}


@Preview
@Composable
private fun BankInfoCardPreview() {
    BankInfoCard(CardInfo())
}
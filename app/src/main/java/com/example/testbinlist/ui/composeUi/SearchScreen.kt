package com.example.testbinlist.ui.composeUi

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.testbinlist.domain.CardInfo
import com.example.testbinlist.viewmodels.SearchViewModel
import com.example.testbinlist.viewmodels.SearchViewState
import org.koin.androidx.compose.koinViewModel


@Composable
fun SearchScreen(viewModel: SearchViewModel = koinViewModel<SearchViewModel>()) {
    val onElementClickListener = OnElementClickListener() { CardElement ->
        when (CardElement) {
            is CardElement.Site -> {
                viewModel.openSite(CardElement.value)
            }

            is CardElement.CountryCoordinates -> {
                viewModel.openCountryCoordinates(CardElement.value)
            }

            is CardElement.Phone -> {
                viewModel.openPhone(CardElement.value)
            }
        }
    }
    val state by viewModel.stateFlow.collectAsState()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 12.dp),

        ) {

        val creditCardText = remember { mutableStateOf(TextFieldValue()) }
        val maxCharCreditCard = 8
        // this for entering number only
        val numberRegex = "^[0-9]+\$".toRegex()
        SimpleToolBar("Поиск")
        OutlinedTextField(
            isError = creditCardText.value.text.length < 6,
            modifier = Modifier.width(300.dp),
            value = creditCardText.value,
            label = { Text("Номер карты") },
            placeholder = { Text(text = "Введите от 6 до 8 знаков") },
            onValueChange = { newValue ->
                val text = newValue.text
                if ((text.length <= maxCharCreditCard && numberRegex.matches(text)) or (text.isEmpty())) {
                    creditCardText.value = newValue
                }
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            maxLines = 1,
            visualTransformation = CreditCardVisualTransformation()
        )
        Button(modifier = Modifier.padding(
            top = 12.dp, bottom = 12.dp, start = 12.dp, end = 12.dp
        ), onClick = {
            if (creditCardText.value.text.length > 5) viewModel.fetchCardInfo(
                creditCardText.value.text
            )
        }, content = { Text("Получить информацию") })



        when {
            (state.cardInfo != CardInfo()) -> {
                BankInfoCard(state.cardInfo, onElementClickListener)
            }

            state.isLoading -> LoadingState()
            state.errorMessage != null -> {
                ShowSingleToastEvent(state)
                viewModel.userMessageShown()
            }
        }

    }
}


@Composable
private fun ShowSingleToastEvent(state: SearchViewState) {
    Toast.makeText(
        LocalContext.current, state.errorMessage, Toast.LENGTH_SHORT
    ).show()
}


class CreditCardVisualTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        return creditCardFilter(text)
    }
}

fun creditCardFilter(text: AnnotatedString): TransformedText {

    // Making XXXX-XXXX string.
    val trimmed = if (text.text.length >= 8) text.text.substring(0..7) else text.text
    var out = ""
    for (i in trimmed.indices) {
        out += trimmed[i]
        if (i % 4 == 3 && i < 7) out += "-"
    }

    /**
     * The offset translator should ignore the hyphen characters, so conversion from
     *  original offset to transformed text works like
     *  - The 4th char of the original text is 5th char in the transformed text.
     *  - The 13th char of the original text is 15th char in the transformed text.
     *  Similarly, the reverse conversion works like
     *  - The 5th char of the transformed text is 4th char in the original text.
     *  - The 12th char of the transformed text is 10th char in the original text.
     */
    val creditCardOffsetTranslator = object : OffsetMapping {
        override fun originalToTransformed(offset: Int): Int {
            if (offset <= 3) return offset
            if (offset <= 7) return offset + 1
            return 9
        }

        override fun transformedToOriginal(offset: Int): Int {
            if (offset <= 4) return offset
            if (offset <= 9) return offset - 1
            return 8
        }
    }

    return TransformedText(AnnotatedString(out), creditCardOffsetTranslator)
}


@Preview
@Composable
fun SearchScreenPreview() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 48.dp, bottom = 12.dp, start = 12.dp, end = 12.dp)
            .padding(top = 48.dp, bottom = 12.dp, start = 12.dp, end = 12.dp)
    ) {
        val creditCardText = remember { mutableStateOf(TextFieldValue("")) }
        val maxCharCreditCard = 8
        // this for entering number only
        val numberRegex = "^[0-9]+\$".toRegex()

        OutlinedTextField(
            isError = creditCardText.value.text.length < 6,
            modifier = Modifier.width(300.dp),
            value = creditCardText.value,
            label = { Text("Номер карты") },
            placeholder = { Text(text = "Введите от 6 до 8 знаков") },
            onValueChange = { newValue ->
                val text = newValue.text
                if ((text.length <= maxCharCreditCard && numberRegex.matches(text)) or (text.isEmpty())) {
                    creditCardText.value = newValue
                }
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            maxLines = 1,
            visualTransformation = CreditCardVisualTransformation()
        )
        Button(modifier = Modifier.padding(
            top = 12.dp,
            bottom = 12.dp,
            start = 12.dp,
            end = 12.dp
        ),
            onClick = {},
            content = { Text("Получить информацию") })
        BankInfoCard(CardInfo(), onElementClickListener = OnElementClickListener { })
    }

}

package com.example.testbinlist.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.testbinlist.domain.CardInfo
import com.example.testbinlist.ui.composeUi.BankInfoCard
import com.example.testbinlist.ui.theme.TestBinlistTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TestBinlistTheme {
                BankInfoCard(CardInfo())
            }
        }
    }
}
@Preview
@Composable
private fun BankInfoCardPreview() {
    BankInfoCard(CardInfo())
}
package com.example.testbinlist.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.testbinlist.ui.composeUi.SearchScreen
import com.example.testbinlist.ui.theme.TestBinlistTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val navController = rememberNavController()

            TestBinlistTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavHost(
                        navController,
                        startDestination = "search"
                    ) {
                        composable("search") {
                            SearchScreen()
                        }
                        composable("history_screen") { /*HistoryScreen() */ }
                    }

                    SearchScreen()
                }
            }
        }
    }

    @Preview
    @Composable
    private fun BankInfoCardPreview() {
        SearchScreen()
    }
}
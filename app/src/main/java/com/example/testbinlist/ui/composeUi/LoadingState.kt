package com.example.testbinlist.ui.composeUi

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.testbinlist.ui.theme.Pink80

private val defaultModifier = Modifier
    .fillMaxSize()
    .padding(all = 128.dp)

@Composable
fun LoadingState(modifier: Modifier = defaultModifier) {
    CircularProgressIndicator(
        modifier = modifier,
        color = Pink80
    )
}
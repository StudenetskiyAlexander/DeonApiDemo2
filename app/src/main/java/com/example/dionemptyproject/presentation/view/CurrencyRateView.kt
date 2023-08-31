package com.example.dionemptyproject.presentation.view

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dionemptyproject.domain.entity.Currencies
import com.example.dionemptyproject.domain.entity.CurrencyRate
import com.example.dionemptyproject.presentation.entity.CurrencyRatePresentation
import com.example.dionemptyproject.presentation.theme.DionEmptyProjectTheme

@Composable
fun CurrencyRateView(
    modifier: Modifier = Modifier,
    currencyRatePresentation: CurrencyRatePresentation,
    onRefreshClicked: () -> Unit
) {
    Row {
        when (currencyRatePresentation) {
            is CurrencyRatePresentation.Completed -> {
                Text(
                    text = currencyRatePresentation.text,
                    modifier = modifier
                )
                IconButton(
                    onClick = { onRefreshClicked() },
                    modifier = modifier.size(20.dp)
                ) {
                    Icon(imageVector = Icons.Filled.Refresh, contentDescription = null)
                }
            }
            is CurrencyRatePresentation.Loading -> {
                LoadingAnimation(circleSize = 20.dp)
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun CurrencyRatePreview() {
    val rubRate = CurrencyRatePresentation.Completed("RUB = 100.0", Currencies.USD)

    DionEmptyProjectTheme {
        CurrencyRateView(currencyRatePresentation = rubRate, onRefreshClicked = {})
    }
}

@Preview(showBackground = true)
@Composable
fun CurrencyRateLoadingPreview() {
    val rubRate = CurrencyRatePresentation.Loading(Currencies.USD)

    DionEmptyProjectTheme {
        CurrencyRateView(currencyRatePresentation = rubRate, onRefreshClicked = {})
    }
}
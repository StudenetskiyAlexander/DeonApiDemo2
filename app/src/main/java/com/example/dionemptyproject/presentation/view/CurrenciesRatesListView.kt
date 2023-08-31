package com.example.dionemptyproject.presentation.view

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
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
fun CurrenciesRatesListView(
    modifier: Modifier = Modifier,
    currenciesRates: List<CurrencyRatePresentation>,
    onAllCurrenciesRatesUpdateClick: () -> Unit,
    onSingleCurrencyUpdateClick: (Currencies) -> Unit
) {
    Column {
        Button(onClick = { onAllCurrenciesRatesUpdateClick() }) {
            Text(text = "refresh all")
        }

        currenciesRates.forEach { rate ->

            CurrencyRateView(modifier, rate) {
                onSingleCurrencyUpdateClick(rate.key)
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun CurrenciesRateListPreview() {
    val rubRate = CurrencyRatePresentation.Completed("RUB = 100.0", Currencies.RUB)
    val eurRate = CurrencyRatePresentation.Completed("EUR = 130.0", Currencies.EUR)
    val loadingRate = CurrencyRatePresentation.Loading(Currencies.USD)

    DionEmptyProjectTheme {
        CurrenciesRatesListView(
            currenciesRates = listOf(rubRate, loadingRate, eurRate),
            onAllCurrenciesRatesUpdateClick = {},
            onSingleCurrencyUpdateClick = {})
    }
}
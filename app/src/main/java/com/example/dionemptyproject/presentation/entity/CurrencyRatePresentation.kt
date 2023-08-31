package com.example.dionemptyproject.presentation.entity

import com.example.dionemptyproject.domain.entity.Currencies

sealed class CurrencyRatePresentation(val key: Currencies) {

    class Loading(key: Currencies): CurrencyRatePresentation(key)

    class Completed(val text: String, key: Currencies): CurrencyRatePresentation(key)
}


class SomePresentation(
    val text: String
)
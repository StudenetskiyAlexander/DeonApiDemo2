package com.example.dionemptyproject.data.mapper

import com.example.dionemptyproject.domain.entity.Currencies

class CurrencyStringToCurrencyMapper {
    fun invoke(currencyString: String): Currencies? {
        return Currencies.values().firstOrNull { it.name == currencyString }
    }
}

// Если "RUB" -> RUB
// Если "HJK" -> null
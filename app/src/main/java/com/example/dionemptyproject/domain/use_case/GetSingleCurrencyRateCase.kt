package com.example.dionemptyproject.domain.use_case

import com.example.dionemptyproject.data.CurrenciesRetrofitRepository
import com.example.dionemptyproject.domain.entity.Currencies

class GetSingleCurrencyRateCase(

) {
    //TODO DI
    private val repositoryRetrofit = CurrenciesRetrofitRepository()

    suspend fun invoke(currency: Currencies) = repositoryRetrofit.getCurrencyData(currency)
}
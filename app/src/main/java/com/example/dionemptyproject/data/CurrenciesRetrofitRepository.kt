package com.example.dionemptyproject.data

import com.example.dionemptyproject.data.entity.DeonLocationResponse
import com.example.dionemptyproject.data.mapper.CurrencyStringToCurrencyMapper
import com.example.dionemptyproject.data.retrofit.CurrencyApi
import com.example.dionemptyproject.data.retrofit.RetrofitHelper
import com.example.dionemptyproject.data.retrofit.WeatherApi
import com.example.dionemptyproject.domain.entity.Currencies
import com.example.dionemptyproject.domain.entity.CurrencyRate
import com.example.dionemptyproject.domain.entity.TempInCity
import com.example.dionemptyproject.presentation.entity.CurrencyApiErrorCode

class CurrenciesRetrofitRepository(
//    private val currencyStringToCurrencyMapper: CurrencyStringToCurrencyMapper()
) {

    //TODO DI
    private val currencyStringToCurrencyMapper = CurrencyStringToCurrencyMapper()

    suspend fun getCurrencyData(currency: Currencies): CurrencyRate {
        val quotesApi = RetrofitHelper.getInstance().create(CurrencyApi::class.java)

        return mapCurrencyResponseToRate(
            quotesApi.getRates().body()?.listOfRates,
            currency
        )
    }

    suspend fun getCitiesTemp(cityCode: String): TempInCity {
        val quotesApi = RetrofitHelper.getInstance().create(WeatherApi::class.java)

        return mapTempResponseToTempInCity(
            quotesApi.getTemp(cityCode).body() ?: error("FUCK BACKEND")
        )
    }

    //TODO to mapper
    private fun mapTempResponseToTempInCity(response: DeonLocationResponse): TempInCity {
        return TempInCity(response.location.nameOfCity, response.temperature.temp)
    }

    //TODO to mapper
    private fun mapCurrencyResponseToRate(currencyResponse: Map<String, Double>?,
                                          findingCurrency: Currencies): CurrencyRate {

        val response =
            currencyResponse ?: return CurrencyRate.Error(findingCurrency, CurrencyApiErrorCode.RESPONSE_ERROR)

        return response.map {
            CurrencyRate.Rate(
                currency = currencyStringToCurrencyMapper.invoke(it.key)
                    ?: return@map null,
                rate = it.value.toString()
            )
        }.filterNotNull().firstOrNull { it.currency == findingCurrency } ?: CurrencyRate.Error(
            findingCurrency, CurrencyApiErrorCode.FAILED_TO_FIND_CURRENCY
        )
    }
}
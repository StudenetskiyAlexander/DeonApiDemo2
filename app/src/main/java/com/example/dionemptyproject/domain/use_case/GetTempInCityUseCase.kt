package com.example.dionemptyproject.domain.use_case

import com.example.dionemptyproject.data.CurrenciesRetrofitRepository

class GetTempInCityUseCase {

    //TODO DI
    private val repositoryRetrofit = CurrenciesRetrofitRepository()

    suspend fun invoke(cityCode: String) = repositoryRetrofit.getCitiesTemp(cityCode)
}
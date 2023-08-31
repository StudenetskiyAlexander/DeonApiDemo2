package com.example.dionemptyproject.presentation.entity

import com.example.dionemptyproject.domain.entity.CurrencyRate
import com.example.dionemptyproject.domain.entity.TempInCity

sealed class CurrencyScreenState {

    object Loading : CurrencyScreenState()

    data class Completed(val rates: List<CurrencyRate>) : CurrencyScreenState()

    data class Temp(val tempInCity: TempInCityPresentation): CurrencyScreenState()
}



enum class CurrencyApiErrorCode {
    FAILED_TO_FIND_CURRENCY, //Валюты нет в списке
    RESPONSE_ERROR, //Вообще непонятный ответ от сервера
    UNKNOWN_ERROR
}
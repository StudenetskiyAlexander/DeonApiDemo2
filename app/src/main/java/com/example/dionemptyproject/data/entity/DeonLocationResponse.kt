package com.example.dionemptyproject.data.entity

import com.google.gson.annotations.SerializedName

class DeonLocationsResponse(
    val someNewNameClass: List<TempInCity>
) {
    class TempInCity(
        @SerializedName("location")
        val location: DeonLocationResponse.Location,

        @SerializedName("current")
        val temperature: DeonLocationResponse.Temperature
    )
}

class DeonLocationResponse(
    @SerializedName("location")
    val location: Location,

    @SerializedName("current")
    val temperature: Temperature
) {
    class Location(
        @SerializedName("name")
        val nameOfCity: String
    )

    class Temperature(
        @SerializedName("temp_c")
        val temp: Float
    )
}
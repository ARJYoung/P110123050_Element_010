package com.example.personalorganiser.data

data class WeatherResponse(
    val location: Location?,
    val current: Current?
)

data class Location(
    val name: String?,
    val country: String?
)

data class Condition(
    val text: String?
)
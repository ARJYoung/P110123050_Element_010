package com.example.personalorganiser.api

import com.example.personalorganiser.data.WeatherResponse // Your data class to hold the response
import retrofit2.Response // The Response object from Retrofit
import retrofit2.http.GET
import retrofit2.http.Query

// This is the Retrofit interface for the Weather API.
// It defines the network requests as functions.
interface WeatherApiService {

    // A GET request to the "current.json" endpoint.
    // The @Query annotations automatically add parameters to the URL
    // (e.g., ?key=YOUR_API_KEY&q=London)
    @GET("current.json")
    suspend fun getCurrentWeather(
        @Query("key") key: String,
        @Query("q") q: String
    ): Response<WeatherResponse> // The 'suspend' keyword and 'Response' return type are for coroutines.
}
package com.example.personalorganiser.api

import com.example.personalorganiser.BuildConfig // Make sure BuildConfig is accessible
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitInstance {

// --- Weather API Setup ---
private const val WEATHER_BASE_URL = "https://api.weatherapi.com/v1/" // Or your specific weather API URL

private val weatherClient = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply {
level = HttpLoggingInterceptor.Level.BODY // Log request/response bodies
        })
                .connectTimeout(30, TimeUnit.SECONDS) // Connection timeout
        .readTimeout(30, TimeUnit.SECONDS)    // Read timeout
        .build()

private val weatherRetrofit: Retrofit = Retrofit.Builder()
        .baseUrl(WEATHER_BASE_URL)
        .client(weatherClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

val weatherApiService: WeatherApiService by lazy { // Lazy initialization
    weatherRetrofit.create(WeatherApiService::class.java)
}

// --- Postman Echo API Setup ---
private const val ECHO_BASE_URL = "https://postman-echo.com/" // The base URL for Postman Echo

// You can reuse the same OkHttpClient if you want, or create a separate one
private val echoClient = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply {
level = HttpLoggingInterceptor.Level.BODY
        })
                .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .build()

private val echoRetrofit: Retrofit = Retrofit.Builder()
        .baseUrl(ECHO_BASE_URL)
        .client(echoClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

val echoApiService: EchoApiService by lazy { // Lazy initialization
    echoRetrofit.create(EchoApiService::class.java)
}
}
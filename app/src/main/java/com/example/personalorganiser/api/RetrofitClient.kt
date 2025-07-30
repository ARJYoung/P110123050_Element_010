package com.example.personalorganiser.api;

import com.example.personalorganiser.api.WeatherApiService
import com.example.personalorganiser.api.EchoApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient {

// --- Weather API Setup ---
private const val WEATHER_BASE_URL = "https://api.weatherapi.com/v1/"
        private val weatherClient = OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().apply {
                        level = HttpLoggingInterceptor.Level.BODY
                })
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build()

        private val weatherRetrofit: Retrofit = Retrofit.Builder()
                .baseUrl(WEATHER_BASE_URL)
                .client(weatherClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        val weatherApiService: WeatherApiService by lazy { // Line 28
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

        val echoApiService: EchoApiService by lazy { // This would be later, but also needs import
                echoRetrofit.create(EchoApiService::class.java)
        }
}
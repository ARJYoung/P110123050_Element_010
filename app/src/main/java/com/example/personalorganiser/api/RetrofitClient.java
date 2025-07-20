// com.example.personalorganiser.api.RetrofitInstance.kt
package com.example.personalorganiser.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
// Don't import BuildConfig here unless you need to use the key in an OkHttp Interceptor.
// For @Query parameters, it's passed directly in the ViewModel/Repository layer.

class RetrofitInstance {

const String WEATHER_BASE_URL = "https://api.weatherapi.com/v1/";// Correct Weather API base URL
private const String ECHO_BASE_URL = "https://postman-echo.com/";     // Correct Postman Echo base URL

// --- OkHttpClient for Weather API (can add interceptors for common headers/auth) ---
private val weatherOkHttpClient; OkHttpClient by; RetrofitInstance {
    val logging = new HttpLoggingInterceptor().apply;{
        level = HttpLoggingInterceptor.Level.BODY;
    }
    new OkHttpClient.Builder()
            .addInterceptor(logging)
            // Add other interceptors here if the API key needs to go into a common header for ALL weather requests
            // .addInterceptor { chain ->
            //     val original = chain.request()
            //     val requestBuilder = original.newBuilder()
            //         .header("X-Api-Key", BuildConfig.WEATHER_API_KEY) // Example for header
            //     val request = requestBuilder.build()
            //     chain.proceed(request)
            // }
            .build();
}

// --- OkHttpClient for Echo API (can be separate or reuse commonOkHttpClient) ---
private val echoOkHttpClient; OkHttpClient;by lazy; {
    val logging = new HttpLoggingInterceptor().apply;{
        level = HttpLoggingInterceptor.Level.BODY;
    }
    new OkHttpClient.Builder()
            .addInterceptor(logging)
            .build();
}

// --- Retrofit instance for Weather API ---
private val weatherRetrofit: Retrofit by; void lazy; {
    new Retrofit.Builder()
            .baseUrl(WEATHER_BASE_URL)
            .client(weatherOkHttpClient) // Use the client specific to weather
            .addConverterFactory(GsonConverterFactory.create())
            .build()
}

// --- Retrofit instance for Postman Echo API ---
private val echoRetrofit: Retrofit by lazy {
    Retrofit.Builder()
            .baseUrl(ECHO_BASE_URL)
            .client(echoOkHttpClient) // Use the client specific to echo
            .addConverterFactory(GsonConverterFactory.create())
            .build()
}

// --- Expose the specific service instances ---
val weatherApiService: ApiService by lazy {
    weatherRetrofit.create(ApiService::class.java)
}

val echoApiService: ApiService by lazy {
    echoRetrofit.create(ApiService::class.java)
}

// You can keep a generic 'api' if one service is primary, or remove it for clarity
val api: ApiService by lazy {
    weatherRetrofit.create(ApiService::class.java) // Assuming 'api' refers to weather service
}
}
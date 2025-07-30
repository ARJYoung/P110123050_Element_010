package com.example.personalorganiser.api

import com.example.personalorganiser.data.EchoResponse // Your data class to hold the response
import retrofit2.Response // The Response object from Retrofit
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import retrofit2.http.Body
import java.util.Map // Import for the Map<String, String> type in the POST body

// This is the Retrofit interface for the Postman Echo API.
// It defines the network requests as functions.
interface EchoApiService {

    // A GET request to the "get" endpoint.
    // The @Query annotations automatically add the parameters to the URL.
    @GET("get")
    suspend fun getEcho(
        @Query("param1") param1: String,
        @Query("value1") value1: String
    ): Response<EchoResponse> // The 'suspend' keyword and 'Response' return type are for coroutines.

    // A POST request to the "post" endpoint.
    // The @Body annotation sends the 'body' as the request body.
    @POST("post")
    suspend fun sendEchoPost(
        @Body body: Map<String, String> // A Map is often used for simple JSON bodies.
    ): Response<EchoResponse>
}
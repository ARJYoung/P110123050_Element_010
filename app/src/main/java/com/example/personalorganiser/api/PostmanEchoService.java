package com.example.personalorganiser.api;

import com.example.personalorganiser.data.EchoResponse;
import java.util.Map;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface PostmanEchoService {

    // GET Request with query parameters
    @GET("get")
    Call<EchoResponse> getWithQueryParams(
            @Query("param1") String value1,
            @Query("param2") String value2
    );

    // POST Request with a JSON body
    @POST("post")
    @Headers("Content-Type: application/json")
    Call<EchoResponse> postJsonBody(
            @Body Map<String, String> body
    );

    // Example of using dynamic headers
    @GET("headers")
    Call<EchoResponse> getHeaders(
            @Header("My-Custom-Header") String customHeader
    );
}
package com.example.personalorganiser.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.LiveData
import com.example.personalorganiser.BuildConfig
import kotlinx.coroutines.launch

// API related imports
import com.example.personalorganiser.api.RetrofitClient // Or RetrofitInstance, confirm your file name
import com.example.personalorganiser.api.WeatherApiService
import com.example.personalorganiser.api.EchoApiService

// Data model imports
import com.example.personalorganiser.data.WeatherResponse
import com.example.personalorganiser.data.EchoResponse

// BuildConfig for API Key

// Retrofit's Response class (NOT Call)
import retrofit2.Response

class MainViewModel(
    private val weatherApiService: WeatherApiService = RetrofitClient.weatherApiService,
    private val echoApiService: EchoApiService = RetrofitClient.echoApiService
) : ViewModel() {

    // --- LiveData declarations ---
    private val _currentWeather = MutableLiveData<WeatherResponse?>()
    val currentWeather: LiveData<WeatherResponse?> = _currentWeather

    private val _weatherError = MutableLiveData<String>()
    val weatherError: LiveData<String> = _weatherError

    private val _echoResponse = MutableLiveData<EchoResponse?>()
    val echoResponse: LiveData<EchoResponse?> = _echoResponse

    private val _echoError = MutableLiveData<String>()
    val echoError: LiveData<String> = _echoError

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    // --- Weather API Function ---
    fun fetchCurrentWeather(location: String) {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                // FIX: The 'response' variable will directly be of type retrofit2.Response<WeatherResponse>
                // because weatherApiService.getCurrentWeather is a 'suspend' function.
                val response = weatherApiService.getCurrentWeather(
                    key = BuildConfig.WEATHER_API_KEY,
                    q = location
                )
                // FIX: isSuccessful, body(), code(), message() are all properties/methods of retrofit2.Response
                if (response.isSuccessful) {
                    _currentWeather.value = response.body()
                    _weatherError.value = ""
                } else {
                    // Use errorBody() for detailed error responses from the server
                    val errorBody = response.errorBody()?.string() ?: "Unknown error"
                    _weatherError.value = "Weather API Error: ${response.code()} - $errorBody"
                    _currentWeather.value = null
                }
            } catch (e: Exception) {
                _weatherError.value = "Network Error: ${e.message}"
                _currentWeather.value = null
            } finally {
                _isLoading.value = false
            }
        }
    }

    // --- Echo Postman API Functions ---
    // These functions already correctly use retrofit2.Response, so no changes needed here.
    fun fetchEchoGet(param1: String, value1: String) {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val response: Response<EchoResponse> = echoApiService.getEcho(param1, value1)
                if (response.isSuccessful) {
                    _echoResponse.value = response.body()
                    _echoError.value = ""
                } else {
                    val errorBody = response.errorBody()?.string() ?: "Unknown error"
                    _echoError.value = "Echo GET API Error: ${response.code()} - $errorBody"
                    _echoResponse.value = null
                }
            } catch (e: Exception) {
                _echoError.value = "Network Error: ${e.message}"
                _echoResponse.value = null
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun sendEchoPost(key: String, value: String) {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val response: Response<EchoResponse> = echoApiService.sendEchoPost(mapOf(key to value))
                if (response.isSuccessful) {
                    _echoResponse.value = response.body()
                    _echoError.value = ""
                } else {
                    val errorBody = response.errorBody()?.string() ?: "Unknown error"
                    _echoError.value = "Echo POST API Error: ${response.code()} - $errorBody"
                    _echoResponse.value = null
                }
            } catch (e: Exception) {
                _echoError.value = "Network Error: ${e.message}"
                _echoResponse.value = null
            } finally {
                _isLoading.value = false
            }
        }
    }
    // --- End Echo Postman API functions ---

    // Getters for LiveData
    fun getCurrentWeather(): LiveData<WeatherResponse?> = _currentWeather
    fun getWeatherError(): LiveData<String> = _weatherError
    fun getEchoResponse(): LiveData<EchoResponse?> = _echoResponse
    fun getEchoError(): LiveData<String> = _echoError
    fun getIsLoading(): LiveData<Boolean> = _isLoading
}
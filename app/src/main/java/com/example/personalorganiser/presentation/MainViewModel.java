// In your MainViewModel.kt file

package com.example.personalorganiser

// --- IMPORTANT: Make sure you have these imports! ---
import com.example.personalorganiser.api.WeatherApiService // This is your Weather Specialist's type
import com.example.personalorganiser.api.EchoApiService     // This is your Echo Specialist's type
import com.example.personalorganiser.api.RetrofitInstance // Needed to get instances
import com.example.personalorganiser.BuildConfig // For your API Key
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.LiveData
import kotlinx.coroutines.launch
import com.example.personalorganiser.data.WeatherResponse // Make sure this is imported
import com.example.personalorganiser.data.EchoResponse   // Make sure this is imported
// --------------------------------------------------

class MainViewModel(
        // Inject the specific services provided by RetrofitInstance
        // NOW, we tell Kotlin the EXACT type of each specialist!
        private val weatherApiService: WeatherApiService = RetrofitInstance.weatherApiService, // This should be your Weather API Service type
        private val echoApiService: EchoApiService = RetrofitInstance.echoApiService     // This should be your Echo API Service type
) : ViewModel() {

    private val _currentWeather = MutableLiveData<WeatherResponse?>()
    val currentWeather: LiveData<WeatherResponse?> = _currentWeather

    private val _weatherError = MutableLiveData<String>()
    val weatherError: LiveData<String> = _weatherError

    private val _echoResponse = MutableLiveData<EchoResponse?>() // Changed to nullable if it can be null initially
    val echoResponse: LiveData<EchoResponse?> = _echoResponse

    private val _echoError = MutableLiveData<String>()
    val echoError: LiveData<String> = _echoError

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun fetchCurrentWeather(location: String) {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                // Call the weather API service, passing the key from BuildConfig
                val response = weatherApiService.getCurrentWeather(
                        key = BuildConfig.WEATHER_API_KEY,
                        q = location
                )
                if (response.isSuccessful) {
                    _currentWeather.value = response.body()
                    _weatherError.value = ""
                } else {
                    _weatherError.value = "Weather API Error: ${response.code()} ${response.message()}"
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

    // --- Add these functions for the Echo Postman API ---
    fun fetchEchoGet(param1: String, value1: String) {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val response = echoApiService.getEcho(param1, value1) // Using the correct echoApiService
                if (response.isSuccessful) {
                    _echoResponse.value = response.body()
                    _echoError.value = ""
                } else {
                    _echoError.value = "Echo GET API Error: ${response.code()} - ${response.message()}"
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
                val response = echoApiService.sendEchoPost(mapOf(key to value)) // Using the correct echoApiService
                if (response.isSuccessful) {
                    _echoResponse.value = response.body()
                    _echoError.value = ""
                } else {
                    _echoError.value = "Echo POST API Error: ${response.code()} - ${response.message()}"
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

    // Getters for LiveData (optional, but good practice)
    fun getCurrentWeather(): LiveData<WeatherResponse?> = _currentWeather
    fun getWeatherError(): LiveData<String> = _weatherError
    fun getEchoResponse(): LiveData<EchoResponse?> = _echoResponse
    fun getEchoError(): LiveData<String> = _echoError
    fun getIsLoading(): LiveData<Boolean> = _isLoading
}
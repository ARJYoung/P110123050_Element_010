class mainViewModel(
        // Inject the specific services provided by RetrofitInstance
        private val weatherApiService: ApiService = RetrofitInstance.weatherApiService,
        private val echoApiService: ApiService = RetrofitInstance.echoApiService
) : ViewModel() {

    // ... LiveData declarations ...

    fun fetchCurrentWeather(location: String) {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                // Call the weather API service, passing the key from BuildConfig
                val response = weatherApiService.getCurrentWeather(
                        key = BuildConfig.WEATHER_API_KEY, // <-- Key used here!
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
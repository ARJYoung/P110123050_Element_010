package com.example.personalorganiser

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.personalorganiser.data.EchoResponse
import com.example.personalorganiser.data.WeatherResponse
import com.example.personalorganiser.MainViewModel
import com.example.personalorganiser.R


class MainActivity : AppCompatActivity() {

    // Modern way to get the ViewModel. This is lazy and survives configuration changes.
    private val viewModel: MainViewModel by viewModels()

    private lateinit var etLocation: EditText
    private lateinit var btnFetchWeather: Button
    private lateinit var tvWeatherResult: TextView

    private lateinit var btnFetchEchoGet: Button
    private lateinit var btnFetchEchoPost: Button
    private lateinit var tvEchoResult: TextView

    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etLocation = findViewById(R.id.etLocation)
        btnFetchWeather = findViewById(R.id.btnFetchWeather)
        tvWeatherResult = findViewById(R.id.tvWeatherResult)
        btnFetchEchoGet = findViewById(R.id.btnFetchEchoGet)
        btnFetchEchoPost = findViewById(R.id.btnFetchEchoPost)
        tvEchoResult = findViewById(R.id.tvEchoResult)
        progressBar = findViewById(R.id.progressBar)

        setupListeners()
        observeViewModel()
    }

    private fun setupListeners() {
        // Use a simple lambda for a cleaner listener
        btnFetchWeather.setOnClickListener {
            val location = etLocation.text.toString().trim()
            if (location.isNotEmpty()) {
                viewModel.fetchCurrentWeather(location)
            } else {
                tvWeatherResult.text = "Please enter a location."
            }
        }

        btnFetchEchoGet.setOnClickListener {
            viewModel.fetchEchoGet("kotlin_param1", "value_kotlin")
        }

        btnFetchEchoPost.setOnClickListener {
            viewModel.sendEchoPost("myKotlinKey", "myKotlinValue")
        }
    }

    private fun observeViewModel() {
        // Observe Weather API data
        viewModel.currentWeather.observe(this, Observer { weatherResponse: WeatherResponse? ->
            weatherResponse?.let {
                // Use idiomatic Kotlin with direct property access and null-safety
                val location = it.location
                val current = it.current
                if (location != null && current != null && current.condition != null) {
                    ("Location: ${location.name}, ${location.country}\n" +
                            "Temp: ${current.tempC}°C / ${current.tempF}°F\n" +
                            "Condition: ${current.condition.text}\n" +
                            "Humidity: ${current.humidity}%").also { tvWeatherResult.text = it }
                }
            }
        })

        // Observe Weather API errors
        viewModel.weatherError.observe(this, Observer { error: String? ->
            if (!error.isNullOrEmpty()) {
                tvWeatherResult.text = error
            }
        })

        // Observe Postman Echo API data
        viewModel.echoResponse.observe(this, Observer { echoResponse: EchoResponse? ->
            echoResponse?.let {
                val userAgent = it.headers?.get("user-agent") ?: "N/A"
                ("Echo URL: ${it.url}\n" +
                        "Args: ${it.args}\n" +
                        "Headers (User-Agent): $userAgent").also { tvEchoResult.text = it }
            }
        })

        // Observe Postman Echo API errors
        viewModel.echoError.observe(this, Observer { error: String? ->
            if (!error.isNullOrEmpty()) {
                tvEchoResult.text = error
            }
        })

        // Observe loading state
        viewModel.isLoading.observe(this, Observer { isLoading: Boolean? ->
            progressBar.visibility = if (isLoading == true) View.VISIBLE else View.GONE
        })
    }
}
package com.example.personalorganiser


import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.personalorganiser.activity_main
import com.example.personalorganiser.data.EchoResponse
import com.example.personalorganiser.data.WeatherResponse
import com.example.personalorganiser.R
import com.example.personalorganiser.MainViewModel

annotation class MainViewModel


private val Unit.btnFetchEchoPost: Int
    get() {
        TODO()
    }
private val Unit.activity_main: Any
    get() {
        TODO()
    }

class MainActivity : AppCompatActivity() {
    private var viewModel: MainViewModel? = null

    private var etLocation: EditText? = null
    private var btnFetchWeather: Button? = null
    private var tvWeatherResult: TextView? = null

    private var btnFetchEchoGet: Button? = null
    private var btnFetchEchoPost: Button? = null
    private var tvEchoResult: TextView? = null

    private var progressBar: ProgressBar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ViewModelProvider(this).get<MainViewModel?>(MainViewModel::class.java).also { viewModel = it }

        etLocation = findViewById<EditText?>(R.id.etLocation)
        btnFetchWeather = findViewById<Button?>(R.id.btnFetchWeather)
        tvWeatherResult = findViewById<TextView?>(R.id.tvWeatherResult)

        btnFetchEchoGet = findViewById<Button?>(R.id.btnFetchEchoGet)
        btnFetchEchoPost = findViewById<Button?>(R.id.btnFetchEchoPost)
        tvEchoResult = findViewById<TextView?>(R.id.tvEchoResult)

        progressBar = findViewById<ProgressBar?>(R.id.progressBar)

        setupListeners()
        observeViewModel()
    }

    private fun setupListeners() {
        btnFetchWeather!!.setOnClickListener(View.OnClickListener { v: View? ->
            val location = etLocation!!.getText().toString().trim { it <= ' ' }
            if (!location.isEmpty()) {
                viewModel!!.fetchCurrentWeather(location)
                // For forecast: viewModel.fetchForecastWeather(location, 3);
            } else {
                tvWeatherResult!!.setText("Please enter a location.")
            }
        })

        btnFetchEchoGet!!.setOnClickListener(View.OnClickListener { v: View? ->
            viewModel!!.fetchEchoGet("java_param1", "value_java")
        })

        btnFetchEchoPost!!.setOnClickListener(View.OnClickListener { v: View? ->
            viewModel!!.sendEchoPost("myJavaKey", "myJavaValue")
        })
    }

    private fun observeViewModel() {
        // Observe Weather API data
        viewModel!!.getCurrentWeather()
            .observe(this, Observer { weatherResponse: WeatherResponse? ->
                if (weatherResponse != null && weatherResponse.getCurrent() != null && weatherResponse.getLocation() != null)
                    tvWeatherResult?.setText(
                        "Location: " + weatherResponse.getLocation()
                            .getName() + ", " + weatherResponse.getLocation().getCountry() + "\n" +
                                "Temp: " + weatherResponse.getCurrent()
                            .getTempC() + "°C / " + weatherResponse.getCurrent() +
                                "Condition: " + weatherResponse.getCurrent().getCondition()
                            .getText() + "\n" +
                                "Humidity: " + weatherResponse.getCurrent().getHumidity() + "%"
                    )
            })

        // Observe Weather API errors
        viewModel!!.getWeatherError().observe(this, Observer { error: String? ->
            if (error != null && !error.isEmpty()) {
                tvWeatherResult!!.setText(error)
            }
        })

        // Observe Postman Echo API data
        viewModel!!.getEchoResponse().observe(this, Observer { echoResponse: EchoResponse? ->
            if (echoResponse != null) {
                val headersUserAgent =
                    if (echoResponse.getHeaders() != null) echoResponse.getHeaders()
                        .get("user-agent") else "N/A"
                tvEchoResult!!.setText(
                    "Echo URL: " + echoResponse.getUrl() + "\n" +
                            "Args: " + echoResponse.getArgs() + "\n" +
                            "Headers (User-Agent): " + headersUserAgent
                )
            }
        })

        // Observe Postman Echo API errors
        viewModel!!.getEchoError().observe(this, Observer { error: String? ->
            if (error != null && !error.isEmpty()) {
                tvEchoResult!!.setText(error)
            }
        })

        // Observe loading state
        viewModel!!.getIsLoading().observe(this, Observer { isLoading: Boolean? ->
            progressBar!!.setVisibility(if (isLoading == true) View.VISIBLE else View.GONE)
        })
    }
}

annotation class also(val value: Any)

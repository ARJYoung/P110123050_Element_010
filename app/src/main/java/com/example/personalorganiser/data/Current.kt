package com.example.personalorganiser.data

import com.google.gson.annotations.SerializedName

data class Current(
    @SerializedName("last_updated_epoch")
    val lastUpdatedEpoch: Long?,
    @SerializedName("last_updated")
    val lastUpdated: String?,
    @SerializedName("temp_c")
    val tempC: Double?,
    @SerializedName("temp_f")
    val tempF: Double?,
    @SerializedName("is_day")
    val isDay: Int?,
    val condition: Condition?,
    @SerializedName("wind_mph")
    val windMph: Double?,
    @SerializedName("wind_kph")
    val windKph: Double?,
    @SerializedName("precip_mm")
    val precipMm: Double?,
    val humidity: Int?,
    val cloud: Int?,
    @SerializedName("feelslike_c")
    val feelslikeC: Double?,
    @SerializedName("feelslike_f")
    val feelslikeF: Double?,
    val uv: Double?
)

data class Current(
    val tempC: Double?,
    val tempF: Double?,
    val humidity: Int?,
    val condition: Condition?
)
package com.example.personalorganiser.data;

import com.google.gson.annotations.SerializedName;

public class Current {
    @SerializedName("last_updated_epoch")
    private long lastUpdatedEpoch;
    @SerializedName("last_updated")
    private String lastUpdated;
    @SerializedName("temp_c")
    private double tempC;
    @SerializedName("temp_f")
    private double tempF;
    @SerializedName("is_day")
    private int isDay;
    private Condition condition;
    @SerializedName("wind_mph")
    private double windMph;
    @SerializedName("wind_kph")
    private double windKph;
    @SerializedName("precip_mm")
    private double precipMm;
    private int humidity;
    private int cloud;
    @SerializedName("feelslike_c")
    private double feelslikeC;
    @SerializedName("feelslike_f")
    private double feelslikeF;
    private double uv;

    public double getTempC() { return tempC; }
    public Condition getCondition() { return condition; }
    public int getHumidity() { return humidity; }
    // Getters for all fields
    // Setters for all fields
}
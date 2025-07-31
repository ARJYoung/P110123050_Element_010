package com.example.personalorganiser.data;

import com.google.gson.annotations.SerializedName;

public class WeatherResponse {
    private Location location;
    private Current current;
    private Forecast forecast;

    public Location getLocation() { return location; }
    public Current getCurrent() { return current; }
    public Forecast getForecast() { return forecast; }

    public void setLocation(Location location) { this.location = location; }
    public void setCurrent(Current current) { this.current = current; }
    public void setForecast(Forecast forecast) { this.forecast = forecast; }

    // toString for debugging
}
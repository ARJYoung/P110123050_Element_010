package com.example.personalorganiser.data;

import com.google.gson.annotations.SerializedName;

public class Location {
    private String name;
    private String region;
    private String country;
    private double lat;
    private double lon;
    @SerializedName("tz_id")
    private String tzId;
    private String localtime;

    public String getName() { return name; }
    public String getRegion() { return region; }
    public String getCountry() { return country; }
    public double getLat() { return lat; }
    public double getLon() { return lon; }
    public String getTzId() { return tzId; }
    public String getLocaltime() { return localtime; }

    // Setters
}

package com.rubenrf.weather_api.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class WeatherResponse implements Serializable {

    private double latitude;
    private double longitude;
    private String resolvedAddress;

    @JsonProperty("timezone")
    private String timezone;

    @JsonProperty("days")
    private List<DayResponse> daysList;

}

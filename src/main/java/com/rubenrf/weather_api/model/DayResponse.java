package com.rubenrf.weather_api.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class DayResponse implements Serializable {

    @JsonProperty("datetime")
    private String datetime;

    @JsonProperty("tempmax")
    private double tempmax;

    @JsonProperty("tempmin")
    private double temp;

    @JsonProperty("feelslikemax")
    private double feelslikemax;

    @JsonProperty("feelslike")
    private double feelslike;

    @JsonProperty("humidity")
    private double humidity;

}

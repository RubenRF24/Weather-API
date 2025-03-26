package com.rubenrf.weather_api.service;

import com.rubenrf.weather_api.model.WeatherResponse;

public interface WeatherService {

    WeatherResponse getTodayWeather(String city);

}

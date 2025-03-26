package com.rubenrf.weather_api.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.rubenrf.weather_api.model.WeatherResponse;
import com.rubenrf.weather_api.service.WeatherService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class WeatherServiceImpl implements WeatherService {

    private final SimpleDateFormat simpleDateFormat;

    @Value("${weather.api.key}")
    private String apiKey;

    private final String baseUrl = "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline";

    @Autowired
    private RestTemplate restTemplate;

    WeatherServiceImpl(SimpleDateFormat simpleDateFormat) {
        this.simpleDateFormat = simpleDateFormat;
    }

    @Override
    @Cacheable(value = "weather", key = "#city", cacheManager = "cacheManager")
    public WeatherResponse getTodayWeather(String city) {

        log.info("#### getTodaysWeather endpoint called ####");
        String query = getQueryForToday(city);
        var todaysWeatherReponse = restTemplate.getForObject(query, WeatherResponse.class);
        log.info("Weather Response for today: {}", todaysWeatherReponse);
        return todaysWeatherReponse;

    }

    private String getQueryForToday(String city) {
        var today = getTodayDate();
        return UriComponentsBuilder.fromUriString(baseUrl)
                .pathSegment(city, today)
                .queryParam("unitGroup", "metric")
                .queryParam("elements", "datetime,tempmax,temp,feelslikemax,feelslike,humidity")
                .queryParam("include", "current,remote")
                .queryParam("key", apiKey)
                .queryParam("contentType", "json")
                .toUriString();

    }

    private String getTodayDate() {
        return simpleDateFormat.format(new Date());
    }
}

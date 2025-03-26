package com.rubenrf.weather_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rubenrf.weather_api.service.WeatherService;

import io.github.resilience4j.ratelimiter.annotation.RateLimiter;

@RestController
@RequestMapping("/api/weather")
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    @GetMapping("/{city}")
    @RateLimiter(name = "todays-weather")
    public ResponseEntity<?> getTodayWeather(@PathVariable String city) {
        return ResponseEntity.ok(weatherService.getTodayWeather(city));
    }

}

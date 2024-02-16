package com.example.weatherootd.domain;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Forecast {
    private Long id;
    private int temperature;
    private LocalDateTime dateTime;
    private WeatherType weatherType;
}

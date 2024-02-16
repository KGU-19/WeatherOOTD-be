package com.example.weatherootd.domain;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Ootd {
    private Long id;
    private int temperature;
    private LocalDateTime dateTime;
    private Style style;
    private WeatherType weatherType;
}

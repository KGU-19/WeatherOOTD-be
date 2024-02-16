package com.example.weatherootd.domain;

/**
 * 기상청 단기예보 조회 서비스를 통해 얻어냄
 */
public enum WeatherType {
    /**
     * PTY = 0
     */
    SUNNY,

    /**
     * PTY = 1
     */
    RAINY,

    /**
     *  PTY = 2 || 3
     */
    SNOWY,

    /**
     * (SKY = 3 || 4) && (PTY = 0)
     */
    CLOUDY

}

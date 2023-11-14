package com.example.labb1_martin;

public class WeatherData {
    public WeatherData(double temperature, String weatherCondition, String windSpeed, String windDirection, String humidity, String precipitation) {
        this.temperature = temperature;
        this.weatherCondition = weatherCondition;
        this.windSpeed = windSpeed;
        this.windDirection = windDirection;
        this.precipitation = precipitation;
        this.humidity = humidity;
    }

    public double getTemperature() {
        return temperature;
    }

    public String getWeatherCondition() {
        return weatherCondition;
    }

    public String getWindSpeed() {
        return windSpeed;
    }

    public String getPrecipitation() {
        return precipitation;
    }

    public String getHumidity() {
        return humidity;
    }

    public String getWindDirection() {
        return windDirection;
    }

    double temperature;
    String weatherCondition;
    String windSpeed;
    String precipitation;
    String humidity;
    String windDirection;

}


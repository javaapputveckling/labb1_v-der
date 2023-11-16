package com.example.labb1_martin;

import java.util.Hashtable;

/*
 * This class represents weather data.
 */
public class WeatherData {
    private static final Hashtable<String, String> weatherIcons_desc = new Hashtable<String, String>();
    static {
        weatherIcons_desc.put("clearsky_day", "Clear sky");
        weatherIcons_desc.put("clearsky_night", "Clear sky");
        weatherIcons_desc.put("cloudy", "Cloudy");
        weatherIcons_desc.put("fair_day", "Fair");
        weatherIcons_desc.put("fair_night", "Fair");
        weatherIcons_desc.put("fog", "Fog");
        weatherIcons_desc.put("heavyrain", "Heavy rain");
        weatherIcons_desc.put("heavyrainandthunder", "Heavy rain and thunder");
        weatherIcons_desc.put("heavyrainshowers_day", "Heavy rain showers");
        weatherIcons_desc.put("heavyrainshowers_night", "Heavy rain showers");
        weatherIcons_desc.put("heavysleet", "Heavy sleet");
        weatherIcons_desc.put("heavysleetandthunder", "Heavy sleet and thunder");
        weatherIcons_desc.put("heavysleetshowers_day", "Heavy sleet showers");
        weatherIcons_desc.put("heavysleetshowers_night", "Heavy sleet showers");
        weatherIcons_desc.put("heavysnow", "Heavy snow");
        weatherIcons_desc.put("heavysnowandthunder", "Heavy snow and thunder");
        weatherIcons_desc.put("heavysnowshowers_day", "Heavy snow showers");
        weatherIcons_desc.put("heavysnowshowers_night", "Heavy snow showers");
        weatherIcons_desc.put("lightrain", "Light rain");
        weatherIcons_desc.put("lightrainandthunder", "Light rain and thunder");
        weatherIcons_desc.put("lightrainshowers_day", "Light rain showers");
    }

    public String getWeatherIcons_desc() {
        return weatherIcons_desc.get(this.getWeatherCondition())!=null? weatherIcons_desc.get(this.getWeatherCondition()) : this.getWeatherCondition();
    }

    public WeatherData(double temperature, String weatherCondition, String windSpeed, String windDirection, String humidity, Double precipitation, String cloudiness) {
        this.temperature = temperature;
        this.weatherCondition = weatherCondition;
        this.windSpeed = windSpeed;
        this.windDirection = windDirection;
        this.precipitation = precipitation;
        this.humidity = humidity;
        this.cloudiness = cloudiness;
        this.weatherConditionImgURL = weatherIconsRepo + "/" + this.getWeatherCondition() + "." + imgFormat;
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

    public Double getPrecipitation() {
        return precipitation;
    }

    public String getHumidity() {
        return humidity;
    }

    public String getWindDirection() {
        return windDirection;
    }
    public String getCloudiness() { return cloudiness; }
    public String getWeatherImgURL() { return weatherConditionImgURL; }

    String imgFormat = "png"; // "png", "svg", "pdf"
    String weatherIconsRepo = "https://raw.githubusercontent.com/metno/weathericons/main/weather/"
            + imgFormat;
    double temperature;
    String weatherCondition;
    String windSpeed;
    Double precipitation;
    String humidity;
    String windDirection;
    String cloudiness;
    String weatherConditionImgURL;

}


package com.example.labb1_martin;

/*
 * This class represents weather data.
 */
public class WeatherData {
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


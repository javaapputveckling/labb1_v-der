package com.example.labb1_martin;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class WeatherDataFetcher {
    public WeatherData fetchWeatherData(String latitude, String longitude) throws InterruptedException, IOException {
        String url = "https://api.met.no/weatherapi/locationforecast/2.0/compact?lat=" + latitude + "&lon=" + longitude;

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .header("User-Agent", "YourApp/1.0")
                .build();

        Response response = client.newCall(request).execute();
        if (response.isSuccessful()) {
            Gson gson = new Gson();
            JsonObject jsonObject = gson.fromJson(response.body().string(), JsonObject.class);
            JsonObject current = jsonObject.getAsJsonObject("properties").getAsJsonArray("timeseries").get(0).getAsJsonObject().getAsJsonObject("data").getAsJsonObject("instant").getAsJsonObject("details");
            double temperature = current.get("air_temperature").getAsDouble();
            JsonObject next1Hour = jsonObject.getAsJsonObject("properties").getAsJsonArray("timeseries").get(0).getAsJsonObject().getAsJsonObject("data").getAsJsonObject("next_1_hours").getAsJsonObject("summary");
            String weatherCondition = next1Hour.has("symbol_code") ? next1Hour.get("symbol_code").getAsString() : "unknown";
            String windSpeed = current.get("wind_speed").getAsString();
            String windDirection = current.get("wind_from_direction").getAsString();
            String humidity = current.get("relative_humidity").getAsString();
            String precipitation ="";// = next1Hour.getAsJsonObject("details").get("precipitation_amount").getAsString();
            return new WeatherData(temperature, weatherCondition, windSpeed, windDirection, humidity, precipitation);
        } else throw new IOException("Failed to retrieve JSON data. Status code: " + response.message());



    }

}


package com.example.labb1_martin;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/*
    * This class fetches weather data from the API and returns it as a WeatherData object.
 */
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
            assert response.body() != null;
            JsonObject jsonObject = gson.fromJson(response.body().string(), JsonObject.class);

            // get current data into a JsonObject by navigating API object
            JsonObject current = jsonObject.getAsJsonObject("properties").
                    getAsJsonArray("timeseries").
                    get(0).getAsJsonObject().getAsJsonObject("data").
                    getAsJsonObject("instant").
                    getAsJsonObject("details");

            // get future data into a JsonObject by navigating API object
            JsonObject next1Hour = jsonObject.getAsJsonObject("properties").
                    getAsJsonArray("timeseries").
                    get(0).getAsJsonObject().
                    getAsJsonObject("data").
                    getAsJsonObject("next_1_hours").
                    getAsJsonObject("summary");

            // assign json data
            double temperature = current.get("air_temperature").getAsDouble();
            String weatherCondition = next1Hour.has("symbol_code") ? next1Hour.get("symbol_code").getAsString() : "unknown";
            String windSpeed = current.get("wind_speed").getAsString();
            String windDirection = current.get("wind_from_direction").getAsString();
            String humidity = current.get("relative_humidity").getAsString();
            double precipitation = jsonObject.getAsJsonObject("properties")
                    .getAsJsonArray("timeseries").get(0).getAsJsonObject()
                    .getAsJsonObject("data").getAsJsonObject("next_1_hours")
                    .getAsJsonObject("details").get("precipitation_amount").getAsDouble();
            //Double precipitation = next1Hour.getAsJsonObject("details").get("precipitation_amount").getAsDouble(); //TODO: get precipitation from API
            String cloudiness = current.get("cloud_area_fraction").getAsString();
            // return json data
            return new WeatherData(temperature, weatherCondition, windSpeed, windDirection, humidity, precipitation, cloudiness);
        }
        else {
            throw new IOException("Failed to retrieve JSON data. Status code: " + response.message());
        }


    }



}


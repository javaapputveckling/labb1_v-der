package com.example.labb1_martin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.Executor;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
/*
        Executor executor = Executors.newSingleThreadExecutor();

        executor.execute(()->{
            WeatherDataFetcher weatherDataFetcher = new WeatherDataFetcher();
            try {
                WeatherDataFetcher weatherDataFetcher1 = new WeatherDataFetcher();
                System.out.println(weatherDataFetcher1.fetchWeatherData("60.10", "9.58").getTemperature());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });*/


    }
}
//hej
//hej
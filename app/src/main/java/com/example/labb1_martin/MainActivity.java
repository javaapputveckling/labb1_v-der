package com.example.labb1_martin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.TextView;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.Executor;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Executor executor = Executors.newSingleThreadExecutor();
        Handler mainHandler = new Handler(Looper.getMainLooper());

        executor.execute(()->{
            try {
                WeatherDataFetcher weatherDataFetcher = new WeatherDataFetcher();
                WeatherData weatherData;
                weatherData = weatherDataFetcher.fetchWeatherData("60.10", "9.58");
                //System.out.println(weatherData.getTemperature());
                double temperature = weatherData.getTemperature();
                mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        TextView helloWorldText = findViewById(R.id.HelloWorldText);
                        helloWorldText.setText(String.valueOf(temperature));
                    }
                });
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });


    }
}
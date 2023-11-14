package com.example.labb1_martin;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.TextView;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.Executor;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button refresh;
    TextView rain;
    TextView cloud;
    TextView temp;
    TextView WindSpeed;
    ImageView MainImage;



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
                        TextView helloWorldText = findViewById(R.id.id_rain);
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

    public void displayApp() {
        initialView();
        SetValues();
        refresh.setOnClickListener(view -> {SetValues();});
    }

    /**
     * Updates values on UI
     */
    public void SetValues (){
    WindSpeed.setText(degToCompass(360));
    rain.setText("no rain");
    cloud.setText("Cloudy");
    temp.setText("temp");

    }

    /**
     * Saves view ids in variables
     */
    public void initialView (){

       refresh = findViewById(R.id.id_refresh_btn);
       rain = findViewById(R.id.id_rain);
       cloud = findViewById(R.id.id_clouds);
       temp = findViewById(R.id.id_temp);
       WindSpeed = findViewById(R.id.id_wind);
       MainImage = findViewById(R.id.Id_main_image);

    }

    /**
     *
     * @param num which is wind degree
     * Converts wind degree to direction
     * @return directions
     */
    public String degToCompass (float num){
        String arr [] = {"N", "NE", "E", "SE", "S", "SW", "W", "NW", "N" };
        return arr [(int)Math.round(((float)  num % 360) / 45)];
    }

}
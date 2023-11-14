package com.example.labb1_martin;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.TextView;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.Executor;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.util.Log;
import java.util.ArrayList;

import kotlinx.coroutines.internal.Symbol;

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

        executeParsing();
        displayApp();
    }

    public void executeParsing() {
        Executor executor = Executors.newSingleThreadExecutor();
        Handler mainHandler = new Handler(Looper.getMainLooper());

        executor.execute(()->{
            try {
                WeatherData weatherData;
                WeatherDataFetcher weatherDataFetcher = new WeatherDataFetcher();
                weatherData = weatherDataFetcher.fetchWeatherData("62.930812", "17.306927");

                System.out.println("WEATHER TEMP: " + weatherData.getTemperature());
                double temperature = weatherData.getTemperature();
                String cloudiness = weatherData.getHumidity();
                String windspeed = weatherData.getWindSpeed();
                String rain = weatherData.getPrecipitation();
                mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        TextView helloWorldText = findViewById(R.id.id_temp);
                        helloWorldText.setText(String.valueOf(temperature));
                    }
                });
            } catch (InterruptedException | IOException e) {
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
        Log.d("TEMPERATURE","SetValues: " + 123.123);

        // double tempDouble = weatherData.getTemperature();

        // System.out.println(weatherData.getHumidity());


        WindSpeed.setText(degToCompass(360));
        rain.setText("no rain");
        cloud.setText("Cloudy");
        temp.setText("strTemp");

    }

    /**
     * Saves view ids in variables
     */
    public void initialView () {

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
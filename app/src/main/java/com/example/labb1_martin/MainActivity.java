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


    String temperatureVar;
    String cloudinessVar;
    String windspeedVar;
    String rainVar;
    String windDir;

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
                WeatherData tempData;
                WeatherDataFetcher weatherDataFetcher = new WeatherDataFetcher();
                tempData = weatherDataFetcher.fetchWeatherData("62.930812", "17.306927");

                temperatureVar = "Temp: " + Double.toString(tempData.getTemperature());
                cloudinessVar = "Cloud: " + tempData.getHumidity();
                windDir = degToCompass(tempData.getWindDirection());
                windspeedVar = "Windspeed: " + tempData.getWindSpeed() + " at direction " + windDir;
                rainVar = "Rain: " + tempData.getPrecipitation();
                mainHandler.post(() -> {
                    temp = findViewById(R.id.id_temp);
                    temp.setText(temperatureVar);
                    cloud = findViewById(R.id.id_clouds);
                    cloud.setText(cloudinessVar);
                    WindSpeed = findViewById(R.id.id_wind);
                    WindSpeed.setText(windspeedVar);
                    rain = findViewById(R.id.id_rain);
                    rain.setText(rainVar);
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


        WindSpeed.setText(windspeedVar);
        rain.setText(rainVar);
        cloud.setText(cloudinessVar);
        temp.setText(temperatureVar);

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
     * @param windDegree which is wind degree in String
     * Converts wind degree to direction
     * @return directions
     */
    public String degToCompass(String windDegree){
        float windFloat = Float.parseFloat(windDegree);
        String arr [] = {"N", "NE", "E", "SE", "S", "SW", "W", "NW", "N" };
        return arr [(int)Math.round(((float)  windFloat % 360) / 45)];
    }

}
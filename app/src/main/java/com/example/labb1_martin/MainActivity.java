package com.example.labb1_martin;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import java.io.IOException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

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
    String imageURL;
    String windDir;
    String iconImg;

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


                temperatureVar = "Temp: " + tempData.getTemperature()+"°";
                cloudinessVar = "Cloud: " + tempData.getCloudiness()+"%";
                windspeedVar = "Windspeed: " + tempData.getWindSpeed() + "m/s  " + degToCompass(Float.parseFloat(tempData.getWindDirection()));
                rainVar = "Rain: " + tempData.getPrecipitation()+" mm";
                imageURL = tempData.getWeatherImgURL();
                mainHandler.post(() -> {
                    temp = findViewById(R.id.id_temp);
                    cloud = findViewById(R.id.id_clouds);
                    WindSpeed = findViewById(R.id.id_wind);
                    rain = findViewById(R.id.id_rain);

                    setValues();


                });
            } catch (InterruptedException | IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public void displayApp() {
        initialView();
        setValues();
        refresh.setOnClickListener(view -> setValues());
    }

    /**
     * Updates values on UI
     */
    public void setValues(){
        Log.d("TEMPERATURE","SetValues: " + 123.123);


        WindSpeed.setText(windspeedVar);
        rain.setText(rainVar);
        cloud.setText(cloudinessVar);
        temp.setText(temperatureVar);

        Glide.with(this)
                .load(imageURL)
                .into(MainImage);

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

     * @param windDegree which is wind degree in String
     * Converts wind degree to direction
     * @return directions
     */
    public String degToCompass (float num){
        String[] arr = {"N", "NE", "E", "SE", "S", "SW", "W", "NW", "N" };
        return arr [(int)Math.round(((float)  num % 360) / 45)];

    }

}
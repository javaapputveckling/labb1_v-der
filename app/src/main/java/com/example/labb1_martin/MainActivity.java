package com.example.labb1_martin;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
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

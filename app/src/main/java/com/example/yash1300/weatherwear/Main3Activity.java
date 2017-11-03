package com.example.yash1300.weatherwear;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class Main3Activity extends AppCompatActivity {
String mainCondition;
    ImageView clothImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        getWindow().setStatusBarColor(Color.parseColor("#3F51B5"));
        getWindow().setNavigationBarColor(Color.parseColor("#303F9F"));
        clothImage = (ImageView) findViewById(R.id.clothImage);
        mainCondition = getIntent().getExtras().getString("condition");

        if (mainCondition.replaceAll("\\s", "").toLowerCase().contains("thunder") || mainCondition.replaceAll("\\s", "").toLowerCase().contains("rain")){
            clothImage.setImageResource(R.drawable.mapicon);
        }
        else if (mainCondition.replaceAll("\\s", "").toLowerCase().contains("sun")){
            clothImage.setImageResource(R.drawable.mapicon);
        }
        else if (mainCondition.replaceAll("\\s", "").toLowerCase().contains("cloud")){
            clothImage.setImageResource(R.drawable.mapicon);
        }
        else{
            clothImage.setImageResource(R.drawable.mapicon);
        }
    }
}

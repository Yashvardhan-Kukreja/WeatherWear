package com.example.yash1300.weatherwear;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class WearWithDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wear_with_detail);

        getWindow().setStatusBarColor(Color.parseColor("#3F51B5"));
        getWindow().setNavigationBarColor(Color.parseColor("#303F9F"));

    }
}

package com.example.yash1300.weatherwear;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.RelativeLayout;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

public class SplashScreen extends AppCompatActivity {
RelativeLayout splashLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        splashLayout = (RelativeLayout) findViewById(R.id.splashLayout);

        getWindow().setStatusBarColor(Color.parseColor("#3F51B5"));
        getWindow().setNavigationBarColor(Color.parseColor("#303F9F"));

        YoYo.with(Techniques.FadeIn).duration(2500).repeat(1).playOn(splashLayout);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SplashScreen.this, BasicDetails.class);
                startActivity(i);
            }
        }, 2500);
    }
}

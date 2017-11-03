package com.example.yash1300.weatherwear;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

public class MainActivity extends AppCompatActivity {
EditText ecity, estate;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setNavigationBarColor(Color.parseColor("#303F9F"));
        getWindow().setStatusBarColor(Color.parseColor("#3F51B5"));
        getWindow().getDecorView().setBackgroundColor(Color.parseColor("#3F51B5"));

        ecity = (EditText) findViewById(R.id.city);
        estate = (EditText) findViewById(R.id.state);
        btn = (Button) findViewById(R.id.proceed);
        btn.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (!(ecity.getText().toString().isEmpty()) && !(estate.getText().toString().isEmpty())) {
                    Intent i = new Intent(MainActivity.this, Main2Activity.class);
                    i.putExtra("city", ecity.getText().toString());
                    i.putExtra("state", estate.getText().toString());
                    startActivity(i);
                } else if (ecity.getText().toString().isEmpty() && estate.getText().toString().isEmpty()){
                    YoYo.with(Techniques.Shake).duration(700).repeat(1).playOn(btn);
                    Toast.makeText(MainActivity.this, "Please fill all the details", Toast.LENGTH_SHORT).show();
                } else {
                    if (ecity.getText().toString().isEmpty()){
                        YoYo.with(Techniques.Shake).duration(700).repeat(1).playOn(ecity);
                        Toast.makeText(MainActivity.this, "Please fill the city", Toast.LENGTH_SHORT).show();
                    } else {
                        YoYo.with(Techniques.Shake).duration(700).repeat(1).playOn(estate);
                        Toast.makeText(MainActivity.this, "Please fill the state or country", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
}

package com.example.yash1300.weatherwear;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MethodSelection extends AppCompatActivity {
String name;
    TextView greet;
    LinearLayout mapsLayout, typingLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_method_selection);

        getWindow().setStatusBarColor(Color.parseColor("#3F51B5"));
        getWindow().setNavigationBarColor(Color.parseColor("#303F9F"));

        name = getIntent().getExtras().getString("name");
        String nameArray[] = name.split(" ");
        String firstName = nameArray[0].substring(0,1).toUpperCase()+nameArray[0].substring(1,nameArray[0].length()).toLowerCase();

        greet = (TextView) findViewById(R.id.greeting);
        greet.setText("Hello,\n "+firstName);

        mapsLayout = (LinearLayout) findViewById(R.id.mapsLayout);
        typingLayout = (LinearLayout) findViewById(R.id.typingLayout);
        mapsLayout.setOnClickListener(new LinearLayout.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MethodSelection.this, MapsActivity2.class);
                startActivity(i);
            }
        });
        typingLayout.setOnClickListener(new LinearLayout.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i2 = new Intent(MethodSelection.this, MainActivity.class);
                startActivity(i2);
            }
        });

    }
}

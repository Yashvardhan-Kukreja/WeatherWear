package com.example.yash1300.weatherwear;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

public class BasicDetails extends AppCompatActivity {
EditText name;
    Button btn;
    RadioGroup radioGroup;
    String choice = "0";

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK){
            Intent i = new Intent(Intent.ACTION_MAIN);
            i.addCategory(Intent.CATEGORY_HOME);
            startActivity(i);
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_details);

        getWindow().setStatusBarColor(Color.parseColor("#3F51B5"));
        getWindow().setNavigationBarColor(Color.parseColor("#303F9F"));
        name = (EditText) findViewById(R.id.nameInput);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch(checkedId){
                    case R.id.radioBoy:
                        choice = "1";
                        break;
                    case R.id.radioGirl:
                        choice = "2";
                        break;
                    default:
                        choice = "0";
                        break;
                }
            }
        });
        btn = (Button) findViewById(R.id.basicDetProceed);
        btn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!name.getText().toString().isEmpty() && (choice.equals("1") || choice.equals("2"))) {
                    Intent i1 = new Intent(BasicDetails.this, MethodSelection.class);
                    i1.putExtra("name", name.getText().toString());
                    startActivity(i1);
                } else if (!(choice.equals("1") || choice.equals("2")) && name.getText().toString().isEmpty()) {
                    YoYo.with(Techniques.Shake).duration(700).repeat(1).playOn(btn);
                    Toast.makeText(BasicDetails.this, "Please fill all the details", Toast.LENGTH_SHORT).show();
                } else {
                    if (name.getText().toString().isEmpty()) {
                        YoYo.with(Techniques.Shake).duration(700).repeat(1).playOn(name);
                        Toast.makeText(BasicDetails.this, "Please fill your name", Toast.LENGTH_SHORT).show();
                    } else {
                        YoYo.with(Techniques.Shake).duration(700).repeat(1).playOn(radioGroup);
                        Toast.makeText(BasicDetails.this, "Please select your gender", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });





    }
}

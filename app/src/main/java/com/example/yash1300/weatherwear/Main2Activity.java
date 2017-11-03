package com.example.yash1300.weatherwear;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity {
String state, city;
    String url;
    List<Weather> mainList = new ArrayList<>();
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    TextView toplace, todate, today, toHighT, toLowT, toTextT;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        getWindow().setStatusBarColor(Color.parseColor("#3F51B5"));
        getWindow().setNavigationBarColor(Color.parseColor("#303F9F"));
        toplace = (TextView) findViewById(R.id.toplace);
        todate = (TextView) findViewById(R.id.todate);
        today = (TextView) findViewById(R.id.today);
        toHighT = (TextView) findViewById(R.id.toHighT);
        toLowT = (TextView) findViewById(R.id.toLowT);
        toTextT = (TextView) findViewById(R.id.toTextT);

        final ProgressDialog progressDialog = new ProgressDialog(Main2Activity.this);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        recyclerView = (RecyclerView) findViewById(R.id.weatherRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(Main2Activity.this));

        state = getIntent().getExtras().getString("state").replaceAll("\\s","").toLowerCase();
        city = getIntent().getExtras().getString("city").replaceAll("\\s","").toLowerCase();

        url = "https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20weather.forecast%20where%20woeid%20in%20(select%20woeid%20from%20geo.places(1)%20where%20text%3D%22"+city+"%2C"+state+"%22)&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                progressDialog.dismiss();
                try{
                    JSONArray forecastArray = jsonObject.getJSONObject("query").
                            getJSONObject("results").getJSONObject("channel").getJSONObject("item").getJSONArray("forecast");
                    JSONObject thePlace = jsonObject.getJSONObject("query").
                            getJSONObject("results").getJSONObject("channel").getJSONObject("location");
                    toplace.setText(thePlace.getString("city") + ", " + thePlace.getString("country"));
                    todate.setText(forecastArray.getJSONObject(0).getString("date"));
                    today.setText(forecastArray.getJSONObject(0).getString("day"));
                    toHighT.setText("Highest Temperature: " + forecastArray.getJSONObject(0).getString("high") + " \u00b0F");
                    toLowT.setText("Lowest Temperature: " + forecastArray.getJSONObject(0).getString("low") + " \u00b0F");
                    toTextT.setText(forecastArray.getJSONObject(0).getString("text"));
                    for (int i=1; i<forecastArray.length(); i++){
                        String date, day, high, low, text;
                        date = forecastArray.getJSONObject(i).getString("date");
                        day = forecastArray.getJSONObject(i).getString("day");
                        high = "Highest Temperature: " + forecastArray.getJSONObject(i).getString("high") + " \u00b0F";
                        low = "Lowest Temperature: " + forecastArray.getJSONObject(i).getString("low") + " \u00b0F";
                        text = forecastArray.getJSONObject(i).getString("text");
                        mainList.add(new Weather(date, day, high, low, text));

                        adapter = new CustomAdapter(mainList, Main2Activity.this);
                        recyclerView.setAdapter(adapter);
                    }

                }catch (JSONException e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                volleyError.printStackTrace();
            }
        });

        Volley.newRequestQueue(Main2Activity.this).add(jsonObjectRequest);
    }
}

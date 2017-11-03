package com.example.yash1300.weatherwear;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class MapsActivity2 extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    Button search;
    String country, locality;
    Marker marker = null;

    private GoogleMap.OnMyLocationChangeListener myLocationChangeListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps2);
        getWindow().setStatusBarColor(Color.parseColor("#3f51b5"));
        initiateMap();
        search = (Button) findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    EditText editText = (EditText) findViewById(R.id.editText);
                    String location = editText.getText().toString();
                    if (location.isEmpty()) {
                        Toast.makeText(MapsActivity2.this, "Enter the place", Toast.LENGTH_SHORT).show();
                    } else {
                        Geocoder geocoder = new Geocoder(MapsActivity2.this);
                        List<Address> addresses = geocoder.getFromLocationName(location, 1);
                        Address address = addresses.get(0);
                        locality = address.getLocality();
                        country = address.getCountryName();
                        double lat = address.getLatitude();
                        double lng = address.getLongitude();
                        gotolocationZoom(lat, lng, 15);
                        LatLng latLng = new LatLng(lat, lng);
                        if (marker != null){
                            marker.remove();
                        }
                        marker = mMap.addMarker(new MarkerOptions().position(latLng).title(locality).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        Button proceed = (Button) findViewById(R.id.proceed);
        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MapsActivity2.this, Main2Activity.class);
                i.putExtra("state", country);
                i.putExtra("city", locality);
                startActivity(i);
            }
        });

    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        gotolocation(20.5937, 78.9629);
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        mMap.setMyLocationEnabled(true);
    }


    private void initiateMap(){
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        Toast.makeText(MapsActivity2.this, "Please turn on GPS to get your present location", Toast.LENGTH_SHORT).show();

    }

    private void gotolocation(double lat, double lng){
        LatLng latLng = new LatLng(lat, lng);
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLng(latLng);
        mMap.animateCamera(cameraUpdate);
    }

    private void gotolocationZoom(double lat, double lng, float zoom){
        LatLng latLng = new LatLng(lat, lng);
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, zoom);
        mMap.animateCamera(cameraUpdate);
    }

}

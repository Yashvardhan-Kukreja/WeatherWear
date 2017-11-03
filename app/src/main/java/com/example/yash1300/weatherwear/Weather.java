package com.example.yash1300.weatherwear;

/**
 * Created by Yash 1300 on 17-06-2017.
 */

public class Weather {
    private String date, day, high, low, text;

    public Weather(String date, String day, String high, String low, String text) {
        this.date = date;
        this.day = day;
        this.high = high;
        this.low = low;
        this.text = text;
    }

    public String getDate() {
        return date;
    }

    public String getDay() {
        return day;
    }

    public String getHigh() {
        return high;
    }

    public String getLow() {
        return low;
    }

    public String getText() {
        return text;
    }
}

package com.awareness.shopping;

import android.app.Application;

import com.awareness.shopping.data.WeatherDescription;

public class DemoApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Utils.createNotificationChannel(this);
        WeatherDescription.init(this);
    }
}

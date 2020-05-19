package com.awareness.shopping;

import android.app.Application;

public class DemoApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        NotificationUtils.createNotificationChannel(this);
        WeatherDescription.init(this);
    }
}

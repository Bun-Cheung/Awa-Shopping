package com.awareness.shopping;

import android.app.Application;

public class DemoApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Utils.createNotificationChannel(this);
    }
}

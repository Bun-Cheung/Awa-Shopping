package com.awareness.shopping;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;

import androidx.annotation.Nullable;

import com.huawei.hms.kit.awareness.barrier.BarrierStatus;

import static com.awareness.shopping.MainActivity.COMBINED_BARRIER_LABEL;

public class BarrierService extends IntentService {

    public BarrierService() {
        super("BarrierService");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationManager manager = getSystemService(NotificationManager.class);
            if (manager == null) {
                return;
            }
            Notification notification = new Notification.Builder(this, NotificationUtils.CHANNEL_ID).build();
            startForeground(1, notification);
        }
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if (intent == null) {
            return;
        }
        BarrierStatus barrierStatus = BarrierStatus.extract(intent);
        int status = barrierStatus.getPresentStatus();
        String label = barrierStatus.getBarrierLabel();
        if (status == BarrierStatus.TRUE && label.equals(COMBINED_BARRIER_LABEL)) {
            NotificationUtils.sendNotification(this,"Multiple coupons in the shoppingMall A");
        }
    }
}

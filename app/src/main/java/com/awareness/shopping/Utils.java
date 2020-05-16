package com.awareness.shopping;


import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.widget.RemoteViews;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;


public class Utils {
    private static final String CHANNEL_ID = "Awa_Shopping";

    static void sendNotification(Context context, String content) {
        RemoteViews notificationLayout = new RemoteViews(context.getPackageName(), R.layout.view_notification);
        notificationLayout.setTextViewText(R.id.notification_content, content);
        RemoteViews notificationLayoutExpand = new RemoteViews(context.getPackageName(), R.layout.view_notification_expand);
        notificationLayoutExpand.setTextViewText(R.id.notification_content, content);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID);
        builder.setSmallIcon(R.mipmap.ic_launcher_round)
                .setStyle(new NotificationCompat.DecoratedCustomViewStyle())
                .setCustomContentView(notificationLayout)
                .setCustomBigContentView(notificationLayoutExpand)
                .setPriority(NotificationCompat.PRIORITY_HIGH);
        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(context);
        managerCompat.notify(0, builder.build());
    }

    static void createNotificationChannel(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationManager manager = context.getSystemService(NotificationManager.class);
            if (manager == null) {
                return;
            }
            CharSequence name = "Coupon Info";
            String description = "Preferential information of surrounding merchants";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            manager.createNotificationChannel(channel);
        }
    }
}

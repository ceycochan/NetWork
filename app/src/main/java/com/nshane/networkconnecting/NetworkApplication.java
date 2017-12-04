package com.nshane.networkconnecting;

import android.app.Application;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

/**
 * Created by da on 2017-12-4.
 */

public class NetworkApplication extends Application{
    public static Context context;

    private static NetworkApplication mInstance;
    public static NetworkApplication getInstance() {
        return mInstance;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }

    public static void sendNotification(String content) {
        final Intent nowReuseIntent = new Intent();
        nowReuseIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        nowReuseIntent.putExtra("from", "longtime_reuse");
        nowReuseIntent.setComponent(new ComponentName("com.nshane", "com.nshane.networkconnecting.ui.TestActivity"));
        PendingIntent clickIntent = PendingIntent.getActivity(context, 0, nowReuseIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationManager notifyManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.mipmap.icon)
                .setContentTitle(NetworkApplication.context.getString(R.string.app_name))
                .setContentText(content)
                .setAutoCancel(true)
                .setContentIntent(clickIntent);
//        if (CommonUtils.isJellyBeanMR1()) {
//            builder.setShowWhen(false);
//        }
        notifyManager.notify(1, builder.build());
    }

}

package com.aerial.classprep;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.NotificationCompat;

/**
 * Created by User on 6/24/2017.
 */

public class AlertReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        System.out.println("EXO && WANNA ONE");
        String sub = intent.getExtras().getString("subject");
        String less = intent.getExtras().getString("lesson");
        createNotification(context,sub,less,"Alert");
    }

    public void createNotification(Context context, String msg, String msgText, String alert) {
        PendingIntent pendingIntent=PendingIntent.getActivity(context,0,new Intent(context,MainActivity.class),0);

        NotificationCompat.Builder builder= (NotificationCompat.Builder) new NotificationCompat.Builder(context)
                .setContentTitle(msg)
                .setContentText(msgText)
                .setTicker(alert)
                .setSmallIcon(R.drawable.ic_stat_name);

        builder.setAutoCancel(true);
        builder.setContentIntent(pendingIntent);
        builder.setDefaults(NotificationCompat.DEFAULT_SOUND);

        NotificationManager notificationManager=(NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1,builder.build());
    }
}

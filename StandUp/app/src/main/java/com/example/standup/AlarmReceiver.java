package com.example.standup;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;

public class AlarmReceiver extends BroadcastReceiver {
    private NotificationManager mNotificationManager;
    private static final int NOTIFICATION_ID = 0;

    // Notification channel ID.
    private static final String PRIMARY_CHANNEL_ID =
            "primary_notification_channel";
    @Override
    public void onReceive(Context context, Intent intent) {
       mNotificationManager=(NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        deliverNotification(context);

    }
    private void deliverNotification(Context context){
        Intent intent=new Intent(context,MainActivity.class);
        PendingIntent contentPendingIntent=PendingIntent.getActivity(context,NOTIFICATION_ID,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        //il ruolo delPendingIntent flags è quello di dirre al sistema come gestire molteplici istannces dello stesso pendingIntent,FLAG_UPDATE_CURRENT usa il vecchio intent ma rimpiazza gli extras data,cosi possiamo usare lo stesso intent over and over
        NotificationCompat.Builder builder= new NotificationCompat.Builder(context,PRIMARY_CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_alarm_notification)
                .setContentTitle("Stand Up Alert")
                .setContentText("Tou should stand up and star to walk areound now!")
                .setContentIntent(contentPendingIntent)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(true)
                .setDefaults(NotificationCompat.DEFAULT_ALL);
        mNotificationManager.notify(NOTIFICATION_ID, builder.build());
    }
}
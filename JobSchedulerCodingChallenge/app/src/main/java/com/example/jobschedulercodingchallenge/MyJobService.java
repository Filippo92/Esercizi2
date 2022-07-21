package com.example.jobschedulercodingchallenge;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

import java.lang.ref.WeakReference;
import java.util.concurrent.Executor;

public class MyJobService extends JobService {

   private JobParameters params;
    //Notificatio channel id
    private static final String PRIMARY_CHANNEL_ID="primary_notification_channel";

    NotificationManager mNotifYManager;



    @Override
    public boolean onStartJob(JobParameters params) {
        createNotificationChannel();

        //settiamo l'intetn che lancera l'app quando cliccata
        PendingIntent contentPending=  PendingIntent.getActivity(this,0,new Intent(this,MainActivity.class),PendingIntent.FLAG_UPDATE_CURRENT);

        //costruisco la notifica con le sue caratteristiche

        NotificationCompat.Builder builder=new NotificationCompat.Builder(this,PRIMARY_CHANNEL_ID)
                .setContentTitle("Job Service")
                .setContentText("Your Job ran to completion!")
                .setContentIntent(contentPending)
                .setSmallIcon(R.drawable.ic_notification)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setAutoCancel(true);

        Notification notification=builder.build();

        mNotifYManager.notify(0,notification);

        Task task1=new Task();
        task1.execute();
        jobFinished(params,false);

        return true;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        return false;
    }

    //creiamo un canale per notifiche per versione superio 8
    public void createNotificationChannel(){

        //creiamo un notificationManager
        mNotifYManager=(NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){

            //creiamo il notification channel con tutti i parametri
            NotificationChannel notificationChannel=new NotificationChannel(PRIMARY_CHANNEL_ID,"job service notification",NotificationManager.IMPORTANCE_HIGH);

            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.enableVibration(true);
            notificationChannel.setDescription("Notification from job service");

            mNotifYManager.createNotificationChannel(notificationChannel);
        }

    }




    public class Task extends AsyncTask<Void,Integer,String> {



        @Override
        protected String doInBackground(Void... voids) {
            Log.d("Task","start the task");
            return null ;
        }


        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            Log.d("task","finish task");
         ;

        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);

        }




    }



}

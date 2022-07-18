package com.example.standup;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {


    private NotificationManager mNotificationManager;
    private static final int NOTIFICATION_ID=0;
    private static final String PRIMARY_CHANNEL_ID="primary_notification_channel";
    private Button nextAlarm ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ToggleButton alarmToggle=findViewById(R.id.alarmToggle);

        nextAlarm=findViewById(R.id.next_alarm_id);

        // This PendingIntent delivers an intent letting the app know it is time to update the remaining time in the notification.
        Intent notifyIntent= new Intent(this,AlarmReceiver.class);
        final PendingIntent notifyPendingIntent = PendingIntent.getBroadcast
                (this, NOTIFICATION_ID, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        //The AlarmManager is responsible for delivering the PendingIntent at a specified interval.
        final AlarmManager alarmManager=(AlarmManager) getSystemService(ALARM_SERVICE);
        boolean alarmUp = (PendingIntent.getBroadcast(this, NOTIFICATION_ID, notifyIntent,
                PendingIntent.FLAG_NO_CREATE) != null);
        alarmToggle.setChecked(alarmUp);
        nextAlarm=findViewById(R.id.next_alarm_id);

        alarmToggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked){
                  //prendo il tempo attuale in millisecondi
                    long intervalTime=AlarmManager.INTERVAL_FIFTEEN_MINUTES;
                    long triggerTime = System.currentTimeMillis() + intervalTime ;
                    if(alarmManager != null){
                      //  alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, triggerTime,repeatInterval,notifyPendingIntent);

                      // set di alarmClock passando il tempo che voglio impostare per l'aarlarme e il pendingiNTENT che trasmette l'info
                        AlarmManager.AlarmClockInfo next= new AlarmManager.AlarmClockInfo(triggerTime,notifyPendingIntent);
                      alarmManager.setAlarmClock(next,notifyPendingIntent);
                      long alarm=next.getTriggerTime();

                      //trasformo i millisecondi nel formato della data,richiamando il metodo getDate sotto costruito
                    String dateAlarm= getDate(alarm,"dd/MM/yyyy hh:mm:ss.SSS");

                        nextAlarm.setOnClickListener(new View.OnClickListener() {
                           @Override
                           public void onClick(View v) {
                                Toast.makeText(MainActivity.this,dateAlarm,Toast.LENGTH_LONG).show();
                           }
                       });
                    }
                }else{
                    if (alarmManager != null) {
                        alarmManager.cancel(notifyPendingIntent);
                    }
                    String message;
                    message=getString(R.string.turnOff_toast);
                    Toast.makeText(MainActivity.this,message,Toast.LENGTH_LONG).show();
                }

            }
        });
        mNotificationManager=(NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        createNotificationChannel();



    }

    public void createNotificationChannel(){
       //CREO UN NOTIFICATIOmANAGER
        mNotificationManager=(NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        //per versione sopra 8.0(API level 27) devo creare un notification channel
        //avvio un check per vedere versionamento di SDK
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){

            //creo NotificationChannel con tutti i paramentri
            NotificationChannel notificationChannel= new NotificationChannel(PRIMARY_CHANNEL_ID,"Stand up Notification",NotificationManager.IMPORTANCE_HIGH);
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.enableVibration(true);
            notificationChannel.setDescription("Notifies every 15 minutes to stand up and walk");
            mNotificationManager.createNotificationChannel(notificationChannel);
        }
    }

    public static String getDate(long milliSeconds, String dateFormat)
    {
        // Create a DateFormatter object for displaying date in specified format.
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);

        // Create a calendar object that will convert the date and time value in milliseconds to date.
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds);
        return formatter.format(calendar.getTime());
    }


}
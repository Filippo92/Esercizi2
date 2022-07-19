package com.example.notificationscheduler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.app.NotificationManager;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private RadioGroup networkOptions;
    private Button scheduleJob;
    private Button cancelJobs;
    private JobScheduler mJobScheduler;
    
    private static final int JOB_ID = 0;

    //Override deadline seekbar
    private SeekBar mSeekBar;

    //Switches for setting job options
    private SwitchCompat mDeviceIdleSwitch;
    private SwitchCompat mDeviceChargingSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        scheduleJob = findViewById(R.id.schedule);
        cancelJobs=findViewById(R.id.cancel);
        mDeviceIdleSwitch = findViewById(R.id.idleSwitch);
        mDeviceChargingSwitch = findViewById(R.id.chargingSwitch);
        networkOptions=findViewById(R.id.networkOptions);
        mSeekBar=findViewById(R.id.seekBar);
        final TextView seekBarProgress = findViewById(R.id.seekBarProgress);
        scheduleJob.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                int selectedNetworkId = networkOptions.getCheckedRadioButtonId();
                int selectedNetworkOption = JobInfo.NETWORK_TYPE_NONE;

                //per vedere progresso avanzamento
                int seekBarInteger=mSeekBar.getProgress();
                boolean seekBarSet=seekBarInteger>0;

                //INIZIALIZZO il jobscheduler
                mJobScheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);

                switch (selectedNetworkId) {
                    case R.id.noNetwork:

                        selectedNetworkOption = JobInfo.NETWORK_TYPE_NONE;
                        break;
                    case R.id.anyNtwork:
                        selectedNetworkOption = JobInfo.NETWORK_TYPE_ANY;
                        break;
                    case R.id.wifiNetwork:
                        selectedNetworkOption = JobInfo.NETWORK_TYPE_UNMETERED;
                        break;
                }
                ComponentName service = new ComponentName(getPackageName(), NotificationJobService.class.getName());
                JobInfo.Builder builder = new JobInfo.Builder(JOB_ID, service)
                        .setRequiresDeviceIdle(mDeviceIdleSwitch.isChecked())
                        .setRequiresCharging(mDeviceChargingSwitch.isChecked());;
                builder.setRequiredNetworkType(selectedNetworkOption);

                if (seekBarSet) {
                    builder.setOverrideDeadline(seekBarInteger * 1000);
                }

                //valore per indicarmi se almeno un lavoro è settato
                boolean constrainSet=(selectedNetworkOption != JobInfo.NETWORK_TYPE_NONE)  || mDeviceChargingSwitch.isChecked() || mDeviceIdleSwitch.isChecked()   || seekBarSet;; //defaul set
                if(constrainSet){
                    //schedulo il job e notifico all'utente
                    JobInfo myJobInfo = builder.build();
                    mJobScheduler.schedule(myJobInfo);

                    Toast.makeText(MainActivity.this, "Job Scheduled, job will run when the constraints are met.", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "Please set at least one constraint " , Toast.LENGTH_SHORT).show();
                }



            }
        });
        cancelJobs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mJobScheduler !=null){
                    mJobScheduler.cancelAll();
                    mJobScheduler=null;
                }
            }
        });
        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
              //progress è il valore corrente dell seekbar,quando un valore è settatto >0 restituisce il valore
                if (progress > 0){
                    seekBarProgress.setText(progress + " s");
                }else {
                    seekBarProgress.setText("Not Set");
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }
}
package com.example.jobschedulercodingchallenge;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.app.IntentService;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int JOB_ID = 0;

    private RadioGroup networkOptions;
    private Button scheduleJob;
    private Button cancelJobs;
    private JobScheduler mJobScheduler;

    //Switches for setting job options
    private SwitchCompat mDeviceIdleSwitch;
    private SwitchCompat mDeviceChargingSwitch;

    //task1
    //Override deadline seekbar
    private SeekBar mSeekBar;

    public static final int TEXT_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        scheduleJob = findViewById(R.id.schedule);
        cancelJobs = findViewById(R.id.cancel);
        mDeviceIdleSwitch = findViewById(R.id.idleSwitch);
        mDeviceChargingSwitch = findViewById(R.id.chargingSwitch);
        networkOptions = findViewById(R.id.networkOptions);

        mSeekBar = findViewById(R.id.seekBar);
        int seekBarInteger = mSeekBar.getProgress();
        boolean seekBarSet = seekBarInteger > 0;


        final TextView seekBarProgress = findViewById(R.id.seekBarProgress);

        scheduleJob.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                int selectedNetworkId = networkOptions.getCheckedRadioButtonId();
                int selectedNetworkOption = JobInfo.NETWORK_TYPE_NONE;


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
                ComponentName service = new ComponentName(getPackageName(), MyJobService.class.getName());
                JobInfo.Builder builder = new JobInfo.Builder(JOB_ID, service)
                        .setRequiredNetworkType(selectedNetworkOption)
                        .setRequiresDeviceIdle(mDeviceIdleSwitch.isChecked())
                        .setRequiresCharging(mDeviceChargingSwitch.isChecked());

                builder.setRequiredNetworkType(selectedNetworkOption);


                //valore per indicarmi se almeno un lavoro è settato

                if (seekBarSet) {
                    builder.setOverrideDeadline(seekBarInteger * 1000);
                }
                boolean constraintSet = selectedNetworkOption
                        != JobInfo.NETWORK_TYPE_NONE
                        || mDeviceChargingSwitch.isChecked()
                        || mDeviceIdleSwitch.isChecked()
                        || seekBarSet;


                boolean constrainSet = (selectedNetworkOption != JobInfo.NETWORK_TYPE_NONE) || mDeviceChargingSwitch.isChecked() || mDeviceIdleSwitch.isChecked();//defaul set
                if (constrainSet) {
                    //schedulo il job e notifico all'utente

                    JobInfo myJobInfo = builder.build();
                    mJobScheduler.schedule(myJobInfo);

                    Toast.makeText(MainActivity.this, "Job Scheduled, job will run when the constraints are met.", Toast.LENGTH_SHORT).show();



                } else {
                    Toast.makeText(MainActivity.this, "Please set at least one constraint ", Toast.LENGTH_SHORT).show();
                }


            }
        });

        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (progress > 0) {
                    seekBarProgress.setText(progress+"s");
                } else {
                    seekBarProgress.setText("not set");
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        //cancelJobs button
        cancelJobs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mJobScheduler != null) {
                    mJobScheduler.cancelAll();
                    mJobScheduler = null;
                    Toast.makeText(MainActivity.this, "stop ", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }




}
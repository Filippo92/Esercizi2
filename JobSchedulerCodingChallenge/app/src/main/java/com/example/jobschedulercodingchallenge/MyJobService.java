package com.example.jobschedulercodingchallenge;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.ref.WeakReference;
import java.util.concurrent.Executor;

public class MyJobService extends JobService {

private TextView mTextView;


    @Override
    public boolean onStartJob(JobParameters params) {
        Task task1=new Task(mTextView);
        task1.execute();
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        return false;
    }


}

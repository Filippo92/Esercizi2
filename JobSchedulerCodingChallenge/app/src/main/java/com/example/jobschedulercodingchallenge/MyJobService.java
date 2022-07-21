package com.example.jobschedulercodingchallenge;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.ref.WeakReference;
import java.util.concurrent.Executor;

public class MyJobService extends JobService {

   private JobParameters params;



    @Override
    public boolean onStartJob(JobParameters params) {
        Task task1=new Task();
        task1.execute();
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        return false;
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
            jobFinished(params,false);

        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);

        }




    }



}

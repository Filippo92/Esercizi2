package com.example.jobschedulercodingchallenge;

import android.app.job.JobParameters;
import android.os.AsyncTask;
import android.widget.TextView;

import java.lang.ref.WeakReference;

public class Task extends AsyncTask<Void,Integer,String> {


    private WeakReference<TextView> mTextView;
    private MyJobService service;
    private JobParameters mParams;


    public Task(TextView mTextView) {
        this.mTextView = new WeakReference<>(mTextView);
    }

    @Override
    protected String doInBackground(Void... voids) {
       String result="ciao";
        return result ;
    }


    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        mTextView.get().setText(s);
       service.jobFinished(mParams,false);
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);

    }
}

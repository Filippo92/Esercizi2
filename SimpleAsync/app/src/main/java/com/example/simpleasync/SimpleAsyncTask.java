package com.example.simpleasync;

import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.Random;

public class SimpleAsyncTask extends AsyncTask<Void,Integer,String> {

    private WeakReference<TextView> mTextView;
    private WeakReference<ProgressBar> mTextUpdate;


    public SimpleAsyncTask(TextView tv,ProgressBar ud) {
        this.mTextView = new WeakReference<>(tv);
        this.mTextUpdate=new WeakReference<>(ud);

    }

    @Override
    protected String doInBackground(Void... voids) {
       Random r= new Random();
       int n = r.nextInt(11);
       int s= n*200;
       int i=0;
       while(i<s){

            try{
                Thread.sleep(s);
                publishProgress(i);
                i++;


            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
       return "Awake at last after sleeping for " + s + " milliseconds!";
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        mTextUpdate.get().setProgress(values[0]);

    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        mTextView.get().setText(s);
        mTextUpdate.get().setVisibility(View.GONE);
    }
}

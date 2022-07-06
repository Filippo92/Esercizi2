package com.example.simpleasync;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView mTextView;
    private static final String TEXT_STATE = "currentText";
    private static final String UPDATE_STATE = "currentUpdate";
    private ProgressBar mTextUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView=findViewById(R.id.textView1);
        mTextUpdate=findViewById(R.id.progressBar);
        if(savedInstanceState!=null){
            mTextView.setText(savedInstanceState.getString(TEXT_STATE));
            mTextUpdate.setProgress(savedInstanceState.getInt(UPDATE_STATE));
        }
    }

    public void startTask(View view) {
        mTextView.setText(R.string.napping);
        SimpleAsyncTask simpleAsyncTask =new SimpleAsyncTask(mTextView,mTextUpdate);

        simpleAsyncTask.execute();
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save the state of the TextView
        outState.putString(TEXT_STATE, mTextView.getText().toString());
        outState.putInt(UPDATE_STATE,mTextUpdate.getProgress());
    }
}

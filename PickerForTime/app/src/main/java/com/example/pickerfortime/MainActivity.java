package com.example.pickerfortime;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void setTimePicker(View view) {
        DialogFragment newfragmment=new TimeFragment();
        newfragmment.show(getSupportFragmentManager(),getString(R.string.timePicker));
    }

    public void processTime(int hourTime,int minutestring){
        String hour_text=Integer.toString(hourTime);
        String minute_text=Integer.toString(minutestring);
        String timeMessage=hour_text+":"+minute_text;
        Toast.makeText(this, "time:"+timeMessage, Toast.LENGTH_SHORT).show();
    }
}
package com.example.codingchallenge21;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private int setButton1;
    private int setButton2;
    private int setButton3;
    private TextView displayOne;
    private TextView displaytwo;
    private TextView displayThree;
    private TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button1 = findViewById(R.id.button1);
        Button button2 = findViewById(R.id.button2);
        Button button3 = findViewById(R.id.button3);
        displayOne = findViewById(R.id.textView1);
        displaytwo = findViewById(R.id.textView2);
        displayThree = findViewById(R.id.textView3);
        text = findViewById(R.id.textViewHeader);

    }

    public void firstStep(View view) {
        setButton1 = 1;
        displayOne.setText("Button one clicked!");

    }

    public void secondStep(View view) {
        setButton2 = 1;
        displaytwo.setText("Button two clicked!");
    }

    //inserire dentro thirdStep metodo lancio della seconda Activity, il metodo del lancio lo creo fuori

    public void thirdStep(View view) {
        setButton3 = 1;
        displayThree.setText("Button three clicked!");


    }

    public void launchSecondActivity(View view) {

        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
    }

    public void changePage(View view) {
        if (setButton1 == setButton2 && setButton1 == setButton3 && setButton1==1) {
            launchSecondActivity(view);
        }
    }
}


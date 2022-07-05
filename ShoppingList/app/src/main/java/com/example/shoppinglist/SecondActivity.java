package com.example.shoppinglist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.text.BreakIterator;

public class SecondActivity extends AppCompatActivity {

    private TextView rice;
    private TextView pasta;
    private TextView cheese;
    private TextView potatoes;
    private TextView spinaci;
    private TextView bread;
    private TextView water;
    private TextView tomatoes;
    private TextView yoghurt;
    private TextView iceCream;
    public static final String EXTRA_REPLY =
            "com.example.android.ShoppingList.extra.REPLY";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Intent intent=getIntent();
        rice=findViewById(R.id.rice);
        pasta=findViewById(R.id.pasta_id);
        cheese=findViewById(R.id.cheese_id);
        potatoes=findViewById(R.id.potatoes_id);
        spinaci=findViewById(R.id.spincaci_id);
        bread=findViewById((R.id.bread_id));
        water=findViewById(R.id.water_id);
        tomatoes=findViewById((R.id.tomatoes_id));
        yoghurt=findViewById((R.id.yogurth_id));
        iceCream=findViewById((R.id.iceCream_id));
    }

    public void addRice(View view) {
        String textElement=rice.getText().toString();
        Intent replyIntent = new Intent();
        replyIntent.putExtra(EXTRA_REPLY, textElement);
        setResult(RESULT_OK,replyIntent);
        finish();

    }


    public void addPasta(View view) {
        String textElement=pasta.getText().toString();
        Intent replyIntent = new Intent();
        replyIntent.putExtra(EXTRA_REPLY, textElement);
        setResult(RESULT_OK,replyIntent);
        finish();
    }

    public void addCheese(View view) {
        String textElement=cheese.getText().toString();
        Intent replyIntent = new Intent();
        replyIntent.putExtra(EXTRA_REPLY, textElement);
        setResult(RESULT_OK,replyIntent);
        finish();
    }

    public void addPotatoes(View view) {
        String textElement=potatoes.getText().toString();
        Intent replyIntent = new Intent();
        replyIntent.putExtra(EXTRA_REPLY, textElement);
        setResult(RESULT_OK,replyIntent);
        finish();
    }

    public void addSpinaci(View view) {

        String textElement=spinaci.getText().toString();
        Intent replyIntent = new Intent();
        replyIntent.putExtra(EXTRA_REPLY, textElement);
        setResult(RESULT_OK,replyIntent);
        finish();
    }

    public void addBread(View view) {
        String textElement=bread.getText().toString();
        Intent replyIntent = new Intent();
        replyIntent.putExtra(EXTRA_REPLY, textElement);
        setResult(RESULT_OK,replyIntent);
        finish();
    }

    public void addWater(View view) {
        String textElement=water.getText().toString();
        Intent replyIntent = new Intent();
        replyIntent.putExtra(EXTRA_REPLY, textElement);
        setResult(RESULT_OK,replyIntent);
        finish();
    }

    public void addTomatoes(View view) {
        String textElement=tomatoes.getText().toString();
        Intent replyIntent = new Intent();
        replyIntent.putExtra(EXTRA_REPLY, textElement);
        setResult(RESULT_OK,replyIntent);
        finish();
    }

    public void addYoghurt(View view) {
        String textElement=yoghurt.getText().toString();
        Intent replyIntent = new Intent();
        replyIntent.putExtra(EXTRA_REPLY, textElement);
        setResult(RESULT_OK,replyIntent);
        finish();
    }

    public void addIceCream(View view) {
        String textElement=iceCream.getText().toString();
        Intent replyIntent = new Intent();
        replyIntent.putExtra(EXTRA_REPLY, textElement);
        setResult(RESULT_OK,replyIntent);
        finish();
    }
}
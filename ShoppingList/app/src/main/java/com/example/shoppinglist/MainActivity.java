package com.example.shoppinglist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TextView elementOne;
    private TextView elementTwo;
    private TextView elementThree;
    private TextView elementFour;
    private TextView elementFive;
    private TextView elementSix;
    private TextView elementSeven;
    private TextView elementEight;
    private TextView elementNine;
    private TextView elementTen;
    private ArrayList<TextView> listElement=new ArrayList<TextView>(){};
    private EditText storePosition;
    public static final int TEXT_REQUEST = 1;
    public static final String EXTRA_REPLY =
            "com.example.android.ShoppingList.extra.REPLY";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        elementOne = findViewById(R.id.textView1);
        elementTwo = findViewById((R.id.textView2));
        elementThree = findViewById((R.id.textView3));
        elementFour = findViewById((R.id.textView4));
        elementFive = findViewById((R.id.textView5));
        elementSix = findViewById((R.id.textView6));
        elementSeven = findViewById((R.id.textView7));
        elementEight = findViewById((R.id.textView8));
        elementNine = findViewById((R.id.textView9));
        elementTen = findViewById((R.id.textView10));

        listElement.add(elementOne);
        listElement.add(elementTwo);
        listElement.add(elementThree);
        listElement.add(elementFour);
        listElement.add(elementFive);
        listElement.add(elementSix);
        listElement.add(elementSeven);
        listElement.add(elementEight);
        listElement.add(elementNine);
        listElement.add(elementTen);

        storePosition=findViewById(R.id.store_location);

        if (savedInstanceState != null) {
            boolean isVisible =
                    savedInstanceState.getBoolean("reply_visible");
            if(isVisible){
                for(int i=0;i<listElement.size();i++){
                    listElement.get(i).setText(savedInstanceState.getString("reply_text"));
                    listElement.get(i).setVisibility(View.VISIBLE);
                }
            }
        }

    }

    public void LaunchList(View view) {
        Intent intent = new Intent(this, SecondActivity.class);
        startActivityForResult(intent, TEXT_REQUEST);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == TEXT_REQUEST) {
            if (resultCode == RESULT_OK) {
                String textElement = data.getStringExtra(SecondActivity.EXTRA_REPLY);
                for(int i=0;i<listElement.size();i++){
                    if(listElement.get(i).getVisibility() != View.VISIBLE){
                        listElement.get(i).setVisibility(View.VISIBLE);
                                listElement.get(i).setText(textElement);
                                listElement.get(i).setVisibility(View.VISIBLE);
                                break;
                    }
                }
            }
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        for(int i=0;i<listElement.size();i++){
            if(listElement.get(i).getVisibility()==View.VISIBLE){
                outState.putBoolean("reply_visible",true);
                outState.putString("reply_text",listElement.get(i).getText().toString());
        }


        }
    }


    public void storePosition(View view) throws Exception {
        String address=storePosition.getText().toString();
        Uri uri=Uri.parse( "geo:0,0?q="+address);
        Intent intent=new Intent(Intent.ACTION_VIEW,uri);
        if(intent.resolveActivity(getPackageManager())==null){
            startActivity(intent);
        }else throw new Exception("this intent can't handle");

    }
}

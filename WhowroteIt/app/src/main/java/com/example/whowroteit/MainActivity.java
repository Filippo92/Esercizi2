package com.example.whowroteit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText mBookInput;
    private TextView mTitleText;
    private TextView mAuthortext;
    private Button mSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBookInput=findViewById(R.id.bookInput);
        mTitleText=findViewById(R.id.titleText);
        mAuthortext=findViewById(R.id.authorText);
        mSearch=findViewById(R.id.searchButton);
        mSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String queryString=mBookInput.getText().toString();
                InputMethodManager inputManager =(InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if(inputManager != null){
                    inputManager.hideSoftInputFromWindow(v.getWindowToken(),
                            InputMethodManager.HIDE_NOT_ALWAYS);
                }
                ConnectivityManager connMgr =(ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo=null;
                if(networkInfo == null){
                    networkInfo=connMgr.getActiveNetworkInfo();
                }
                if(networkInfo !=null && queryString.length()!=0 && networkInfo.isConnected()){
                    new FetchBook(mTitleText,mAuthortext).execute(queryString);
                    mAuthortext.setText("");
                    mTitleText.setText(R.string.loading);
                }
                else{
                    if(queryString.length()==0){
                        mTitleText.setText(R.string.not_found);
                        mAuthortext.setText("");

                    }
                    else{
                        mTitleText.setText(R.string.connect_error);
                        mAuthortext.setText("");
                    }
                }

            }
        });

    }
}
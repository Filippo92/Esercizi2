package com.example.whowroteit;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String> {

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
                    Bundle queryBundle = new Bundle();
                    queryBundle.putString("queryString", queryString);
                    getSupportLoaderManager().restartLoader(0, queryBundle, MainActivity.this);
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
        if(getSupportLoaderManager().getLoader(0)!=null){
            getSupportLoaderManager().initLoader(0,null,this);
        }

    }

    @NonNull
    @Override
    public Loader<String> onCreateLoader(int id, @Nullable Bundle args) {
       String queryString="";
       if(args !=null){
           queryString=args.getString("queryString");
       }
        return new BookLoader(this,queryString);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String data) {
        try {
            JSONObject jsonObject = new JSONObject(data);
            JSONArray itemsArray =jsonObject.getJSONArray("items");
            int i=0;
            String title=null;
            String authors=null;
            while(i< itemsArray.length() && title== null && authors==null){
                JSONObject book= itemsArray.getJSONObject(i);
                JSONObject volumeInfo=book.getJSONObject("volumeInfo");

                try{
                    title=volumeInfo.getString("title");
                    authors=volumeInfo.getString("authors");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                i++;
            }
            if(title !=null && authors != null){
                mTitleText.setText(title);
                mAuthortext.setText(authors);
            }
            else{
                mTitleText.setText(R.string.no_results);
                mAuthortext.setText("");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }


    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {

    }
}
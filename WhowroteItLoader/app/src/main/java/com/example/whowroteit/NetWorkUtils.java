package com.example.whowroteit;

import android.net.Uri;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class NetWorkUtils {

    private static final String LOG_TAG= NetWorkUtils.class.getSimpleName();
    private static final String BOOK_BASE_URL= "https://www.googleapis.com/books/v1/volumes?";
    private static final String QUERY_PARAM= "q";
    private static final String MAX_RESULTS= "maxResults";
    private static final  String PRINT_TYPE= "printType";
    private static final String FILTER_PARAM= "filter";

    static String getBookInfo(String queryString){
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String bookJSONString = null;
        try{
            //creo uri (univoco) a cui appendo i vari parametri per filtrare ricerca
            Uri builtURI = Uri.parse(BOOK_BASE_URL).buildUpon()
                .appendQueryParameter(QUERY_PARAM, queryString)
                .appendQueryParameter(MAX_RESULTS, "10")
                .appendQueryParameter(PRINT_TYPE, "books")
                    .appendQueryParameter(FILTER_PARAM,"ebooks")
                .build();
            URL requestURL = new URL(builtURI.toString());

            //get the request

            urlConnection= (HttpURLConnection) requestURL.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            //stream per passare il dao uno per volta e non scaricarlo tutto insieme
            InputStream inputStream=urlConnection.getInputStream();

            //creo il buffer per leggere il dato che verra
            reader=new BufferedReader(new InputStreamReader(inputStream));

            // contenitore dato
            StringBuilder builder= new StringBuilder();

            String line;
            while((line=reader.readLine())!= null){
                builder.append(line);
                builder.append("\n");
                if(builder.length()==0){
                    return null;
                }
                bookJSONString = builder.toString();
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        Log.d(LOG_TAG, bookJSONString);
        return  bookJSONString;
    }
}

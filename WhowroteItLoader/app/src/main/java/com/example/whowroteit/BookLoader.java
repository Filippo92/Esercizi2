package com.example.whowroteit;

import android.content.Context;
import android.net.NetworkInfo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

public class BookLoader extends AsyncTaskLoader<String> {

    private String mQueryString;


    public BookLoader(@NonNull Context context,String queryString) {
        super(context);
        this.mQueryString=queryString;
    }

    @Nullable
    @Override
    public String loadInBackground() {
        return NetWorkUtils.getBookInfo(mQueryString);
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }
}

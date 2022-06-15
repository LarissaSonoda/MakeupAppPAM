package com.example.appmakeuppam;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

public class LoadProducts extends AsyncTaskLoader<String> {

    private String mQueryString;
    LoadProducts(Context context, String queryString) {
        super(context);
        mQueryString = queryString;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }

    @Nullable
    @Override
    public String loadInBackground() {
        return NetworkUtils.searchMakeup(mQueryString);
    }
}

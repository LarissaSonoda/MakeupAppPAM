package com.example.appmakeuppam;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

public class CarregaProdutos extends AsyncTaskLoader<String> {

    private String mQueryString;
    CarregaProdutos(Context context, String queryString) {
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

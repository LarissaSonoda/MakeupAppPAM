package com.example.appmakeuppam;

import android.net.Uri;

import java.io.BufferedReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class NetworkUtils {
    private static final String LOG_TAF = NetworkUtils.class.getSimpleName();
    private static final String MAKEUP_URL = "http://makeup-api.herokuapp.com/api/v1/products.json";
    private static final String QUERY_PARAM = "brand";
    private static final String MAX_RESULTS = "maxResults";
    private static final String TIPO_IMPRESSAO = "printType";

    static String searchMakeup(String queryString){
        HttpURLConnection urlConnection = null;
        BufferedReader raader = null;
        String makeupSONString = null;
        try{
            Uri builtURI = Uri.parse(MAKEUP_URL).buildUpon()
                    .appendQueryParameter(QUERY_PARAM, queryString)
                    .appendQueryParameter(MAX_RESULTS, "10")
                    .appendQueryParameter(TIPO_IMPRESSAO, "makeup")
                    .build();
            URL requestURL = new URL(builtURI.toString());
            urlConnection = (HttpURLConnection) requestURL.openConnection();
        }
    };

}

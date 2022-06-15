package com.example.appmakeuppam;

import android.net.Uri;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class NetworkUtils {
    private static final String LOG_TAG = NetworkUtils.class.getSimpleName();
    private static final String MAKEUP_URL = "http://makeup-api.herokuapp.com/api/v1/products.json?";
    private static final String QUERY_PARAM = "brand";
    private static final String MAX_RESULTS = "maxResults";
    private static final String TYPE_PRINT = "printType";

    static String seachProduct(String queryString){
        String makeupJSONString = null;

        try{
            makeupJSONString = searchMakeup(queryString);
            if(makeupJSONString == null){
                return null;
            }

            JSONObject makeupJSONObject = new JSONObject(makeupJSONString);

            String nameProduct = null;
            String priceProduct = null;
            String descriptionProduct = null;
            String brandProduct = null;
            String typeProduct = null;

            try{
                nameProduct = makeupJSONObject.getString("name");
                priceProduct = makeupJSONObject.getString("price");
                descriptionProduct = makeupJSONObject.getString("description");
                brandProduct = makeupJSONObject.getString("brand");
                typeProduct = makeupJSONObject.getString("product_type");
            } catch (JSONException e){
                e.printStackTrace();
            }

            JSONObject productJSONObject = new JSONObject();
            try{
                productJSONObject.put("name", nameProduct);
                productJSONObject.put("price", priceProduct);
                productJSONObject.put("description", descriptionProduct);
                productJSONObject.put("brand", brandProduct);
                productJSONObject.put("product_type", typeProduct);

                makeupJSONString = makeupJSONObject.toString();
            } catch (JSONException e){
                e.printStackTrace();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d(LOG_TAG, makeupJSONString);
        return makeupJSONString;
    }

    static String searchMakeup(String queryString){
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String makeupJSONString = null;

        try{
            Uri builtURI = Uri.parse(MAKEUP_URL).buildUpon()
                    .appendQueryParameter(QUERY_PARAM, queryString).build();
            URL requestURL = new URL(builtURI.toString());
            urlConnection = (HttpURLConnection) requestURL.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            InputStream inputStream = urlConnection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder builder = new StringBuilder();

            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
                builder.append("\n");
            }

            if (builder.length() == 0) {
                return null;
            }

            makeupJSONString = builder.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
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

        Log.d(LOG_TAG, makeupJSONString);
        return makeupJSONString;
    }
}
package com.example.appmakeuppam;

import android.net.Uri;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class NetworkUtils {
    private static final String LOG_TAG = NetworkUtils.class.getSimpleName();
    private static final String MAKEUP_URL = "http://makeup-api.herokuapp.com/api/v1/products.json?";
    private static final String QUERY_PARAM = "brand";
    private static final String MAX_RESULTS = "maxResults";
    private static final String TYPE_PRINT = "printType";

    static String searchMakeup(String queryString){
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String makeupJSONString = null;
<<<<<<< HEAD
=======

>>>>>>> ce568d7a06faf1c5c49a547a72d0a5f2c45aede7
        try{
            Uri builtURI = Uri.parse(MAKEUP_URL).buildUpon()
                    .appendQueryParameter(QUERY_PARAM, queryString)
                    //.appendQueryParameter(MAX_RESULTS, "10")
                    //.appendQueryParameter(TYPE_PRINT, "makeup")
                    .build();
            URL requestURL = new URL(builtURI.toString());
            urlConnection = (HttpURLConnection) requestURL.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            // Busca o InputStream.
            InputStream inputStream = urlConnection.getInputStream();
            // Cria o buffer para o input stream
            reader = new BufferedReader(new InputStreamReader(inputStream));
            // Usa o StringBuilder para receber a resposta.
            StringBuilder builder = new StringBuilder();
<<<<<<< HEAD
            String linha;
            while ((linha = reader.readLine()) != null) {
                // Adiciona a linha a string.
                builder.append(linha);
=======
            String line;
            while ((line = reader.readLine()) != null) {
                // Adiciona a linha a string.
                builder.append(line);
>>>>>>> ce568d7a06faf1c5c49a547a72d0a5f2c45aede7
                builder.append("\n");
            }
            if (builder.length() == 0) {
                // se o stream estiver vazio não faz nada
                return null;
            }
            makeupJSONString = builder.toString();
        } catch (IOException e) {
            e.printStackTrace();
<<<<<<< HEAD
        }finally{
            if(urlConnection != null){
                urlConnection.disconnect();
            }
            if(reader != null){
                try{
                    reader.close();
                }catch (IOException e){
=======
        } finally {
            // fecha a conexão e o buffer.
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
>>>>>>> ce568d7a06faf1c5c49a547a72d0a5f2c45aede7
                    e.printStackTrace();
                }
            }
        }
<<<<<<< HEAD
        Log.d(LOG_TAF, makeupJSONString);
=======
        // escreve o Json no log
        Log.d(LOG_TAG, makeupJSONString);
>>>>>>> ce568d7a06faf1c5c49a547a72d0a5f2c45aede7
        return makeupJSONString;
    }

}

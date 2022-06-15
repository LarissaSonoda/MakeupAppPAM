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
    private static final String LOG_TAF = NetworkUtils.class.getSimpleName();
    private static final String MAKEUP_URL = "http://makeup-api.herokuapp.com/api/v1/products.json";
    private static final String QUERY_PARAM = "brand";
    private static final String MAX_RESULTS = "maxResults";
    private static final String TIPO_IMPRESSAO = "printType";

    static String searchMakeup(String queryString){
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String makeupJSONString = null;
        try{
            Uri builtURI = Uri.parse(MAKEUP_URL).buildUpon()
                    .appendQueryParameter(QUERY_PARAM, queryString)
                    .appendQueryParameter(MAX_RESULTS, "10")
                    .appendQueryParameter(TIPO_IMPRESSAO, "makeup")
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
            String linha;
            while ((linha = reader.readLine()) != null) {
                // Adiciona a linha a string.
                builder.append(linha);
                builder.append("\n");
            }
            if (builder.length() == 0) {
                // se o stream estiver vazio não faz nada
                return null;
            }
            makeupJSONString = builder.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            if(urlConnection != null){
                urlConnection.disconnect();
            }
            if(reader != null){
                try{
                    reader.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
        Log.d(LOG_TAF, makeupJSONString);
        return makeupJSONString;
    }

}

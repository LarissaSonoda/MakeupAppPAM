package com.example.appmakeuppam;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SearchProducts extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String>  {

    private EditText edtPesq;
    ListView listViewProducts;
    List<Product> listProduct;
    Product product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesquisa_cat);

        edtPesq = findViewById(R.id.edtPesq);

        listViewProducts = findViewById(R.id.listProd);

        //List<Prod> prod = listAllProd();

        if(getSupportLoaderManager().getLoader(0) != null) {
            getSupportLoaderManager().initLoader(0, null, this);
        }
    }

    public void searchProducts(View view){
        String queryString  = edtPesq.getText().toString();

        InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputManager != null) {
            inputManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }

        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = null;

        if (connMgr != null) {
            networkInfo = connMgr.getActiveNetworkInfo();
        }
        if (networkInfo != null && networkInfo.isConnected() && queryString.length() != 0) {
            Bundle queryBundle = new Bundle();
            queryBundle.putString("queryString", queryString);
            getSupportLoaderManager().restartLoader(0, queryBundle, this);
        }
        else {
            if (queryString.length() == 0) {
                edtPesq.setText(R.string.no_search_term);
            } else {
                edtPesq.setText(R.string.no_network);
            }
        }
    }

    @NonNull
    @Override
    public Loader<String> onCreateLoader(int id, @Nullable Bundle args) {
        String queryString = "";
        if (args != null) {
            queryString = args.getString("queryString");
        }
        return new LoadProducts(this, queryString);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String data) {
        try {
            JSONObject jsonObject = new JSONObject(data);
            JSONArray itemsArray = jsonObject.getJSONArray("items");

            int i = 0;
            String name = null;
            String price = null;
            String description = null;
            String brand = null;
            String product_type = null;

            while (i < itemsArray.length() && (name == null && brand == null)) {
                JSONObject makeup = itemsArray.getJSONObject(i);
                JSONObject makeupInfo = makeup.getJSONObject("makeupInfo");

                try {
                    name = makeupInfo.getString("name");
                    price = makeupInfo.getString("price");
                    description = makeupInfo.getString("description");
                    brand = makeupInfo.getString("brand");
                    product_type = makeupInfo.getString("product_type");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                i++;
            }

            if (name != null && brand != null) {
                ArrayList<String> array_list = new ArrayList<String>();

                List<String> your_array_list = new ArrayList<String>();
                your_array_list.add(name);
                your_array_list.add(brand);
                your_array_list.add(price);
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                        this,
                        R.layout.listview_item,
                        your_array_list );

                ListView listView = findViewById(R.id.listProd);
                listView.setAdapter(arrayAdapter);
            } else {

            }
        } catch (Exception e) {
            // Se não receber um JSOn valido, informa ao usuário
            //.setText(R.string.no_results);
            edtPesq.setText(R.string.str_empty);
            e.printStackTrace();
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {

    }
}
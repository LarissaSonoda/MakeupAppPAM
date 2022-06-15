package com.example.appmakeuppam;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SearchProducts extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String>  {

    private EditText edtPesq;
    TextView  txtName, txtDescription;
    ListView listViewProducts;
    TextView nmProduct, nmBrand;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesquisa_cat);

        edtPesq = findViewById(R.id.edtPesq);
        txtName = findViewById(R.id.txtName);
        txtDescription = findViewById(R.id.txtDescription);

        //nmProduct = findViewById(R.id.NameProduct);
        //nmBrand = findViewById(R.id.brand);
        //listViewProducts = findViewById(R.id.listProd);

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
            Toast.makeText(getApplicationContext(), "Procurando pelo produto...", Toast.LENGTH_SHORT).show();
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


        String name = null;
        String price = null;
        String description = null;
        String brand = null;
        String product_type = null;

        try {
            JSONArray jsonArray = new JSONArray(data);
            String stringArray = jsonArray.toString();
            String[] arrayProduct = stringArray.split(",");

            JSONObject jsonObject = new JSONObject();

            jsonObject.put("name", arrayProduct[2]);
            jsonObject.put("price", arrayProduct[3]);
            jsonObject.put("description", arrayProduct[13]);
            jsonObject.put("brand", arrayProduct[1]);
            jsonObject.put("product_type", arrayProduct[19]);

            try {
                name = jsonObject.getString("name");
                price = jsonObject.getString("price");
                description = jsonObject.getString("description");
                brand = jsonObject.getString("brand");
                product_type = jsonObject.getString("product_type");

                txtName.setText(name);
                txtDescription.setText(description);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            if(name != null && price != null && description != null && brand != null && product_type != null){
                getSupportLoaderManager().destroyLoader(0);
            }

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Não encontramos seu produto.", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {

    }

}
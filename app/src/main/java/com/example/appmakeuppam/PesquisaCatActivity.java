package com.example.appmakeuppam;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PesquisaCatActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String>  {

    private EditText edtPesq;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesquisa_cat);
        edtPesq = findViewById(R.id.edtPesq);
        // List<Prod> prod = listAllProd();
        ListView listView = findViewById(R.id.ListProd);
        if(getSupportLoaderManager().getLoader(0) != null) {
            getSupportLoaderManager().initLoader(0, null, this);
        }
    }
    public void searchProducts(View view){
        String queryString  = edtPesq.getText().toString();

        // esconde o teclado qdo o botão é clicado
        InputMethodManager inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputManager != null) {
            inputManager.hideSoftInputFromWindow(view.getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }

        //Verificar a conexão com a internet
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = null;
        if (connMgr != null) {
            networkInfo = connMgr.getActiveNetworkInfo();
        }
        if (networkInfo != null && networkInfo.isConnected()
                && queryString.length() != 0) {
            Bundle queryBundle = new Bundle();
            queryBundle.putString("queryString", queryString);
            //getSupportLoaderManager().restartLoader(0, queryBundle, this);
            //Fazer lista de produtos: 06/06
        }
        // atualiza a textview para informar que não há conexão ou termo de busca
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
        return new CarregaProdutos(this, queryString);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String data) {
        try {
            // Converte a resposta em Json
            JSONObject jsonObject = new JSONObject(data);
            // Obtem o JSONArray dos produtos
            JSONArray itemsArray = jsonObject.getJSONArray("items");
            // inicializa o contador
            int i = 0;
            String name = null;
            String price = null;
            String desc = null;
            String brand = null;
            String product_type = null;
            // Procura resultados nos itens do array
            while (i < itemsArray.length() &&
                    (name == null && brand == null)) {
                // Obtem a informação
                JSONObject makeup = itemsArray.getJSONObject(i);
                JSONObject makeupInfo = makeup.getJSONObject("makeup");
                //  Obter dados do item
                // erro se o campo estiver vazio
                try {
                    name = makeupInfo.getString("info");
                    price = makeupInfo.getString("price");
                    desc = makeupInfo.getString("description");
                    brand = makeupInfo.getString("brand");
                    product_type = makeupInfo.getString("category");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                // move para a proxima linha
                i++;
            }
            //mostra o resultado qdo possivel.
            if (name != null && brand != null) {
                ArrayList<String> array_list = new ArrayList<String>();

                List<String> your_array_list = new ArrayList<String>();
                your_array_list.add(name);
                your_array_list.add(brand);
                your_array_list.add(price);
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                        this,
                        android.R.layout.simple_list_item_1,
                        your_array_list );

                ListView listView = findViewById(R.id.ListProd);
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
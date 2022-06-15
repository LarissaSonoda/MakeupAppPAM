package com.example.appmakeuppam;

<<<<<<< HEAD
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
import android.widget.EditText;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class Menu extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String> {
    private EditText edtPesq;
=======
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
>>>>>>> ce568d7a06faf1c5c49a547a72d0a5f2c45aede7

public class Menu extends SearchProducts {

    ImageButton itemMenu_home, itemMenu_search, itemMenu_user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
<<<<<<< HEAD
        setContentView(R.layout.activity_menu);
        edtPesq = findViewById(R.id.edtPesq);
        List<Prod> prod = listAllProd();
        ListView listView = findViewById(R.id.ListProd);
        if(getSupportLoaderManager().getLoader(0) != null){
            getSupportLoaderManager().initLoader(0, null, this);
        }
    }

    public void searchProducts (View view){
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
        return new CarregaProdutos(PesquisaCatActivity, queryString);
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
            // Procura pro resultados nos itens do array
            while (i < itemsArray.length() &&
                    (name == null && brand == null)) {
                // Obtem a informação
                JSONObject nome = itemsArray.getJSONObject(i);
                JSONObject dados = makeup.getJSONObject("makeup");
                //  Obter dados do item
                // erro se o campo estiver vazio
                try {
                    name = makeup.getString("info");
                    price = makeup.getString("price");
                    desc = makeup.getString("description");
                    brand = makeup.getString("brand");
                    product_type = makeup.getString("category");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                // move para a proxima linha
                i++;
            }
            //mostra o resultado qdo possivel.
            if (name != null && brand != null) {

                .setText(titulo);
                nmAutor.setText(autor);
                //nmLivro.setText(R.string.str_empty);
            } else {
                // If none are found, update the UI to show failed results.
                nmTitulo.setText(R.string.no_results);
                nmAutor.setText(R.string.str_empty);
            }
        } catch (Exception e) {
            // Se não receber um JSOn valido, informa ao usuário
            .setText(R.string.no_results);
            nmAutor.setText(R.string.str_empty);
            e.printStackTrace();
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {

=======
        setContentView(R.layout.bottom_menu);

        itemMenu_home = findViewById(R.id.itemMenu_home);
        itemMenu_search = findViewById(R.id.itemMenu_search);
        itemMenu_user = findViewById(R.id.itemMenu_user);

        itemMenu_home.setOnClickListener(v -> {
            startActivity(new Intent(getBaseContext(), HomeActivity.class));
            finish();
        });

        itemMenu_search.setOnClickListener(v -> {
            startActivity(new Intent(getBaseContext(), SearchProducts.class));
            finish();
        });

        itemMenu_user.setOnClickListener(v -> {
            startActivity(new Intent(getBaseContext(), UserActivity.class));
            finish();
        });
>>>>>>> ce568d7a06faf1c5c49a547a72d0a5f2c45aede7
    }
}
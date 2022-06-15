package com.example.appmakeuppam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PesquisaCatActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesquisa_cat);

        List<Prod> prod = listAllProd();
        ListView listView = findViewById(R.id.ListProd);

        //métodos da lista de produtos
        ArrayAdapter<Prod> adapter = new ArrayAdapter<Prod>(this, android.R.layout.simple_list_item_1, prod);

        listView.setAdapter(adapter);
    }

    public class Cat {

        private String nome = ;
        private int cod;


        @Override public String toString() {
            return nome;



        }

        //listar todas as categorias
        private List<Cat> listAllCat() {
            return new ArrayList<>(Arrays.asList(
                        new Cat(nome);


//métodos

    }
}
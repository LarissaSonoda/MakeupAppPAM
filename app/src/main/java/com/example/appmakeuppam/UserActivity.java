package com.example.appmakeuppam;

<<<<<<< HEAD:app/src/main/java/com/example/appmakeuppam/PesquisaCatActivity.java
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

=======
>>>>>>> ce568d7a06faf1c5c49a547a72d0a5f2c45aede7:app/src/main/java/com/example/appmakeuppam/UserActivity.java
import android.os.Bundle;
import android.view.View;
<<<<<<< HEAD
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;
=======
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
>>>>>>> 6f0e2ef65211f66d501d24ba355ec600d8b0e8f0

import androidx.appcompat.app.AppCompatActivity;

import java.io.FileOutputStream;

public class UserActivity extends AppCompatActivity {
    RatingBar ratingBar;
    Button btnSave;
    int rating;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
<<<<<<< HEAD:app/src/main/java/com/example/appmakeuppam/PesquisaCatActivity.java
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

=======
        setContentView(R.layout.activity_user);
<<<<<<< HEAD
        addListenerOnButtonClick();
    }

    public void addListenerOnButtonClick(){
        btnSave = findViewById(R.id.btnSave);
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);

        btnSave.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View arg0) {
                //Getting the rating and displaying it on the toast
                 rating = Math.round(ratingBar.getRating());
                //String rating=String.valueOf();
                //Toast.makeText(getApplicationContext(), rating, Toast.LENGTH_LONG).show();
            }

        });
=======
>>>>>>> ce568d7a06faf1c5c49a547a72d0a5f2c45aede7:app/src/main/java/com/example/appmakeuppam/UserActivity.java
>>>>>>> 6f0e2ef65211f66d501d24ba355ec600d8b0e8f0
    }
}




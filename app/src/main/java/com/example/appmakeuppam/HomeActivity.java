package com.example.appmakeuppam;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity  {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void search (View v){
        Intent intent = new Intent(getApplicationContext(), PesquisaCatActivity.class);
        startActivity(intent);
    }
}

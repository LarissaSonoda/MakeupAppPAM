package com.example.appmakeuppam;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void openSearch (View v){
        Intent intent = new Intent(getApplicationContext(), SearchProducts.class);
        startActivity(intent);
    }
    public void openUser (View v){
        Intent intent = new Intent(getApplicationContext(), UserActivity.class);
        startActivity(intent);
    }
}

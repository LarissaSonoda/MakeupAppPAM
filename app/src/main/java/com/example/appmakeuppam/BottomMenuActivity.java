package com.example.appmakeuppam;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class BottomMenuActivity extends AppCompatActivity {

    ImageButton itemMenu_home, itemMenu_search, itemMenu_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bottom_menu);

        itemMenu_home = findViewById(R.id.itemMenu_home);
        itemMenu_search = findViewById(R.id.itemMenu_search);
        itemMenu_user = findViewById(R.id.itemMenu_user);

        itemMenu_home.setOnClickListener(v -> {
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
        });

        itemMenu_search.setOnClickListener(v -> {
            Intent intent = new Intent(this, SearchProducts.class);
            startActivity(intent);
        });

        itemMenu_user.setOnClickListener(v -> {
            Intent intent = new Intent(this, UserActivity.class);
            startActivity(intent);
        });
    }
}

package com.example.appmakeuppam;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

public class Menu extends SearchProducts {

    ImageButton itemMenu_home, itemMenu_search, itemMenu_user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
    }
}
package com.example.appmakeuppam;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.FileOutputStream;

public class UserActivity extends AppCompatActivity {
    RatingBar ratingBar;
    Button btnSave;
    int rating;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
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
    }
}




package com.example.hotelheritage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button rms = findViewById(R.id.rmBtn);
        Button bd = findViewById(R.id.BdBtn);
        Button lg = findViewById(R.id.LgBtn);

        rms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent lga = new Intent(getApplicationContext(), CheckOutActivity.class);
                startActivity(lga);
            }
        });

        bd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent lga1 = new Intent(getApplicationContext(), EditProfileActivity.class);
                startActivity(lga1);
            }
        });

        lg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent lga2 = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(lga2);
            }
        });

    }
}


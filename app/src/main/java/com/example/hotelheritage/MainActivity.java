package com.example.hotelheritage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn1 = (Button) findViewById(R.id.btn);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent int5 = new Intent(getApplicationContext(), ReservationsHomeActivity.class);
                startActivity(int5);
            }
        });

        Button btn2 = (Button) findViewById(R.id.btnsignin);

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent int5 = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(int5);
            }
        });

        Button btn3 = (Button) findViewById(R.id.btnsignup);

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent int5 = new Intent(getApplicationContext(), RegistrationActivity.class);
                startActivity(int5);
            }
        });

    }
}


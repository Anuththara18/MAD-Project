package com.example.hotelheritage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Room1Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room1);

        Button bookNowBtn = findViewById(R.id.bookNowBtn);

        bookNowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent lga = new Intent(getApplicationContext(), CheckOutActivity.class);
                startActivity(lga);
            }
        });

    }
}
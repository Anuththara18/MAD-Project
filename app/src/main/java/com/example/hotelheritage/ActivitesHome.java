package com.example.hotelheritage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ActivitesHome extends AppCompatActivity {
    public Button button;
    private Button button_spa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activites_home);

        button = (Button) findViewById(R.id.gym);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivitesHome.this, GymSection.class);
                intent.putExtra("PATH", "Gym");
                startActivity(intent);
                finish();
            }
        });


        button_spa = (Button) findViewById(R.id.spa);
        button_spa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivitesHome.this, GymSection.class);
                intent.putExtra("PATH", "Spa");
                startActivity(intent);
                finish();
            }
        });


    }
}
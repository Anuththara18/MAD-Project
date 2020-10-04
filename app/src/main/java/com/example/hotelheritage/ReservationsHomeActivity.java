package com.example.hotelheritage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ReservationsHomeActivity extends AppCompatActivity {

    DatabaseReference dbRef;
    Users useraccounts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservations_home);

        Button btn2 = (Button) findViewById(R.id.rooms);
        Button btn3 = (Button) findViewById(R.id.booknow);
        Button btn4 = (Button) findViewById(R.id.bookings);
        final Button btn5 = (Button) findViewById(R.id.profile);

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent int1 = new Intent(getApplicationContext(), RoomsSelectionActivity.class);
                startActivity(int1);
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent int2 = new Intent(getApplicationContext(), CheckOutActivity.class);
                startActivity(int2);
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent int3 = new Intent(getApplicationContext(), MyBookingsActivity.class);
                startActivity(int3);
            }
        });

        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent int4 = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(int4);
            }
        });

    }
}
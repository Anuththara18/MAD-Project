package com.example.hotelheritage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MyBookingsActivity extends AppCompatActivity {

    DatabaseReference dbRef;
    TextView rName, inDate, outDate, inTime, totPrice;
    Button rescheduleBtn, cancelBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_bookings);

        rName = (TextView) findViewById(R.id.rName);
        inDate = (TextView) findViewById(R.id.inDate);
        outDate = (TextView) findViewById(R.id.outDate);
        inTime = (TextView) findViewById(R.id.inTime);
        totPrice = (TextView) findViewById(R.id.totPrice);
        rescheduleBtn = (Button) findViewById(R.id.rescheduleBtn);
        cancelBtn = (Button)findViewById(R.id.cancelBtn);

        rescheduleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent int2 = new Intent(getApplicationContext(), UpdateBookingActivity.class);
                startActivity(int2);
            }
        });


        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog();
            }
        });

        dbRef = FirebaseDatabase.getInstance().getReference().child("Bookings/1");
        dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChildren()) {
                    rName.setText(dataSnapshot.child("roomType").getValue().toString());
                    inDate.setText("Check In Date : " + dataSnapshot.child("checkInDate").getValue().toString());
                    outDate.setText("Check Out Date : " + dataSnapshot.child("checkOutDate").getValue().toString());
                    inTime.setText("Check In Time : " + dataSnapshot.child("checkInTime").getValue().toString());
                    totPrice.setText("Amount : " + dataSnapshot.child("totalAmount").getValue().toString());

                }
                else {
                    Toast.makeText(getApplicationContext(), "Cannot Find Booking", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }


    private void alertDialog() {
        AlertDialog.Builder dialog=new AlertDialog.Builder(this);
        dialog.setMessage("Are You sure you want to cancel?");
        dialog.setTitle("Cancel Booking");
        dialog.setPositiveButton("No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int which) {
                        Toast.makeText(getApplicationContext(),"Booking Not Cancelled",Toast.LENGTH_LONG).show();
                    }
                });
        dialog.setNegativeButton("Yes",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dbRef = FirebaseDatabase.getInstance().getReference().child("Bookings").child("1");
                dbRef.removeValue();
                Toast.makeText(getApplicationContext(), "Booking Cancelled Successfully", Toast.LENGTH_SHORT).show();
            }
        });
        AlertDialog alertDialog=dialog.create();
        alertDialog.show();
    }

}
package com.example.hotelheritage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

import java.util.ArrayList;
import java.util.List;

public class BookingDetailsActivity extends AppCompatActivity {

    TextView CheckInDate, CheckOutDate, roomType, noOfAdults, noOfChildren, CheckInTime, price, TotalAmount;
    Float n1, n2;
    Button btn2;
    long maxid = 0;

    //a list to store all the bookings in firebase database
    List<RoomsReservation> bookings;

    DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_details);

        // getting the reference of bookings node
        dbRef = FirebaseDatabase.getInstance().getReference().child("Bookings");

        //list to store artists
        bookings = new ArrayList<>();

        roomType = (TextView)findViewById(R.id.roomType);
        CheckInDate = (TextView)findViewById(R.id.CheckInDate);
        CheckOutDate = (TextView)findViewById(R.id.CheckOutDate);
        noOfAdults = (TextView)findViewById(R.id.noOfAdults);
        noOfChildren = (TextView)findViewById(R.id.noOfChildren);
        CheckInTime = (TextView)findViewById(R.id.CheckInTime);
        price = (TextView)findViewById(R.id.price);
        TotalAmount = (TextView)findViewById(R.id.TotalAmount);
        btn2 = (Button) findViewById(R.id.confirmbooking);

        Intent intent = getIntent();

        String adults = intent.getStringExtra("n4");
        String children = intent.getStringExtra("n5");

        roomType.setText(intent.getStringExtra("n1"));
        CheckInDate.setText(intent.getStringExtra("n2"));
        CheckOutDate.setText(intent.getStringExtra("n3"));
        noOfAdults.setText(intent.getStringExtra("n4"));
        noOfChildren.setText(intent.getStringExtra("n5"));
        CheckInTime.setText(intent.getStringExtra("n6"));

        n1 = Float.parseFloat(adults);
        n2 = Float.parseFloat(children);

        if(roomType.getText().toString().equals("Deluxe Ocean View")) {
            price.setText("LKR 11,000 / Night");
            TotalAmount.setText("LKR " + ((n1*11000) + (n2*5500)) );
        }

        else if(roomType.getText().toString().equals("Family Suite with Sea View")) {
            price.setText("LKR 12,000 / Night");
            TotalAmount.setText("LKR " + ((n1*12000) + (n2*6000)) );
        }

        else if(roomType.getText().toString().equals("Junior Suite Superior")) {
            price.setText("LKR 13,000 / Night");
            TotalAmount.setText("LKR " + ((n1*13000) + (n2*6500)) );
        }

        else if(roomType.getText().toString().equals("Executive Suite")) {
            price.setText("LKR 14,000 / Night");
            TotalAmount.setText("LKR " + ((n1*14000) + (n2*7000)) );
        }

        else if(roomType.getText().toString().equals("Heritage Suite")) {
            price.setText("LKR 15,000 / Night");
            TotalAmount.setText("LKR " + ((n1*15000) + (n2*7500)) );
        }


        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                    maxid = (dataSnapshot.getChildrenCount());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        // adding an onclicklistener to button
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveBookings();
                //Intent int5 = new Intent(getApplicationContext(), MyBookingsActivity.class);
                //startActivity(int5);
            }
        });
    }

    private void saveBookings() {

        RoomsReservation bookings = new RoomsReservation();

        //getting a unique id
        //we will use it as the Primary Key for our booking
        String id = String.valueOf(maxid+1);

        bookings.setBookingID(id);
        bookings.setRoomType(roomType.getText().toString().trim());
        bookings.setPrice(price.getText().toString().trim());
        bookings.setNoOfAdults(Integer.parseInt(noOfAdults.getText().toString().trim()));
        bookings.setNoOfChildren(Integer.parseInt(noOfChildren.getText().toString().trim()));
        bookings.setCheckInDate(CheckInDate.getText().toString().trim());
        bookings.setCheckOutDate(CheckOutDate.getText().toString().trim());
        bookings.setCheckInTime(CheckInTime.getText().toString().trim());
        bookings.setTotalAmount(TotalAmount.getText().toString().trim());

        //Saving the booking
        dbRef.child(id).setValue(bookings);

        //displaying a success toast
        Toast.makeText(getApplicationContext(), "Booking Successful", Toast.LENGTH_SHORT).show();

    }

}
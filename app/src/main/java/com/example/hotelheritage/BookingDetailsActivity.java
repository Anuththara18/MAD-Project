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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BookingDetailsActivity extends AppCompatActivity {

    TextView CheckInDate, CheckOutDate, roomType, noOfAdults, noOfChildren, CheckInTime, price, TotalAmount;
    Float n1, n2;
    Button btn2;
    long maxid = 0;
    Float roomCharge;
    Float noOfDays;

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

        SimpleDateFormat dates = new SimpleDateFormat("dd / MM / yyyy");
        String inDate = intent.getStringExtra("n2");
        String outDate = intent.getStringExtra("n3");

        try {

            Date date1, date2;
            date1 = dates.parse(inDate);
            date2 = dates.parse(outDate);

            noOfDays = calcDateDifference(date1, date2);

            if(roomType.getText().toString().equals("Deluxe Ocean View")) {
                price.setText("LKR 11,000 / Night");
                roomCharge = 11000f;
                TotalAmount.setText("LKR " +  calcTotalAmount(roomCharge, n1, n2, noOfDays) );
            }

            else if(roomType.getText().toString().equals("Family Suite with Sea View")) {
                price.setText("LKR 12,000 / Night");
                roomCharge = 12000f;
                TotalAmount.setText("LKR " + calcTotalAmount(roomCharge, n1, n2, noOfDays) );
            }

            else if(roomType.getText().toString().equals("Junior Suite Superior")) {
                price.setText("LKR 13,000 / Night");
                roomCharge = 13000f;
                TotalAmount.setText("LKR " + calcTotalAmount(roomCharge, n1, n2, noOfDays) );
            }

            else if(roomType.getText().toString().equals("Executive Suite")) {
                price.setText("LKR 14,000 / Night");
                roomCharge = 14000f;
                TotalAmount.setText("LKR " + calcTotalAmount(roomCharge, n1, n2, noOfDays) );
            }

            else if(roomType.getText().toString().equals("Heritage Suite")) {
                price.setText("LKR 15,000 / Night");
                roomCharge = 15000f;
                TotalAmount.setText("LKR " + calcTotalAmount(roomCharge, n1, n2, noOfDays) );
            }

        }
        catch (Exception exception) {
            Toast.makeText(this, "Unable to get Total Amount", Toast.LENGTH_SHORT).show();
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
        Intent int5 = new Intent(getApplicationContext(), MyBookingsActivity.class);
        startActivity(int5);

    }

    public float calcDateDifference(Date checkInDate, Date checkOutDate) {
        long difference = checkOutDate.getTime() - checkInDate.getTime();
        float daysBetween = (difference / ( 1000 * 60 * 60 * 24 ));
        return daysBetween;
    }

    public float calcTotalAmount(Float roomCharge, Float adults, Float children, Float noOfDays) {
        return ( ( (adults*roomCharge) + (children*roomCharge/2) ) * noOfDays );
    }


}
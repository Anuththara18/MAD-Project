package com.example.hotelheritage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UpdateBookingDetailsActivity extends AppCompatActivity {

    TextView CheckInDate, CheckOutDate, roomType, noOfAdults, noOfChildren, CheckInTime, price, TotalAmount;
    Float n1, n2;
    Button btn2;

    DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_booking_details);

        roomType = (TextView)findViewById(R.id.roomType);
        CheckInDate = (TextView)findViewById(R.id.CheckInDate);
        CheckOutDate = (TextView)findViewById(R.id.CheckOutDate);
        noOfAdults = (TextView)findViewById(R.id.noOfAdults);
        noOfChildren = (TextView)findViewById(R.id.noOfChildren);
        CheckInTime = (TextView)findViewById(R.id.CheckInTime);
        price = (TextView)findViewById(R.id.price);
        TotalAmount = (TextView)findViewById(R.id.TotalAmount);
        btn2 = (Button) findViewById(R.id.confirmreschedulebooking);

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

        // adding an onclicklistener to button
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbRef = FirebaseDatabase.getInstance().getReference();
                dbRef.child("Bookings/1/roomType").setValue(roomType.getText().toString().trim());
                dbRef.child("Bookings/1/price").setValue(price.getText().toString().trim());
                dbRef.child("Bookings/1/noOfAdults").setValue(noOfAdults.getText().toString().trim());
                dbRef.child("Bookings/1/noOfChildren").setValue(noOfChildren.getText().toString().trim());
                dbRef.child("Bookings/1/checkInDate").setValue(CheckInDate.getText().toString().trim());
                dbRef.child("Bookings/1/checkOutDate").setValue(CheckOutDate.getText().toString().trim());
                dbRef.child("Bookings/1/checkInTime").setValue(CheckInTime.getText().toString().trim());
                dbRef.child("Bookings/1/totalAmount").setValue(TotalAmount.getText().toString().trim());
                Toast.makeText(getApplicationContext(), "Updated Successfully", Toast.LENGTH_SHORT).show();
                Intent int5 = new Intent(getApplicationContext(), MyBookingsActivity.class);
                startActivity(int5);
            }
        });

    }



}
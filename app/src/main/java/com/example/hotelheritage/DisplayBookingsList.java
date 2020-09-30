package com.example.hotelheritage;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DisplayBookingsList extends AppCompatActivity {

    DatabaseReference dbRef;
    ListView bookingsListView;
    List<RoomsReservation> bookingList;
    ArrayAdapter<RoomsReservation> adapter;
    RoomsReservation roomsReservation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookings_list);

        roomsReservation = new RoomsReservation();

        dbRef = FirebaseDatabase.getInstance().getReference("Bookings");

        bookingsListView = (ListView)findViewById(R.id.bookingList);

        bookingList = new ArrayList<>();
        adapter = new ArrayAdapter<RoomsReservation>(this, R.layout.activity_my_bookings, R.id.rName, bookingList);

        dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                bookingList.clear();

                for(DataSnapshot bookingsSnapshot : dataSnapshot.getChildren()) {
                    RoomsReservation bookings = bookingsSnapshot.getValue(RoomsReservation.class);
                    bookingList.add(bookings);
                }

                BookingsList adapter = new BookingsList(DisplayBookingsList.this, bookingList);
                bookingsListView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }


}

package com.example.hotelheritage;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class UpdateBookingActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    DatePickerDialog checkindatepicker;
    DatePickerDialog checkoutdatepicker;
    EditText checkinDate;
    EditText checkoutDate;
    Button checkInBtn;
    Button checkOutBtn;
    String item1, item2, item3, item4;
    DatabaseReference dbRef;
    Spinner roomTypeSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_booking);

        checkinDate=(EditText)findViewById(R.id.checkinDate);
        checkInBtn=(Button)findViewById(R.id.check_in_btn);

        checkoutDate=(EditText)findViewById(R.id.checkoutDate);
        checkOutBtn=(Button)findViewById(R.id.check_out_btn);

        dbRef = FirebaseDatabase.getInstance().getReference().child("Bookings/3");
        dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChildren()) {
                    checkinDate.setText(dataSnapshot.child("checkInDate").getValue().toString());
                    checkoutDate.setText(dataSnapshot.child("checkOutDate").getValue().toString());
                }
                else {
                    Toast.makeText(getApplicationContext(), "Cannot Find Booking", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        checkInBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);

                // date picker dialog
                checkindatepicker = new DatePickerDialog(UpdateBookingActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                checkinDate.setText(dayOfMonth + " / " + (monthOfYear + 1) + " / " + year);
                            }
                        }, year, month, day);
                checkindatepicker.getDatePicker().setMinDate(System.currentTimeMillis());
                checkindatepicker.show();
            }
        });

        checkOutBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);

                // date picker dialog
                checkoutdatepicker = new DatePickerDialog(UpdateBookingActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                checkoutDate.setText(dayOfMonth + " / " + (monthOfYear + 1) + " / " + year);
                            }
                        }, year, month, day);
                checkoutdatepicker.getDatePicker().setMinDate(System.currentTimeMillis());
                checkoutdatepicker.show();
            }
        });


        // Spinner element
        roomTypeSpinner = (Spinner) findViewById(R.id.roomTypeSpinner);
        Spinner adultsSpinner = (Spinner) findViewById(R.id.adultsSpinner);
        Spinner childrenSpinner = (Spinner) findViewById(R.id.childrenSpinner);
        Spinner checkInTimeSpinner = (Spinner) findViewById(R.id.checkInTime);


        // Spinner click listener
        roomTypeSpinner.setOnItemSelectedListener(this);
        adultsSpinner.setOnItemSelectedListener(this);
        childrenSpinner.setOnItemSelectedListener(this);
        checkInTimeSpinner.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        List<String> roomsCategories = new ArrayList<String>();
        roomsCategories.add("Deluxe Ocean View");
        roomsCategories.add("Family Suite with Sea View");
        roomsCategories.add("Junior Suite Superior");
        roomsCategories.add("Executive Suite");
        roomsCategories.add("Heritage Suite");

        // Spinner Drop down elements
        List<String> adultsCategories = new ArrayList<String>();
        adultsCategories.add("1");
        adultsCategories.add("2");
        adultsCategories.add("3");
        adultsCategories.add("4");
        adultsCategories.add("5");
        adultsCategories.add("6");
        adultsCategories.add("7");
        adultsCategories.add("8");
        adultsCategories.add("9");
        adultsCategories.add("10");

        List<String> childrenCategories = new ArrayList<String>();
        childrenCategories.add("0");
        childrenCategories.add("1");
        childrenCategories.add("2");
        childrenCategories.add("3");
        childrenCategories.add("4");
        childrenCategories.add("5");
        childrenCategories.add("6");
        childrenCategories.add("7");
        childrenCategories.add("8");
        childrenCategories.add("9");
        childrenCategories.add("10");

        List<String> checkInTimeCategories = new ArrayList<String>();
        checkInTimeCategories.add("10:00 a.m.");
        checkInTimeCategories.add("02:00 p.m.");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapterRooms = new ArrayAdapter<String>(this, R.layout.spinner_item, roomsCategories);
        ArrayAdapter<String> dataAdapterAdults = new ArrayAdapter<String>(this, R.layout.spinner_item, adultsCategories);
        ArrayAdapter<String> dataAdapterChildren = new ArrayAdapter<String>(this, R.layout.spinner_item, childrenCategories);
        ArrayAdapter<String> dataAdapterTime = new ArrayAdapter<String>(this, R.layout.spinner_item, checkInTimeCategories);

        // Drop down layout style - list view with radio button
        dataAdapterRooms.setDropDownViewResource(R.layout.spinner_dropdown_item);
        dataAdapterAdults.setDropDownViewResource(R.layout.spinner_dropdown_item);
        dataAdapterChildren.setDropDownViewResource(R.layout.spinner_dropdown_item);
        dataAdapterTime.setDropDownViewResource(R.layout.spinner_dropdown_item);

        // attaching data adapter to spinner
        roomTypeSpinner.setAdapter(dataAdapterRooms);
        adultsSpinner.setAdapter(dataAdapterAdults);
        childrenSpinner.setAdapter(dataAdapterChildren);
        checkInTimeSpinner.setAdapter(dataAdapterTime);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item

        switch(parent.getId()){
            case R.id.roomTypeSpinner:
                item1 = parent.getItemAtPosition(position).toString();
                break;
            case R.id.adultsSpinner:
                item2 = parent.getItemAtPosition(position).toString();
                break;
            case R.id.childrenSpinner:
                item3 = parent.getItemAtPosition(position).toString();
                break;
            case R.id.checkInTime:
                item4 = parent.getItemAtPosition(position).toString();
                break;

        }

        // Showing selected spinner item
        //Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_SHORT).show();
    }
    public void onNothingSelected(AdapterView<?> arg0) {

    }

    public void sendDetails( View view ) {

        if (TextUtils.isEmpty(checkinDate.getText().toString()))
            Toast.makeText(getApplicationContext(), "Select Check-In Date", Toast.LENGTH_SHORT).show();

        else if (TextUtils.isEmpty(checkoutDate.getText().toString()))
            Toast.makeText(getApplicationContext(), "Select Check-Out Date", Toast.LENGTH_SHORT).show();

        else {
            Intent intent = new Intent(this, UpdateBookingDetailsActivity.class);
            intent.putExtra("n1", item1);
            intent.putExtra("n2", checkinDate.getText().toString());
            intent.putExtra("n3", checkoutDate.getText().toString());
            intent.putExtra("n4", item2);
            intent.putExtra("n5", item3);
            intent.putExtra("n6", item4);
            startActivity(intent);
        }

    }

}
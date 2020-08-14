package com.example.hotelheritage;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CheckOutActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    DatePickerDialog checkindatepicker;
    DatePickerDialog checkoutdatepicker;
    EditText checkinDate;
    EditText checkoutDate;
    Button checkInBtn;
    Button checkOutBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out);

        checkinDate=(EditText)findViewById(R.id.checkinDate);
        checkInBtn=(Button)findViewById(R.id.check_in_btn);

        checkoutDate=(EditText)findViewById(R.id.checkoutDate);
        checkOutBtn=(Button)findViewById(R.id.check_out_btn);

        checkInBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);

                // date picker dialog
                checkindatepicker = new DatePickerDialog(CheckOutActivity.this,
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
                checkoutdatepicker = new DatePickerDialog(CheckOutActivity.this,
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
        Spinner adultsSpinner = (Spinner) findViewById(R.id.adultsSpinner);
        Spinner childrenSpinner = (Spinner) findViewById(R.id.childrenSpinner);

        // Spinner click listener
        adultsSpinner.setOnItemSelectedListener(this);
        childrenSpinner.setOnItemSelectedListener(this);

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

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapterAdults = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, adultsCategories);
        ArrayAdapter<String> dataAdapterChildren = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, childrenCategories);

        // Drop down layout style - list view with radio button
        dataAdapterAdults.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dataAdapterChildren.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        adultsSpinner.setAdapter(dataAdapterAdults);
        childrenSpinner.setAdapter(dataAdapterChildren);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_SHORT).show();
    }
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }

}
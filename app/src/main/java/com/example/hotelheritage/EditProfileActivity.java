package com.example.hotelheritage;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;

public class EditProfileActivity extends AppCompatActivity {

    DatePickerDialog dobpicker;
    EditText dob;
    Button selectDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        dob=(EditText)findViewById(R.id.dob);
        selectDate=(Button)findViewById(R.id.selectDate);

        dob.setInputType(InputType.TYPE_NULL);

        selectDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);

                // date picker dialog
                dobpicker = new DatePickerDialog(EditProfileActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                dob.setText(dayOfMonth + " / " + (monthOfYear + 1) + " / " + year);
                            }
                        }, year, month, day);
                dobpicker.getDatePicker().setMinDate(System.currentTimeMillis());
                dobpicker.show();
            }
        });

    }

}
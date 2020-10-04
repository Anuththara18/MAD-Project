package com.example.hotelheritage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;
import android.app.DatePickerDialog;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegistrationActivity extends AppCompatActivity implements View.OnClickListener{

    EditText name, phone, dob, email, password, username;
    Button signUp, signIn;

    private DatePickerDialog DatePickerDialog;
    private SimpleDateFormat dateFormatter;

    Users useraccounts;

    DatabaseReference dbRef;
    long maxid = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        useraccounts = new Users();

        name = (EditText)findViewById(R.id.name);
        phone = (EditText)findViewById(R.id.phone);
        dob = (EditText)findViewById(R.id.dob);
        email = (EditText)findViewById(R.id.email);
        username = (EditText)findViewById(R.id.username);
        password = (EditText)findViewById(R.id.password);
        signUp = (Button) findViewById(R.id.register);
        signIn = (Button) findViewById(R.id.signIn);

        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);

        findViewsById();

        setDateTimeField();

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isValid = true;
                String MobilePattern = "[0-9]{10}";
                if (TextUtils.isEmpty(name.getText().toString()))
                    Toast.makeText(getApplicationContext(), "Enter Your Name", Toast.LENGTH_SHORT).show();
                else if (TextUtils.isEmpty(phone.getText().toString()))
                    Toast.makeText(getApplicationContext(), "Enter Your Contact Number", Toast.LENGTH_SHORT).show();
                else if (TextUtils.isEmpty(dob.getText().toString()))
                    Toast.makeText(getApplicationContext(), "Enter Your DOB", Toast.LENGTH_SHORT).show();
                else if (TextUtils.isEmpty(email.getText().toString()))
                    Toast.makeText(getApplicationContext(), "Enter Your Email", Toast.LENGTH_SHORT).show();
                else if (TextUtils.isEmpty(username.getText().toString()))
                    Toast.makeText(getApplicationContext(), "Enter a Username", Toast.LENGTH_SHORT).show();
                else if (TextUtils.isEmpty(password.getText().toString()))
                    Toast.makeText(getApplicationContext(), "Enter a Password", Toast.LENGTH_SHORT).show();
                else if(!phone.getText().toString().matches(MobilePattern)) {
                    Toast.makeText(getApplicationContext(), "Please enter valid 10 digit phone number", Toast.LENGTH_SHORT).show();
                }
                else if (!isEmail(email)) {
                    email.setError("Enter valid email!");
                    isValid = false;
                }
                else if ( password.getText().toString().length() < 4 ) {
                    password.setError("Password must be at least 4 characters long!");
                }
                else {
                    dbRef = FirebaseDatabase.getInstance().getReference().child("Users");

                    useraccounts.setName(name.getText().toString().trim());
                    useraccounts.setConNo(phone.getText().toString().trim());
                    useraccounts.setDob(dob.getText().toString().trim());
                    useraccounts.setEmail(email.getText().toString().trim());
                    useraccounts.setUsername(username.getText().toString().trim());
                    useraccounts.setPassword(password.getText().toString().trim());

                    //getting a unique id
                    //we will use it as the Primary Key for our booking
                    //String id = dbRef.push().getKey();
                    String id = String.valueOf(maxid+1);

                    //Saving the booking
                    dbRef.child(id).setValue(useraccounts);

                    //displaying a success toast
                    Toast.makeText(getApplicationContext(), "Successfully Registered", Toast.LENGTH_SHORT).show();
                }

            }
        });

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent forgotPassword = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(forgotPassword);
            }
        });
    }

    private void findViewsById() {
        signIn = findViewById(R.id.signIn);
        dob = (EditText) findViewById(R.id.dob);
        dob.setInputType(InputType.TYPE_NULL);
        dob.requestFocus();
    }

    private void setDateTimeField() {
        dob.setOnClickListener((View.OnClickListener) this);

        Calendar newCalendar = Calendar.getInstance();
        DatePickerDialog = new DatePickerDialog(this, new android.app.DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                dob.setText(dateFormatter.format(newDate.getTime()));
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

    }

    @Override
    public void onClick(View view) {
        DatePickerDialog.show();
    }

    boolean isEmail(EditText text) {
        CharSequence email = text.getText().toString();
        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }

}

package com.example.hotelheritage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RegistrationActivity extends AppCompatActivity {

    EditText name, phone, dob, email, password, username;
    Button signUp, signIn;

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

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dbRef = FirebaseDatabase.getInstance().getReference().child("Users");

                useraccounts.setName(name.getText().toString().trim());
                //useraccounts.setConNo(Integer.parseInt(phone.getText().toString().trim()));
                useraccounts.setDob(dob.getText().toString().trim());
                useraccounts.setEmail(email.getText().toString().trim());
                useraccounts.setUsername(username.getText().toString().trim());
                useraccounts.setPassword(password.getText().toString().trim());

                //getting a unique id
                //we will use it as the Primary Key for our booking
                String id = dbRef.push().getKey();

                //Saving the booking
                dbRef.child(id).setValue(useraccounts);

                //displaying a success toast
                Toast.makeText(getApplicationContext(), "Successfully Registered", Toast.LENGTH_SHORT).show();

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

}

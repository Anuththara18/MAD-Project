package com.example.hotelheritage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    EditText username, password;
    Button signIn, signUp, forgotPassword;
    String Username, Password, uname, pwd;

    DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = (EditText)findViewById(R.id.lusername);
        password = (EditText)findViewById(R.id.lpassword);
        signIn = (Button)findViewById(R.id.lsignIn);
        signUp = (Button)findViewById(R.id.register);
        forgotPassword = (Button)findViewById(R.id.forgotPassword);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signUp = new Intent(getApplicationContext(), RegistrationActivity.class);
                startActivity(signUp);
            }
        });

        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent forgotPassword = new Intent(getApplicationContext(), ForgotPasswordActivity.class);
                startActivity(forgotPassword);
            }
        });


        signIn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                uname = username.getText().toString();
                pwd = password.getText().toString();

                dbRef = FirebaseDatabase.getInstance().getReference().child("Users/2");
                dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.hasChildren()) {
                            String name = dataSnapshot.child("name").getValue().toString();
                            String phone = dataSnapshot.child("conNo").getValue().toString();
                            String dob = dataSnapshot.child("dob").getValue().toString();
                            String email = dataSnapshot.child("email").getValue().toString();
                            Username = dataSnapshot.child("username").getValue().toString();
                            Password = dataSnapshot.child("password").getValue().toString();

                            Intent int4 = new Intent(getApplicationContext(), MyProfileActivity.class);
                            int4.putExtra("n1", name);
                            int4.putExtra("n2", phone);
                            int4.putExtra("n3", dob);
                            int4.putExtra("n4", email);
                            int4.putExtra("n5", Username);

                            if ( uname.equals(Username) ) {
                                if ( pwd.equals(Password)) {
                                    startActivity(int4);
                                }
                                else {
                                    Toast.makeText(getApplicationContext(), "Incorrect Password", Toast.LENGTH_SHORT).show();
                                }
                            }

                            else {
                                Toast.makeText(getApplicationContext(), "Invalid Username", Toast.LENGTH_SHORT).show();
                            }

                        }
                        else {
                            Toast.makeText(getApplicationContext(), "User Account Not Available", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });


            }
        });

    }

}
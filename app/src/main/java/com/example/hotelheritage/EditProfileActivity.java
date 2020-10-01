package com.example.hotelheritage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class EditProfileActivity extends AppCompatActivity {

    DatabaseReference dbRef;
    EditText epName, epPhone, epDob, epEmail, epUsername;
    Button saveChanges, deleteprofile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        epName = (EditText)findViewById(R.id.epName);
        epPhone = (EditText)findViewById(R.id.epPhone);
        epDob = (EditText)findViewById(R.id.epDob);
        epEmail = (EditText)findViewById(R.id.epEmail);
        epUsername = (EditText)findViewById(R.id.epUsername);
        saveChanges = (Button)findViewById(R.id.saveChanges);
        deleteprofile = (Button)findViewById(R.id.deleteprofile);

        dbRef = FirebaseDatabase.getInstance().getReference().child("Users/2");
        dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChildren()) {
                    epName.setText(dataSnapshot.child("name").getValue().toString());
                    epPhone.setText(dataSnapshot.child("conNo").getValue().toString());
                    epDob.setText(dataSnapshot.child("dob").getValue().toString());
                    epEmail.setText(dataSnapshot.child("email").getValue().toString());
                    epUsername.setText(dataSnapshot.child("username").getValue().toString());

                }
                else {
                    Toast.makeText(getApplicationContext(), "Cannot Find User", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        saveChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbRef = FirebaseDatabase.getInstance().getReference();
                dbRef.child("Users").child("2").child("name").setValue(epName.getText().toString().trim());
                dbRef.child("Users/2/conNo").setValue(epPhone.getText().toString().trim());
                dbRef.child("Users/2/dob").setValue(epDob.getText().toString().trim());
                dbRef.child("Users/2/email").setValue(epEmail.getText().toString().trim());
                dbRef.child("Users/2/username").setValue(epUsername.getText().toString().trim());
                Toast.makeText(getApplicationContext(), "Updated Successfully", Toast.LENGTH_SHORT).show();
                //Intent int1 = new Intent(getApplicationContext(), MyProfileActivity.class);
                //startActivity(int1);
            }
        });

        deleteprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog();
            }
        });

    }

    private void alertDialog() {
        AlertDialog.Builder dialog=new AlertDialog.Builder(this);
        dialog.setMessage("Are You sure you want to delete your profile?");
        dialog.setTitle("Delete Profile");
        dialog.setPositiveButton("No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int which) {
                        Toast.makeText(getApplicationContext(),"Profile Not Deleted",Toast.LENGTH_LONG).show();
                    }
                });
        dialog.setNegativeButton("Yes",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dbRef = FirebaseDatabase.getInstance().getReference().child("Users").child("2");
                dbRef.removeValue();
                Toast.makeText(getApplicationContext(), "Profile Deleted Successfully", Toast.LENGTH_SHORT).show();
                Intent int2 = new Intent(getApplicationContext(), ReservationsHomeActivity.class);
                startActivity(int2);
            }
        });
        AlertDialog alertDialog=dialog.create();
        alertDialog.show();
    }

}
package com.example.hotelheritage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MyProfileActivity extends AppCompatActivity {

    TextView pName, pPhone, pDob, pEmail, pUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);

        pName = (TextView)findViewById(R.id.pName);
        pPhone = (TextView)findViewById(R.id.pPhone);
        pDob = (TextView)findViewById(R.id.pDob);
        pEmail = (TextView)findViewById(R.id.pEmail);
        pUsername = (TextView)findViewById(R.id.pUsername);
        Button btn3 = (Button) findViewById(R.id.editprofile);

        Intent intent = getIntent();
        pName.setText(intent.getStringExtra("n1"));
        pPhone.setText(intent.getStringExtra("n2"));
        pDob.setText(intent.getStringExtra("n3"));
        pEmail.setText(intent.getStringExtra("n4"));
        pUsername.setText(intent.getStringExtra("n5"));

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent int5 = new Intent(getApplicationContext(), EditProfileActivity.class);
                startActivity(int5);
            }
        });


    }
}
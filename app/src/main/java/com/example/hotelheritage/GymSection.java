package com.example.hotelheritage;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hotelheritage.Adapters.CountAdapter;
import com.example.hotelheritage.Models.Gym;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class GymSection extends AppCompatActivity {
    public Button button;
    private CalendarView calendarView2;
    private DatabaseReference mDatabase;
    private FirebaseUser currentFirebaseUser;
    private FirebaseAuth mAuth;
    private String TAG = "LOGIN";
    private FirebaseUser currentUser;
    private String Name = "Dilip";
    private String Time = "";
    private String TimeID = "";
    private ArrayList<String> strings;
    private ArrayList<String> stringsID;
    private Spinner spiner;
    private Button button3;
    private String curDate = "";
    private ListView dataList;
    private boolean DONT_SAVE = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gym_section);


        strings = new ArrayList<>();

        strings.add("8AM TO 9AM");
        strings.add("9AM TO 10AM");
        strings.add("10AM TO 11AM");
        strings.add("11AM TO 12AM");
        strings.add("12PM TO 1PM");
        strings.add("1AM TO 2PM");


        stringsID = new ArrayList<>();

        stringsID.add("8");
        stringsID.add("9");
        stringsID.add("10");
        stringsID.add("11");
        stringsID.add("12");
        stringsID.add("1");


        dataList = findViewById(R.id.data_list);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();


        spiner = findViewById(R.id.spinner);
        spiner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Time = strings.get(i);
                TimeID = stringsID.get(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();


        button3 = findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (Time.equals("") || TimeID.equals("")) {
                    Toast.makeText(GymSection.this, "Select time", Toast.LENGTH_SHORT).show();
                } else {

                    if (curDate.equals("")) {
                        Toast.makeText(GymSection.this, "Select a date", Toast.LENGTH_SHORT).show();
                    } else {
                        SaveReservation(curDate);
                    }
                }
            }
        });

        calendarView2 = findViewById(R.id.calendarView2);
        calendarView2.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int day) {


                Calendar c = Calendar.getInstance();
                c.set(year, month, day);


                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                String selectedDate = sdf.format(new Date(c.getTimeInMillis()));

                curDate = selectedDate;


                Toast.makeText(getApplicationContext(), curDate, Toast.LENGTH_LONG).show();

                GetDataByDate(selectedDate);
            }
        });

        InitData();

      //  GetData();


    }

    private void InitData() {

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(GymSection.this, android.R.layout.simple_spinner_dropdown_item, strings);
        spiner.setAdapter(adapter);
    }

    @Override
    public void onStart() {
        super.onStart();
        currentUser = mAuth.getCurrentUser();


        if (currentUser.isEmailVerified()) {
            createAccount("Dilip.wijethunga@gmail.com", "123456");
        }

    }

    private void SaveReservation(String selectedDate) {


        Gym gym = new Gym(currentUser.getUid(), Name, Time, selectedDate, TimeID);

        long time = new Date().getTime();

        DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference cities = databaseRef.child(getIntent().getStringExtra("PATH"));
        Query citiesQuery = cities.orderByChild("UserID").equalTo(currentUser.getUid());

        citiesQuery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    String TimeIDD = postSnapshot.child("Time").getValue(String.class);
                    String Date = postSnapshot.child("Date").getValue(String.class);
                    if (Date.equals(selectedDate)) {
                        if (Time.equals(TimeIDD)) {
                            System.out.println(Date + "  " + TimeIDD + "    selectedDate  " + selectedDate + "  " + Time);
                            DONT_SAVE = false;
                            break;
                        } else {
                            DONT_SAVE = true;
                        }


//                        if (Time.equals(TimeIDD)) {
//                            DONT_SAVE = false;
//                            break;
//                        } else {
//                            DONT_SAVE = true;
//                        }
                    }
                }


                if (DONT_SAVE) {
                    mDatabase.child(getIntent().getStringExtra("PATH")).child(time + "").setValue(gym).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(GymSection.this, "Reservation Added successfully", Toast.LENGTH_SHORT).show();
                            GetDataByDate(curDate);
                        }
                    });
                    DONT_SAVE = true;
                } else {
                    DONT_SAVE = true;
                    Toast.makeText(GymSection.this, "Can not add a reservation for same time", Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }


    private void createAccount(String email, String password) {
        Log.d(TAG, "createAccount:" + email);
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "createUserWithEmail:success");
                            currentUser = mAuth.getCurrentUser();
                        } else {
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                        }
                    }
                });
    }

    public void GetData() {
        DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference();

        String input = "G";

        DatabaseReference cities = databaseRef.child(getIntent().getStringExtra("PATH"));
        cities.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<String> cities = new ArrayList<String>();

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    cities.add(postSnapshot.getValue().toString());
                }
                System.out.println(cities);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    public void GetDataByDate(String date) {
        DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference();

        String input = "G";

        DatabaseReference cities = databaseRef.child(getIntent().getStringExtra("PATH"));
        Query citiesQuery = cities.orderByChild("Date").equalTo(date);
        citiesQuery.addValueEventListener(new ValueEventListener() {
            private int count = 0;

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<HashMap<String, String>> cities = new ArrayList<>();


                List<String> strings22 = new ArrayList<>();

                String TimeIDD = "";
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                    TimeIDD = postSnapshot.child("Time").getValue(String.class);
                    strings22.add(TimeIDD);

                }

                for (int i = 0; i < strings.size(); i++) {
                    count = 0;
                    for (int ii = 0; ii < strings22.size(); ii++) {
                        if (strings22.get(ii).equals(strings.get(i))) {
                            count++;
                        }
                    }
                    HashMap<String, String> stringStringHashMap = new HashMap<>();
                    stringStringHashMap.put("A", strings.get(i));
                    stringStringHashMap.put("B", count + "");
                    cities.add(stringStringHashMap);
                }


                System.out.println(cities);

                CountAdapter countAdapter = new CountAdapter(GymSection.this, R.layout.lastbilllay, cities);
                dataList.setAdapter(countAdapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

}
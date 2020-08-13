package com.example.hotelheritage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;

public class RoomsSelectionActivity extends AppCompatActivity {

    List<RoomsList> roomsList;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rooms_selection);

        roomsList = new ArrayList<>();
        listView = (ListView) findViewById(R.id.roomsList);

        roomsList.add(new RoomsList(R.drawable.room1a, "Deluxe Ocean View", R.drawable.star_ratings_4,"LKR 11,000 / Night"));
        roomsList.add(new RoomsList(R.drawable.room2a, "Family Suite with Sea View", R.drawable.star_ratings_3,"LKR 12,000 / Night"));
        roomsList.add(new RoomsList(R.drawable.room3a, "Junior Suite Superior", R.drawable.star_ratings_2,"LKR 13,000 / Night"));
        roomsList.add(new RoomsList(R.drawable.room4a, "Executive Suite", R.drawable.star_ratings_1,"LKR 14,000 / Night"));
        roomsList.add(new RoomsList(R.drawable.room5a, "Heritage Suite", R.drawable.star_ratings_1,"LKR 15,000 / Night"));

        RoomListAdapter adapter = new RoomListAdapter(this, R.layout.room_list, roomsList);

        listView.setAdapter(adapter);

    }
}
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

        roomsList.add(new RoomsList(R.drawable.room1a, "Room1", "LKR 11,000"));
        roomsList.add(new RoomsList(R.drawable.room2a, "Room2", "LKR 12,000"));
        roomsList.add(new RoomsList(R.drawable.room3a, "Room3", "LKR 13,000"));
        roomsList.add(new RoomsList(R.drawable.room4a, "Room4", "LKR 14,000"));
        roomsList.add(new RoomsList(R.drawable.room5a, "Room5", "LKR 15,000"));

        RoomListAdapter adapter = new RoomListAdapter(this, R.layout.room_list, roomsList);

        listView.setAdapter(adapter);

    }
}
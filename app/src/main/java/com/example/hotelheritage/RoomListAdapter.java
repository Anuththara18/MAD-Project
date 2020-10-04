package com.example.hotelheritage;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.List;


public class RoomListAdapter extends ArrayAdapter<RoomsList> {

    Context mCtx;
    int resource;
    List<RoomsList> roomList;
    ListView listView;

    public RoomListAdapter (Context mCtx, int resource, List<RoomsList> roomsList) {
        super(mCtx, resource, roomsList);
        this.mCtx = mCtx;
        this.resource = resource;
        this.roomList = roomsList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(resource, null);
        TextView textViewName = view.findViewById(R.id.roomName);
        TextView textViewPrice = view.findViewById(R.id.roomPrice);
        ImageView imageView = view.findViewById(R.id.roomImage);
        ImageView imageView2 = view.findViewById(R.id.ratings);

        RoomsList roomsList = roomList.get(position);

        textViewName.setText(roomsList.getName());
        textViewPrice.setText(roomsList.getPrice());
        imageView.setImageDrawable(mCtx.getResources().getDrawable(roomsList.getImage1()));
        imageView2.setImageDrawable(mCtx.getResources().getDrawable(roomsList.getImage2()));




        return view;
    }

}

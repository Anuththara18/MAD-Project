package com.example.hotelheritage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.List;


public class RoomListAdapter extends ArrayAdapter<RoomsList> {

    Context mCtx;
    int resource;
    List<RoomsList> roomList;

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

        RoomsList roomsList = roomList.get(position);

        textViewName.setText(roomsList.getName());
        textViewPrice.setText(roomsList.getPrice());
        imageView.setImageDrawable(mCtx.getResources().getDrawable(roomsList.getImage()));

        view.findViewById(R.id.detailsBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        view.findViewById(R.id.bookNowBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return view;
    }

}

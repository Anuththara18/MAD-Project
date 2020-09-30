package com.example.hotelheritage;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ArrayAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;


public class BookingsList extends ArrayAdapter<RoomsReservation> {

    private Activity context;
    List<RoomsReservation> bookings;

    public BookingsList(Activity context, List<RoomsReservation> bookings) {
        super(context, R.layout.activity_bookings_list, bookings);
        this.context = context;
        this.bookings = bookings;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();
        View view = inflater.inflate(R.layout.activity_bookings_list, null, true);

        TextView rName = (TextView) view.findViewById(R.id.rName);
        TextView outDate = (TextView) view.findViewById(R.id.outDate);
        TextView inDate = (TextView) view.findViewById(R.id.inDate);
        TextView inTime = (TextView) view.findViewById(R.id.inTime);
        TextView totPrice = (TextView) view.findViewById(R.id.totPrice);

        RoomsReservation booking = bookings.get(position);
        rName.setText(booking.getRoomType());
        outDate.setText(booking.getCheckOutDate());
        inDate.setText(booking.getCheckInDate());
        inTime.setText(booking.getCheckInTime());
        totPrice.setText(booking.getTotalAmount());

        return view;
    }

}

package com.example.hotelheritage.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.hotelheritage.R;

import java.util.HashMap;
import java.util.List;

class ViewHolderlast {
    TextView COL1;
    TextView COL2;

}

public class CountAdapter extends ArrayAdapter<HashMap<String, String>> {
    LayoutInflater inflater;
    Context myContext;
    List<HashMap<String, String>> newListt;
    private String unitname;

    public CountAdapter(Context context, int resource, List<HashMap<String, String>> list) {
        super(context, resource, list);
        // TODO Auto-generated constructor stub
        myContext = context;
        newListt = list;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        final ViewHolderlast holder;
        if (view == null) {
            holder = new ViewHolderlast();
            view = inflater.inflate(R.layout.lastbilllay, null);
            holder.COL1 = (TextView) view.findViewById(R.id.textView3);
            holder.COL2 = (TextView) view.findViewById(R.id.textView2);
            view.setTag(holder);
        } else {
            holder = (ViewHolderlast) view.getTag();
        }

            holder.COL1.setText(newListt.get(position).get("A"));
            holder.COL2.setText(newListt.get(position).get("B"));

        return view;
    }
}

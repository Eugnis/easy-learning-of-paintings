package com.eugnis.easylearningofpaintings.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.eugnis.easylearningofpaintings.R;
import com.eugnis.easylearningofpaintings.data.model.Style;

import java.util.List;

/**
 * Created by Eugnis on 17.12.2016.
 */
public class StylesListAdapter extends ArrayAdapter<Style> {

    public StylesListAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    public StylesListAdapter(Context context, int resource, List<Style> items) {
        super(context, resource, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.itemlistrow, null);
        }

        Style p = getItem(position);

        if (p != null) {
            TextView tt1 = (TextView) v.findViewById(R.id.painterList_name);
            TextView tt2 = (TextView) v.findViewById(R.id.painterList_years);
            // TextView tt3 = (TextView) v.findViewById(R.id.description);

            if (tt1 != null) {
                tt1.setText(p.getName());
            }

            if (tt2 != null) {
                // tt2.setText(p.getAbout());
            }
/*
            if (tt3 != null) {
                tt3.setText(p.getDescription());
            }*/
        }

        return v;
    }

}
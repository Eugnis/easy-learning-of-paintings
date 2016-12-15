package com.eugnis.easylearningofpaintings;


import android.content.ClipData;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.eugnis.easylearningofpaintings.data.model.Painter;
import com.eugnis.easylearningofpaintings.data.model.Painting;
import com.eugnis.easylearningofpaintings.data.model.Style;
import com.eugnis.easylearningofpaintings.data.repo.PaintersRepo;
import com.eugnis.easylearningofpaintings.data.repo.StylesRepo;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class OptionsChooseFragment extends Fragment implements AdapterView.OnItemClickListener {

    ListView optionsList;
    boolean stylesMode;


    public OptionsChooseFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        String strtext=getArguments().getString("type");
        stylesMode = !(strtext != null && strtext.equals("painters"));

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_options_choose, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (!stylesMode)
        {
            PaintersRepo paintersRepo = new PaintersRepo();
            List<Painter> paintersList= paintersRepo.getPainters();

            optionsList = (ListView) getView().findViewById(R.id.optionsList);
            PaintersListAdapter adapter = new PaintersListAdapter(getContext(), R.layout.itemlistrow, paintersList);
            optionsList.setAdapter(adapter);
            optionsList.setOnItemClickListener(this);
        }
        else {
            StylesRepo stylesRepo = new StylesRepo();
            List<Style> stylesList= stylesRepo.getStyles();

            optionsList = (ListView) getView().findViewById(R.id.optionsList);
            StylesListAdapter adapter = new StylesListAdapter(getContext(), R.layout.itemlistrow, stylesList);
            optionsList.setAdapter(adapter);
            optionsList.setOnItemClickListener(this);
        }


    }

    @Override
    public void onItemClick(AdapterView parent, View view, int position, long id) {
        if (stylesMode)
        {
            Style s = (Style)parent.getItemAtPosition(position);
            Toast.makeText(getContext(), "hello"+s.getName(), Toast.LENGTH_SHORT).show();
        }
        else
        {
            Painter p = (Painter)parent.getItemAtPosition(position);
            Toast.makeText(getContext(), "hello"+p.getCountry(), Toast.LENGTH_SHORT).show();
        }

    }
}

class PaintersListAdapter extends ArrayAdapter<Painter>  {

    public PaintersListAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    public PaintersListAdapter(Context context, int resource, List<Painter> items) {
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

        Painter p = getItem(position);

        if (p != null) {
            TextView tt1 = (TextView) v.findViewById(R.id.painterList_name);
            TextView tt2 = (TextView) v.findViewById(R.id.painterList_years);
           // TextView tt3 = (TextView) v.findViewById(R.id.description);

            if (tt1 != null) {
                tt1.setText(p.getName());
            }

            if (tt2 != null) {
                tt2.setText(p.getYears());
            }
/*
            if (tt3 != null) {
                tt3.setText(p.getDescription());
            }*/
        }

        return v;
    }

}

class StylesListAdapter extends ArrayAdapter<Style>  {

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



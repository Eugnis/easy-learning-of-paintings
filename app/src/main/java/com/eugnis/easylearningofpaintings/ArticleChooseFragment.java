package com.eugnis.easylearningofpaintings;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.eugnis.easylearningofpaintings.data.model.Painting;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ArticleChooseFragment extends Fragment {


    public ArticleChooseFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_article_choose, container, false);
    }

}

class PaintingsListAdapter extends ArrayAdapter<Painting> {

    public PaintingsListAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    public PaintingsListAdapter(Context context, int resource, List<Painting> items) {
        super(context, resource, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.paintings_catalogrow, null);
        }

        Painting p = getItem(position);

        if (p != null) {
            ImageView im1 = (ImageView) v.findViewById(R.id.pictureView);
            TextView tt2 = (TextView) v.findViewById(R.id.pictureDescription);

            // TextView tt3 = (TextView) v.findViewById(R.id.description);

            if (im1 != null) {
                im1.setImageBitmap(p.getPicture());
            }

            if (tt2 != null) {
                tt2.setText(p.getName());
            }
/*
            if (tt3 != null) {
                tt3.setText(p.getDescription());
            }*/
        }

        return v;
    }

}

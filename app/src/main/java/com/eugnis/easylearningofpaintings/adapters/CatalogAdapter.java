package com.eugnis.easylearningofpaintings.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.eugnis.easylearningofpaintings.R;
import com.eugnis.easylearningofpaintings.data.model.Painter;
import com.eugnis.easylearningofpaintings.data.model.Painting;

import java.util.List;

/**
 * Created by Eugnis on 17.12.2016.
 */

public class CatalogAdapter extends ArrayAdapter<Painting> {

    public CatalogAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    public CatalogAdapter(Context context, int resource, List<Painting> items) {
        super(context, resource, items);
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.catalogitem, null);
        }

        //ImageView imageView;
        Painting p = getItem(position);
        if (p != null) {

            TextView tt1 = (TextView) v.findViewById(R.id.textView_catalogImageName);
            TextView tt2 = (TextView) v.findViewById(R.id.textView_catalogImageAuthor);
            ImageView img = (ImageView) v.findViewById(R.id.imageView_catalog);
            //img.setImageBitmap(null);

            Glide
                    .with(v.getContext())
                    .load(p.getPictureLink())
                    .centerCrop()
                    .override(250, 250)
                    .placeholder(R.drawable.progress_animation)
                    .crossFade()
                    .into(img);


            //img.setImageBitmap(null);
            //ImageGridHandler handler = new ImageGridHandler(getContext(), img);
            //handler.execute(p.getPictureLink(), "250", "250");
            tt1.setText("\"" + p.getName()+"\"");
            tt2.setText(p.getPainter().getName());

        }

        return v;
    }



}

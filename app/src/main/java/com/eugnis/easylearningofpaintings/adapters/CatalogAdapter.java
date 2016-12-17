package com.eugnis.easylearningofpaintings.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;

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
        ImageView imageView;
        Painting p = getItem(position);
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            imageView = new ImageView(getContext());
            imageView.setLayoutParams(new GridView.LayoutParams(250, 250));
            imageView.setScaleType(ImageView.ScaleType.CENTER);
            //imageView.setPadding(8, 8, 8, 8);
        } else {
            imageView = (ImageView) convertView;
        }

        if (p != null) {
            imageView.setImageBitmap(p.getPicture());
        }
        return imageView;
    }



}

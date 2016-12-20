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

import com.eugnis.easylearningofpaintings.R;
import com.eugnis.easylearningofpaintings.data.model.Painter;
import com.eugnis.easylearningofpaintings.data.model.Painting;
import com.eugnis.easylearningofpaintings.helpers.ImageGridHandler;

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

        ImageView imageView;
        Painting p = getItem(position);
        if (p != null) {

            TextView tt1 = (TextView) v.findViewById(R.id.textView_catalogDescription);
            ImageView img = (ImageView) v.findViewById(R.id.imageView_catalog);

            //img.setImageBitmap(p.getPicture());
            //ImageView image = (ImageView)view.findViewById(R.id.img_item);
            img.setImageBitmap(null);
            ImageGridHandler handler = new ImageGridHandler(getContext(), img);
            handler.execute(p.getPictureLink(), "250", "250");
            tt1.setText("\"" + p.getName()+"\"\n" + p.getPainter().getName());

            //imageView = new ImageView(getContext());
            //imageView.setLayoutParams(new GridView.LayoutParams(250, 250));
            //imageView.setScaleType(ImageView.ScaleType.CENTER);
            //imageView.setPadding(8, 8, 8, 8);
        }

        return v;
    }



}

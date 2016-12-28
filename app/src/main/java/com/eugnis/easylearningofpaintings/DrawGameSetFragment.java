package com.eugnis.easylearningofpaintings;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.eugnis.easylearningofpaintings.data.model.Painting;


public class DrawGameSetFragment extends Fragment {

    View v;
    TextView paintingToDrawName;
    Painting paintingToDraw;


    public DrawGameSetFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_draw_game_set, container, false);
        paintingToDrawName = (TextView) v.findViewById(R.id.paintingToDrawName);

        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        paintingToDraw = ((DrawGameActivity)this.getActivity()).getPaintingToDraw();
        paintingToDrawName.setText("\"" + paintingToDraw.getName() + "\"\n" + paintingToDraw.getPainter().getName());
    }

}

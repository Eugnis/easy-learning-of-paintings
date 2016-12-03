package com.eugnis.easylearningofpaintings;


import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainMenuFragment extends Fragment {

    ViewGroup mContainer;
    public MainMenuFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main_menu,
                container, false);


        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //RelativeLayout layout =(RelativeLayout)v.findViewById(R.id.relative_layout_menu);
        //layout.setBackgroundResource(R.drawable.background);

        long startMs = System.currentTimeMillis();

        Log.d(getString(R.string.app_name),
                "TIME " + String.valueOf(System.currentTimeMillis() - startMs) + "ms");
        //layout.setBackgroundResource(((ImageView) v.findViewById(R.id.bg_menu)));



    }




}

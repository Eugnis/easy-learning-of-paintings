package com.eugnis.easylearningofpaintings;


import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.eugnis.easylearningofpaintings.custom.OnSwipeTouchListener;
import com.eugnis.easylearningofpaintings.data.model.Painting;


/**
 * A simple {@link Fragment} subclass.
 */
public class DrawCompareFragment extends Fragment {

    View v;
    ImageView originalPictureView;
    ImageView drawedPictureView;
    Button saveToGallery;

    Painting painting;
    Bitmap drawed;

    TabHost tabHost;

    private float x1,x2;
    static final int MIN_DISTANCE = 150;

    public DrawCompareFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_draw_compare, container, false);
        tabHost = (TabHost) v.findViewById(android.R.id.tabhost);

        originalPictureView = (ImageView) v.findViewById(R.id.originalPictureView);
        drawedPictureView = (ImageView) v.findViewById(R.id.drawedPictureView);
        saveToGallery = (Button) v.findViewById(R.id.saveToGallery);

        v.setOnTouchListener(new OnSwipeTouchListener(v.getContext()) {
            public void onSwipeRight() {
                //Toast.makeText(v.getContext(), "right", Toast.LENGTH_SHORT).show();
                tabHost.setCurrentTab(0);
            }
            public void onSwipeLeft() {
                //Toast.makeText(v.getContext(), "left", Toast.LENGTH_SHORT).show();
                tabHost.setCurrentTab(1);
            }
        });

        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        painting = ((DrawGameActivity)this.getActivity()).getPaintingToDraw();
        drawed = ((DrawGameActivity)this.getActivity()).getDrawedImage();

        tabHost.setup();
        TabHost.TabSpec tabSpec;

        tabSpec = tabHost.newTabSpec("tag1");
        tabSpec.setIndicator(painting.getName());
        tabSpec.setContent(R.id.originalPictureView);
        tabHost.addTab(tabSpec);

        tabSpec = tabHost.newTabSpec("tag2");
        tabSpec.setIndicator("Ваш малюнок");
        tabSpec.setContent(R.id.drawedLinearLayout);
        tabHost.addTab(tabSpec);

        Glide.with(this).load(painting.getPictureLink()).crossFade().into(originalPictureView);
        //BitmapDrawable ob = new BitmapDrawable(getResources(), drawed);

        drawedPictureView.setImageBitmap(drawed);
        drawedPictureView.setBackgroundColor(Color.WHITE);
    }


    public void switchTabs(boolean direction) {
        if (direction) // true = move left
        {
            if (tabHost.getCurrentTab() == 0)
                tabHost.setCurrentTab(tabHost.getTabWidget().getTabCount() - 1);
            else
                tabHost.setCurrentTab(tabHost.getCurrentTab() - 1);
        } else
        // move right
        {
            if (tabHost.getCurrentTab() != (tabHost.getTabWidget()
                    .getTabCount() - 1))
                tabHost.setCurrentTab(tabHost.getCurrentTab() + 1);
            else
                tabHost.setCurrentTab(0);
        }
    }

    /*@Override
    public boolean onTouch(View v, MotionEvent event) {
        switch(event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                x1 = event.getX();
                break;
            case MotionEvent.ACTION_UP:
                x2 = event.getX();
                float deltaX = x2 - x1;
                if (Math.abs(deltaX) > MIN_DISTANCE)
                {
                    Toast.makeText(v.getContext(), "left2right swipe", Toast.LENGTH_SHORT).show ();
                }
                else
                {
                    // consider as something else - a screen tap for example
                }
                break;
        }
        return false;
    }*/
}

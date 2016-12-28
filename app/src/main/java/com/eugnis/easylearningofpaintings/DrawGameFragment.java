package com.eugnis.easylearningofpaintings;


import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.flask.colorpicker.ColorPickerView;
import com.flask.colorpicker.OnColorSelectedListener;
import com.flask.colorpicker.builder.ColorPickerClickListener;
import com.flask.colorpicker.builder.ColorPickerDialogBuilder;
import com.github.clans.fab.FloatingActionButton;
import com.simplify.ink.InkView;


/**
 * A simple {@link Fragment} subclass.
 */
public class DrawGameFragment extends Fragment {

    public static final String TAG = DrawGameFragment.class.getSimpleName();


    FloatingActionButton chooseColorBtn, clearBtn, saveBtn;
    int backgroundColor;
    InkView ink;
    View v;


    public DrawGameFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_draw_game, container, false);
        ink = ((InkView) v.findViewById(R.id.ink));
        chooseColorBtn = (FloatingActionButton) v.findViewById(R.id.chooseColorBtn);

        chooseColorBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                ColorPickerDialogBuilder
                        .with(v.getContext())
                        .setTitle("Виберіть колір")
                        .initialColor(backgroundColor)
                        .wheelType(ColorPickerView.WHEEL_TYPE.FLOWER)
                        .lightnessSliderOnly()
                        .density(12)
                        .setOnColorSelectedListener(new OnColorSelectedListener() {
                            @Override
                            public void onColorSelected(int selectedColor) {
                                //Toast.makeText(getBaseContext(), "onColorSelected: 0x" + Integer.toHexString(selectedColor), Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setPositiveButton("OK", new ColorPickerClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int selectedColor, Integer[] allColors) {
                                backgroundColor = selectedColor;
                                ink.setColor(backgroundColor);
                                chooseColorBtn.setColorNormal(backgroundColor);
                            }
                        })
                        .setNegativeButton("Відміна", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        })
                        .build()
                        .show();
            }
        });

        clearBtn = (FloatingActionButton) v.findViewById(R.id.clearBtn);
        clearBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                new AlertDialog.Builder(v.getContext())
                        .setTitle("Очистити")
                        .setMessage("Ви дійсно хочете очистити малюнок?")
                        .setPositiveButton("Так", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                ink.clear();
                            }
                        })
                        .setNegativeButton("Ні", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // do nothing
                            }
                        })
                        .show();
            }
        });


        saveBtn = (FloatingActionButton) v.findViewById(R.id.saveBtn);
        saveBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                new AlertDialog.Builder(v.getContext())
                        .setTitle("Зберегти")
                        .setMessage("Ви завершили малюнок?")
                        .setPositiveButton("Так, порівняти", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                saveDrawedImage(ink.getBitmap());
                            }
                        })
                        .setNegativeButton("Ще ні", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // do nothing
                            }
                        })
                        .show();
            }
        });
        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initDraw();
    }

    public void initDraw(){
        backgroundColor = getResources().getColor(android.R.color.black);
        ink.setColor(backgroundColor);
        ink.setMinStrokeWidth(1.5f);
        ink.setMaxStrokeWidth(6f);
        ink.addFlag(InkView.FLAG_RESPONSIVE_WIDTH);
        ink.setBackgroundColor(Color.WHITE);


        //chooseColorBtn.setBackgroundColor(backgroundColor);
        chooseColorBtn.setColorNormal(backgroundColor);
    }

    public void saveDrawedImage(Bitmap drawedImage){
        ((DrawGameActivity)this.getActivity()).setDrawedImage(drawedImage);
        //getActivity().getSupportFragmentManager().popBackStack();
    }

}

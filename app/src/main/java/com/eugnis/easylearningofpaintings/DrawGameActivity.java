package com.eugnis.easylearningofpaintings;

import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.eugnis.easylearningofpaintings.app.App;
import com.flask.colorpicker.ColorPickerView;
import com.flask.colorpicker.OnColorSelectedListener;
import com.flask.colorpicker.builder.ColorPickerClickListener;
import com.flask.colorpicker.builder.ColorPickerDialogBuilder;
import com.simplify.ink.InkView;
import com.github.clans.fab.FloatingActionButton;

public class DrawGameActivity extends AppCompatActivity {


    FloatingActionButton chooseColorBtn;
    int backgroundColor;
    InkView ink;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw_game);

        ink = ((InkView) findViewById(R.id.ink));
        chooseColorBtn = (FloatingActionButton) findViewById(R.id.chooseColorBtn);

        backgroundColor = getResources().getColor(android.R.color.black);
        ink.setColor(backgroundColor);
        ink.setMinStrokeWidth(1.5f);
        ink.setMaxStrokeWidth(6f);
        ink.addFlag(InkView.FLAG_RESPONSIVE_WIDTH);

        //chooseColorBtn.setBackgroundColor(backgroundColor);
        chooseColorBtn.setColorNormal(backgroundColor);

    }

    public void chooseColor(View view){
        ColorPickerDialogBuilder
                .with(this)
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



}

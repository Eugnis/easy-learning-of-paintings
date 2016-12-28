package com.eugnis.easylearningofpaintings;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.eugnis.easylearningofpaintings.app.App;
import com.eugnis.easylearningofpaintings.data.model.Painting;
import com.eugnis.easylearningofpaintings.data.repo.PaintingsRepo;
import com.flask.colorpicker.ColorPickerView;
import com.flask.colorpicker.OnColorSelectedListener;
import com.flask.colorpicker.builder.ColorPickerClickListener;
import com.flask.colorpicker.builder.ColorPickerDialogBuilder;
import com.simplify.ink.InkView;
import com.github.clans.fab.FloatingActionButton;

import java.io.File;
import java.io.FileOutputStream;

public class DrawGameActivity extends AppCompatActivity {
    Painting paintingToDraw;
    Bitmap drawedImage;

    DrawGameSetFragment drawGameSetFragment;
    DrawGameFragment drawGameFragment;
    DrawCompareFragment drawCompareFragment;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw_game);


        setRandomPainting();

        drawGameSetFragment = new DrawGameSetFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.drawMainLayout, drawGameSetFragment).commit();


    }

    public void startPaint(View v){
        drawGameFragment = new DrawGameFragment();   // instantiate fragment
        getSupportFragmentManager().beginTransaction().replace(R.id.drawMainLayout, drawGameFragment).addToBackStack(null).commit();  //  replace original fragment with new fragment, add original to backstack
    }

    public void setRandomPainting(){
        paintingToDraw = (new PaintingsRepo()).getPainting(null);

    }

    public Painting getPaintingToDraw(){
        return paintingToDraw;
    }

    public void setDrawedImage(Bitmap image){
        this.drawedImage = image;

        getSupportFragmentManager().popBackStackImmediate();

        drawCompareFragment = new DrawCompareFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.drawMainLayout, drawCompareFragment).addToBackStack(null).commit();  //  replace original fragment with new fragment, add original to backstack

    }

    public Bitmap getDrawedImage(){
        return drawedImage;
    }

    public void saveDrawToGallery(View v){
        MediaStore.Images.Media.insertImage(getContentResolver(), this.drawedImage, "Мій рисунок " + this.paintingToDraw.getName() , "Мій рисунок " + this.paintingToDraw.getName());
        Toast.makeText(v.getContext(), "Збережено!", Toast.LENGTH_SHORT).show();

    }


}

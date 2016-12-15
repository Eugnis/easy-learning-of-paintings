package com.eugnis.easylearningofpaintings;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.eugnis.easylearningofpaintings.data.model.Painting;
import com.eugnis.easylearningofpaintings.data.repo.PaintingsRepo;

import java.io.IOException;
import java.io.InputStream;

public class PaintingView extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();

    ImageView pictureView;
    TextView descriptionView;

    boolean isImageFitToScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_painting_view);

        descriptionView = (TextView) findViewById(R.id.pictureDescription);
        pictureView = (ImageView) findViewById(R.id.pictureView);

        Intent intent = getIntent();
        String painting_id = intent.getStringExtra(MainActivity.PICTURE_ID);

        PaintingsRepo paintingsRepo = new PaintingsRepo();
        Painting painting = paintingsRepo.getPainting(painting_id);

        descriptionView.append(painting.getAbout());
        pictureView.setImageBitmap(painting.getPicture());

        /*AssetManager assetManager = getAssets();
        try {
            InputStream is = assetManager.open("pictures/"+ painting.getPainter().getFolder() +"/paintings/"+painting.getPicture());
            Bitmap bitmap = BitmapFactory.decodeStream(is);
            pictureView.setImageBitmap(bitmap);
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        pictureView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //fullScreen();
            }
        });



    }


}

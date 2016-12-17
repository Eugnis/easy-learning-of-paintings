package com.eugnis.easylearningofpaintings;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.eugnis.easylearningofpaintings.data.model.Painting;
import com.eugnis.easylearningofpaintings.data.repo.PaintingsRepo;

public class PaintingView extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();

    ImageView pictureView;
    TextView descriptionView;
    TextView paintingName;
    //TextView authorName;
    TextView styleName;
    Painting painting;

    boolean isImageFitToScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_painting_view);

        descriptionView = (TextView) findViewById(R.id.pictureDescription);
        paintingName = (TextView) findViewById(R.id.paintingName);
        //authorName = (TextView) findViewById(R.id.authorName);
        styleName = (TextView) findViewById(R.id.styleName);
        pictureView = (ImageView) findViewById(R.id.pictureView);


        PaintingsRepo paintingsRepo = new PaintingsRepo();
        Intent intent = getIntent();
        Boolean random = intent.getExtras().getBoolean(MainActivity.RANDOM_ARTICLE, false);
        if (random) painting = paintingsRepo.getPainting(null);
        else painting = paintingsRepo.getPainting(intent.getExtras().getString(CatalogActivity.PICTURE_ID));
//        Log.d(TAG, intent.getExtras().getString(CatalogActivity.PICTURE_ID));

        descriptionView.setText(painting.getAbout());
        paintingName.setText("\"" + painting.getName() + "\" " + painting.getPainter().getName());
        //authorName.setText(painting.getPainter().getName());
        styleName.append(painting.getStyle().getName());
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

package com.eugnis.easylearningofpaintings;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.eugnis.easylearningofpaintings.data.model.Painter;
import com.eugnis.easylearningofpaintings.data.model.Painting;
import com.eugnis.easylearningofpaintings.data.model.Style;
import com.eugnis.easylearningofpaintings.data.repo.PaintingsRepo;

import static com.eugnis.easylearningofpaintings.CatalogActivity.ARTICLE_ID;
import static com.eugnis.easylearningofpaintings.CatalogActivity.ARTICLE_TYPE;

public class PaintingView extends AppCompatActivity {

    public static final String TAG = PaintingView.class.getSimpleName();

    ImageView pictureView;
    TextView descriptionView;
    TextView paintingName;
    TextView authorName;
    TextView styleName;
    Painting painting;

    boolean isImageFitToScreen;

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        //if (pictureView!=null)
        //    ((BitmapDrawable)pictureView.getDrawable()).getBitmap().recycle();
        fillData(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_painting_view);

        descriptionView = (TextView) findViewById(R.id.pictureDescription);
        paintingName = (TextView) findViewById(R.id.paintingName);
        authorName = (TextView) findViewById(R.id.authorName);
        styleName = (TextView) findViewById(R.id.styleName);
        pictureView = (ImageView) findViewById(R.id.pictureView);
        Intent intent = getIntent();
        fillData(intent);

    }

    public void fillData(Intent intent){
        PaintingsRepo paintingsRepo = new PaintingsRepo();

        Boolean random = intent.getExtras().getBoolean(MainActivity.RANDOM_ARTICLE, false);
        if (random) painting = paintingsRepo.getPainting(null);
        else painting = paintingsRepo.getPainting(intent.getExtras().getString(CatalogActivity.PICTURE_ID));

//        Log.d(TAG, intent.getExtras().getString(CatalogActivity.PICTURE_ID));

        descriptionView.setText(painting.getAbout());
        paintingName.setText("\"" + painting.getName() + "\"\n" + painting.getYear());
        authorName.setText(painting.getPainter().getName());
        styleName.setText("Стиль: "+ painting.getStyle().getName());
        //ImageGridHandler handler = new ImageGridHandler(this, pictureView);
        //handler.execute(painting.getPictureLink(), "250", "250");
        Glide.with(this).load(painting.getPictureLink()).crossFade().thumbnail(0.1f).into(pictureView);

        pictureView.setMaxHeight(1000);
        pictureView.setAdjustViewBounds(true);


        pictureView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PaintingView.this, FullscreenPictureView.class);
                intent.putExtra("ImageFileLink", painting.getPictureLink());
                startActivity(intent);
                //Intent intent = new Intent();
                //intent.setAction(Intent.ACTION_VIEW);
                //intent.setDataAndType(Uri.parse(painting.getPictureLink()), "image/*");
                //startActivity(intent);

            }
        });

    }

    public void styleInfoBtnClick(View v) {
        Intent intent = new Intent(this, ArticleView.class);
        intent.putExtra(ARTICLE_TYPE, Style.TAG);
        intent.putExtra(ARTICLE_ID, Integer.toString(painting.getStyle().getStyleID()));
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);

        Log.d(TAG, "Open style");

        startActivity(intent);
    }

    public void painterInfoBtnClick(View v) {
        Intent intent = new Intent(this, ArticleView.class);
        intent.putExtra(ARTICLE_TYPE, Painter.TAG);
        intent.putExtra(ARTICLE_ID, Integer.toString(painting.getPainter().getPainterID()));
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);

        Log.d(TAG, "Open painter");

        startActivity(intent);
    }

    public void onDestroy() {
        super.onDestroy();

        pictureView.setImageDrawable(null);
    }


}

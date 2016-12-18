package com.eugnis.easylearningofpaintings;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
    //TextView authorName;
    TextView styleName;
    Painting painting;

    boolean isImageFitToScreen;

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        fillData(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_painting_view);

        descriptionView = (TextView) findViewById(R.id.pictureDescription);
        paintingName = (TextView) findViewById(R.id.paintingName);
        //authorName = (TextView) findViewById(R.id.authorName);
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
        paintingName.setText("\"" + painting.getName() + "\" " + painting.getPainter().getName());
        //authorName.setText(painting.getPainter().getName());
        styleName.setText("Стиль: "+ painting.getStyle().getName());
        pictureView.setImageBitmap(painting.getPicture());


        pictureView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //fullScreen();
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


}

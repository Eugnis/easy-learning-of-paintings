package com.eugnis.easylearningofpaintings;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.eugnis.easylearningofpaintings.adapters.CatalogAdapter;
import com.eugnis.easylearningofpaintings.custom.CustomCatalogGridView;
import com.eugnis.easylearningofpaintings.data.model.Painter;
import com.eugnis.easylearningofpaintings.data.model.Painting;
import com.eugnis.easylearningofpaintings.data.model.Style;
import com.eugnis.easylearningofpaintings.data.repo.PaintersRepo;
import com.eugnis.easylearningofpaintings.data.repo.PaintingsRepo;
import com.eugnis.easylearningofpaintings.data.repo.StylesRepo;

import java.util.List;

import static com.eugnis.easylearningofpaintings.CatalogActivity.PICTURE_ID;

public class ArticleView extends AppCompatActivity {

    public static final String TAG = ArticleView.class.getSimpleName();

    ImageView pictureView;
    TextView articleDescription;
    TextView articleName;
    TextView textAdditional;
    //TextView styleName;

    Painter painter;
    Style style;

    String articleType;
    String articleID;

    CustomCatalogGridView gridViewCatalog;

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
        setContentView(R.layout.activity_article_view);

        articleName = (TextView) findViewById(R.id.articleName);
        articleDescription = (TextView) findViewById(R.id.articleDescription);
        textAdditional = (TextView) findViewById(R.id.textAdditional);
        gridViewCatalog = (CustomCatalogGridView) findViewById(R.id.gridViewCatalog);
        pictureView = (ImageView) findViewById(R.id.pictureView);


        //PaintingsRepo paintingsRepo = new PaintingsRepo();
        Intent intent = getIntent();
        fillData(intent);
    }

    private void fillData(Intent intent){
        articleType = intent.getExtras().getString(CatalogActivity.ARTICLE_TYPE);
        if(articleType!=null)
        if(articleType.equals(Painter.TAG)) {
            articleID = intent.getExtras().getString(CatalogActivity.ARTICLE_ID);
            PaintersRepo paintersRepo = new PaintersRepo();
            painter = paintersRepo.getPainter(articleID);
            articleName.setText(painter.getName() + " " + painter.getCountry() + "\n" + painter.getYears());
            //painter.setPicture();
            pictureView.setVisibility(View.VISIBLE);
            //pictureView.setImageBitmap(painter.getPicture());

            //ImageGridHandler handler = new ImageGridHandler(this, pictureView);
            //handler.execute(painter.getPictureLink(), "100", "100");
            Glide.with(this).load(painter.getPictureLink()).override(250,300).crossFade().thumbnail(0.1f).into(pictureView);
            //pictureView.setImageBitmap(ImageHelper.decodeSampledBitmapFromAssets(painter.getPictureLink(),100,100));
            //pictureView.setMaxHeight(1000);
            pictureView.setAdjustViewBounds(true);
            articleDescription.setText(painter.getAbout());
            textAdditional.setText("Картини цього художника:");
            fillCatalog(painter);

        }
        else if (articleType.equals(Style.TAG)){
            articleID = intent.getExtras().getString(CatalogActivity.ARTICLE_ID);
            StylesRepo stylesRepo = new StylesRepo();
            style = stylesRepo.getStyle(articleID);
            articleName.setText(style.getName());
            pictureView.setVisibility(View.GONE);
            articleDescription.setText(style.getAbout());
            textAdditional.setText("Картини цього стилю:");
            fillCatalog(style);

        }
    }

    private void fillCatalog(Object selected){
        PaintingsRepo paintingsRepo = new PaintingsRepo();
        List<Painting> paintingsList;
        if (selected instanceof Painter) {
            Painter p = (Painter)selected;
            paintingsList = paintingsRepo.getFilteredPaintings(Painter.TAG, p.getPainterID());

        }
        else if (selected instanceof Style){
            Style s = (Style)selected;
            paintingsList= paintingsRepo.getFilteredPaintings(Style.TAG, s.getStyleID());

        }
        else paintingsList= paintingsRepo.getPaintings(false, null);

        gridViewCatalog = (CustomCatalogGridView) findViewById(R.id.gridViewCatalog);
        CatalogAdapter paintingsCatalogAdapter = new CatalogAdapter(this, R.layout.itemlistrow, paintingsList);
        gridViewCatalog.setAdapter(paintingsCatalogAdapter);

        gridViewCatalog.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Painting p = (Painting)parent.getItemAtPosition(position);
                Log.d(TAG, p.getName());

                Intent intent = new Intent(ArticleView.this, PaintingView.class);
                intent.putExtra(PICTURE_ID, Integer.toString(p.getPaintingID()));
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
            }
        });


    }
    @Override
    public void onDestroy() {
        super.onDestroy();

        pictureView.setImageDrawable(null);
    }


}



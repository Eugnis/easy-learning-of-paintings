package com.eugnis.easylearningofpaintings;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.eugnis.easylearningofpaintings.data.model.Painter;
import com.eugnis.easylearningofpaintings.data.repo.PaintersRepo;
import com.eugnis.easylearningofpaintings.data.repo.PaintingsRepo;
import com.eugnis.easylearningofpaintings.data.repo.StylesRepo;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();
    public final static String PICTURE_ID = "com.eugnis.easylearningofpaintings.PICTURE_ID";
    public final static String MODE = "com.eugnis.easylearningofpaintings.MODE";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Fragment fg = new MainMenuFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.mainLayout, fg).commit();

        //insertSampleData();
    }


    public void openRandomArticle(View v) {
        Intent intent = new Intent(this, PaintingView.class);
        intent.putExtra(PICTURE_ID, "1");
        startActivity(intent);

        Toast.makeText(this, "Random article", Toast.LENGTH_SHORT).show();
    }

    public void openStyles(View v) {
        Intent intent = new Intent(this, ArticlesSelectActivity.class);
        intent.putExtra(MODE, "styles");
        startActivity(intent);
        Toast.makeText(this, "Styles", Toast.LENGTH_SHORT).show();
    }

    public void openPainters(View v) {
        Toast.makeText(this, "Painters", Toast.LENGTH_SHORT).show();
        ListPainters();
        Intent intent = new Intent(this, ArticlesSelectActivity.class);
        intent.putExtra(MODE, "painters");
        startActivity(intent);
    }

    public void openGuessGame(View v) {
        Toast.makeText(this, "GuessGame", Toast.LENGTH_SHORT).show();
    }

    private void insertSampleData(){
        PaintersRepo paintersRepo = new PaintersRepo();
        PaintingsRepo paintingsRepo = new PaintingsRepo();
        StylesRepo stylesRepo = new StylesRepo();

        paintersRepo.delete();
        paintingsRepo.delete();
        stylesRepo.delete();

        //Insert Sample data if the table is empty
        Painter painter = new Painter();
        painter.setName("Leonardo Da Vinci");
        painter.setYears("1452–1519");
        painter.setCountry("Italy");
        painter.setAbout("Renaissance painter, scientist, inventor, and more. Da Vinci is one of most famous painters in the world for his iconic Mona Lisa and Last Supper.");
        paintersRepo.insert(painter);

        painter = new Painter();
        painter.setName("Vincent Van Gogh");
        painter.setYears("1853–1890");
        painter.setCountry("Dutch");
        painter.setAbout("Dutch post-impressionist painter. Famous paintings include: Sunflowers, The Starry night, and Cafe Terrace at Night.");
        paintersRepo.insert(painter);

        painter = new Painter();
        painter.setName("Rembrandt");
        painter.setYears("1606–1669");
        painter.setCountry("Dutch");
        painter.setAbout("Dutch Master. One of the greatest painters, admired for his vivid realism. Famous paintings include The Jewish Bride and The Storm of the Sea of Galilee.");
        paintersRepo.insert(painter);


    }

    private void ListPainters(){

        PaintersRepo paintersRepo = new PaintersRepo();
        List<Painter> paintersList= paintersRepo.getPainters();

        Log.d(TAG,String.format("%-7s", "Painter") +
                String.format("%-31s", "Name") +
                String.format("%-6s", "Years") +
                String.format("%-5s", "Country") +
                String.format("%-5s", "About"));
        Log.d(TAG,"=============================================================");
        for (int i= 0; i< paintersList.size();i++ ){
            Log.d(TAG,String.format("%-7s", paintersList.get(i).getPainterID())+
                    String.format("%-31s", paintersList.get(i).getName()) +
                    String.format("%-6s", paintersList.get(i).getYears()) +
                    String.format("%-5s", paintersList.get(i).getCountry()) +
                    String.format("%-5s", paintersList.get(i).getAbout()));

        }
        Log.d(TAG,"=============================================================");

    }

}

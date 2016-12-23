package com.eugnis.easylearningofpaintings;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
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
    public final static String RANDOM_ARTICLE = "com.eugnis.easylearningofpaintings.RANDOM_ARTICLE";
    public final static String MODE = "com.eugnis.easylearningofpaintings.MODE";

    private static final int TIME_INTERVAL = 2000; // # milliseconds, desired time passed between two back presses.
    private long mBackPressed;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Fragment fg = new MainMenuFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.mainLayout, fg).commit();

        //insertSampleData();
    }


    public void openRandomArticle(View v) {
        Intent intent = new Intent(this, PaintingView.class);
        intent.putExtra(RANDOM_ARTICLE, true);
        startActivity(intent);

        //Toast.makeText(this, "Random article", Toast.LENGTH_SHORT).show();
    }

    public void openStyles(View v) {
        Intent intent = new Intent(this, CatalogActivity.class);
        intent.putExtra(MODE, "styles");
        startActivity(intent);
        //Toast.makeText(this, "Styles", Toast.LENGTH_SHORT).show();
    }

    public void openPainters(View v) {
        //Toast.makeText(this, "Painters", Toast.LENGTH_SHORT).show();
        //ListPainters();
        Intent intent = new Intent(this, CatalogActivity.class);
        intent.putExtra(MODE, "painters");
        startActivity(intent);
    }

    public void openGuessGameMenu(View v) {
        //Toast.makeText(this, "GuessGame", Toast.LENGTH_SHORT).show();
        GuessGameMenuFragment guessMenu = new GuessGameMenuFragment();   // instantiate fragment
        getSupportFragmentManager().beginTransaction().replace(R.id.mainLayout, guessMenu).addToBackStack(null).commit();  //  replace original fragment with new fragment, add original to backstack
    }

    public void openDrawGame(View view) {
        Intent intent = new Intent(this, DrawGameActivity.class);
        startActivity(intent);
    }

    public void openGuessGame(View view) {
        Intent intent = new Intent(this, QuizGameActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed()
    {
        FragmentManager fm = this.getSupportFragmentManager();
        if (fm.getBackStackEntryCount() != 0){
            fm.popBackStack();
            return;
        }

        if (mBackPressed + TIME_INTERVAL > System.currentTimeMillis())
        {
            super.onBackPressed();
            return;
        }
        else { Toast.makeText(getBaseContext(), R.string.exitMessage, Toast.LENGTH_SHORT).show(); }

        mBackPressed = System.currentTimeMillis();

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

package com.eugnis.easylearningofpaintings.app;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;

import com.eugnis.easylearningofpaintings.data.DBHelper;
import com.eugnis.easylearningofpaintings.data.DatabaseManager;
import com.simplify.ink.InkView;

import java.util.Locale;

/**
 * Created by Eugnis on 03.12.2016.
 */

public class  App extends Application {
    private static Context context;
    private static DBHelper dbHelper;

    @Override
    public void onCreate()
    {
        super.onCreate();
        context = this.getApplicationContext();
        dbHelper = new DBHelper();
        DatabaseManager.initializeInstance(dbHelper);

        //Locale locale = new Locale("uk");
        //Locale.setDefault(locale);
        //Configuration config = getBaseContext().getResources().getConfiguration();
        //config.locale = locale;
        //getBaseContext().getResources().updateConfiguration(config,
        //        getBaseContext().getResources().getDisplayMetrics());

    }

    public static Context getContext(){
        return context;
    }

}

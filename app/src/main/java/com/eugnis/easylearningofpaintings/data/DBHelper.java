package com.eugnis.easylearningofpaintings.data;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.eugnis.easylearningofpaintings.app.App;
import com.eugnis.easylearningofpaintings.data.model.Painter;
import com.eugnis.easylearningofpaintings.data.model.Painting;
import com.eugnis.easylearningofpaintings.data.model.Style;
import com.eugnis.easylearningofpaintings.data.repo.PaintersRepo;
import com.eugnis.easylearningofpaintings.data.repo.PaintingsRepo;
import com.eugnis.easylearningofpaintings.data.repo.StylesRepo;

/**
 * Created by Eugnis on 03.12.2016.
 */

public class DBHelper extends SQLiteOpenHelper {
    //version number to upgrade database version
    //each time if you Add, Edit table, you need to change the
    //version number.
    private static final int DATABASE_VERSION =1;
    // Database Name
    private static final String DATABASE_NAME = "sqliteDB_ELOP.db";
    private static final String TAG = DBHelper.class.getSimpleName();

    public DBHelper( ) {
        super(App.getContext(), DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //All necessary tables you like to create will create here
        db.execSQL(PaintersRepo.createTable());
        db.execSQL(StylesRepo.createTable());

        db.execSQL(PaintingsRepo.createTable());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d(TAG, String.format("SQLiteDatabase.onUpgrade(%d -> %d)", oldVersion, newVersion));

        // Drop table if existed, all data will be gone!!!
        db.execSQL("DROP TABLE IF EXISTS " + Painter.TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + Painting.TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + Style.TABLE);
        onCreate(db);
    }

}

package com.eugnis.easylearningofpaintings.data.repo;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.eugnis.easylearningofpaintings.data.DatabaseManager;
import com.eugnis.easylearningofpaintings.data.model.Painter;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by Eugnis on 03.12.2016.
 */

public class PaintersRepo {

    private Painter painter;

    public PaintersRepo(){
        painter = new Painter();
    }

    public static String createTable(){
        return "CREATE TABLE " + Painter.TABLE  + "("
                + Painter.KEY_PainterID  + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + Painter.KEY_Name + " TEXT, "
                + Painter.KEY_Years + " TEXT, "
                + Painter.KEY_About + " TEXT, "
                + Painter.KEY_Country + " TEXT )";
    }

    public int insert(Painter painter) {
        int painterId;
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        ContentValues values = new ContentValues();
        //values.put(Painter.KEY_PainterID, painter.getPainterID());
        values.put(Painter.KEY_Name, painter.getName());
        values.put(Painter.KEY_Years, painter.getYears());
        values.put(Painter.KEY_About, painter.getAbout());
        values.put(Painter.KEY_Country, painter.getCountry());

        // Inserting Row
        painterId=(int)db.insert(Painter.TABLE, null, values);
        DatabaseManager.getInstance().closeDatabase();

        return painterId;
    }

    public void delete( ) {
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        db.delete(Painter.TABLE,null,null);
        DatabaseManager.getInstance().closeDatabase();
    }

    public List<Painter> getPainters(){
        Painter painter;
        List<Painter> paintersList = new ArrayList<>();

        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        String selectQuery =  " SELECT " + Painter.KEY_PainterID
                + ", " + Painter.KEY_Name
                + ", " + Painter.KEY_Years
                + ", " + Painter.KEY_About
                + ", " + Painter.KEY_Country
                + " FROM " + Painter.TABLE;

        Log.d(TAG, selectQuery);
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                painter= new Painter();
                painter.setPainterID(cursor.getInt(cursor.getColumnIndex(Painter.KEY_PainterID)));
                painter.setName(cursor.getString(cursor.getColumnIndex(Painter.KEY_Name)));
                painter.setAbout(cursor.getString(cursor.getColumnIndex(Painter.KEY_About)));
                painter.setYears(cursor.getString(cursor.getColumnIndex(Painter.KEY_Years)));
                painter.setCountry(cursor.getString(cursor.getColumnIndex(Painter.KEY_Country)));


                paintersList.add(painter);
            } while (cursor.moveToNext());
        }

        cursor.close();
        DatabaseManager.getInstance().closeDatabase();

        return paintersList;

    }


}

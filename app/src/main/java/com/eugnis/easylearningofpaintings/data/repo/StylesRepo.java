package com.eugnis.easylearningofpaintings.data.repo;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.eugnis.easylearningofpaintings.data.DatabaseManager;
import com.eugnis.easylearningofpaintings.data.model.Style;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by Eugnis on 03.12.2016.
 */

public class StylesRepo {

    private Style style;

    public StylesRepo(){
        style = new Style();
    }

    public static String createTable(){
        return "CREATE TABLE " + Style.TABLE  + "("
                + Style.KEY_StyleID  + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + Style.KEY_Name + " TEXT, "
                + Style.KEY_About + " TEXT )";
    }

    public int insert(Style style) {
        int styleId;
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        ContentValues values = new ContentValues();
        //values.put(Style.KEY_StyleID, style.getStyleID());
        values.put(Style.KEY_Name, style.getName());
        values.put(Style.KEY_About, style.getAbout());

        // Inserting Row
        styleId=(int)db.insert(Style.TABLE, null, values);
        DatabaseManager.getInstance().closeDatabase();

        return styleId;
    }

    public void delete( ) {
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        db.delete(Style.TABLE,null,null);
        DatabaseManager.getInstance().closeDatabase();
    }

    public List<Style> getStyles(){
        Style style;
        List<Style> stylesList = new ArrayList<>();

        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        String selectQuery =  " SELECT " + Style.KEY_StyleID
                + ", " + Style.KEY_Name
                + ", " + Style.KEY_About
                + " FROM " + Style.TABLE;

        Log.d(TAG, selectQuery);
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                style= new Style();
                style.setStyleID(cursor.getInt(cursor.getColumnIndex(Style.KEY_StyleID)));
                style.setName(cursor.getString(cursor.getColumnIndex(Style.KEY_Name)));
                style.setAbout(cursor.getString(cursor.getColumnIndex(Style.KEY_About)));


                stylesList.add(style);
            } while (cursor.moveToNext());
        }

        cursor.close();
        DatabaseManager.getInstance().closeDatabase();

        return stylesList;

    }
}

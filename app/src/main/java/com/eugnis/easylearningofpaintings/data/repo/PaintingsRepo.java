package com.eugnis.easylearningofpaintings.data.repo;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.eugnis.easylearningofpaintings.data.DatabaseManager;
import com.eugnis.easylearningofpaintings.data.model.Painter;
import com.eugnis.easylearningofpaintings.data.model.Painting;
import com.eugnis.easylearningofpaintings.data.model.Style;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by Eugnis on 03.12.2016.
 */

public class PaintingsRepo {

    private Painting painting;

    public PaintingsRepo(){
        painting = new Painting();
    }

    public static String createTable(){
        return "CREATE TABLE " + Painting.TABLE  + "("
                + Painting.KEY_PaintingID  + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + Painting.KEY_PainterID + " INTEGER, "
                + Painting.KEY_StyleID + " INTEGER, "
                + Painting.KEY_Name + " TEXT, "
                + Painting.KEY_Year + " TEXT, "
                + Painting.KEY_About + " TEXT, "
                + Painting.KEY_Picture + " TEXT, "
                + Painting.KEY_Watched + " INTEGER, "
                + " FOREIGN KEY ("+ Painting.KEY_PainterID +") REFERENCES "+ Painter.TABLE +"("+ Painter.KEY_PainterID +"), "
                + " FOREIGN KEY ("+ Painting.KEY_StyleID +") REFERENCES "+ Style.TABLE +"("+ Style.KEY_StyleID +"));";
    }

    public int insert(Painting painting) {
        int paintingId;
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        ContentValues values = new ContentValues();
        //values.put(Painting.KEY_PaintingID, painting.getPaintingID());
        values.put(Painting.KEY_PainterID, painting.getPainterID());
        values.put(Painting.KEY_StyleID, painting.getStyleID());
        values.put(Painting.KEY_Name, painting.getName());
        values.put(Painting.KEY_Year, painting.getYear());
        values.put(Painting.KEY_About, painting.getAbout());
        values.put(Painting.KEY_Picture, painting.getPicture());
        values.put(Painting.KEY_Watched, (painting.getWatched())? 1 : 0);

        // Inserting Row
        paintingId=(int)db.insert(Painting.TABLE, null, values);
        DatabaseManager.getInstance().closeDatabase();

        return paintingId;
    }

    public void delete( ) {
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        db.delete(Painting.TABLE,null,null);
        DatabaseManager.getInstance().closeDatabase();
    }

    public List<Painting> getPaintings(){
        Painting painting;
        List<Painting> paintingList = new ArrayList<>();

        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        String selectQuery =  " SELECT Painting." + Painting.KEY_PaintingID
                + ", Painting." + Painting.KEY_Name
                + ", Painting." + Painting.KEY_Year
                + ", Painting." + Painting.KEY_About
                + ", Painting." + Painting.KEY_Picture
                + ", Painting." + Painting.KEY_Watched
                + ", Painter." + Painter.KEY_PainterID
                + ", Painter." + Painter.KEY_Name + " As PainterName"
                + ", Style." + Style.KEY_StyleID
                + ", Style." + Style.KEY_Name + " As StyleName"
                + " FROM " + Painting.TABLE + "  As Painting "
                + " INNER JOIN " + Painter.TABLE + " Painter ON Painter." + Painter.KEY_PainterID + " =  Painting." + Painting.KEY_PainterID
                + " INNER JOIN " + Style.TABLE + " Style ON Style." + Style.KEY_StyleID + " =  Painting." + Painting.KEY_StyleID
                ;

        Log.d(TAG, selectQuery);
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                painting= new Painting();
                painting.setPaintingID(cursor.getInt(cursor.getColumnIndex(Painting.KEY_PaintingID)));
                painting.setPainterID(cursor.getInt(cursor.getColumnIndex(Painting.KEY_PainterID)));
                painting.setStyleID(cursor.getInt(cursor.getColumnIndex(Painting.KEY_StyleID)));
                painting.setPainterName(cursor.getString(cursor.getColumnIndex("PainterName")));
                painting.setStyleName(cursor.getString(cursor.getColumnIndex("StyleName")));
                painting.setName(cursor.getString(cursor.getColumnIndex(Painting.KEY_Name)));
                painting.setAbout(cursor.getString(cursor.getColumnIndex(Painting.KEY_About)));
                painting.setPicture(cursor.getString(cursor.getColumnIndex(Painting.KEY_Picture)));
                painting.setWatched((cursor.getInt(cursor.getColumnIndex(Painting.KEY_Watched))) !=0 );


                paintingList.add(painting);
            } while (cursor.moveToNext());
        }

        cursor.close();
        DatabaseManager.getInstance().closeDatabase();

        return paintingList;

    }
}

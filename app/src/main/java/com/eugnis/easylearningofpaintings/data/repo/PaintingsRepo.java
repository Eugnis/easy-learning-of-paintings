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

    /*public int insert(Painting painting) {
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
    }*/

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
                + ", Painter." + Painter.KEY_PainterID + " As PainterID"
                + ", Painter." + Painter.KEY_Name + " As PainterName"
                + ", Painter." + Painter.KEY_Years + " As PainterYears"
                + ", Painter." + Painter.KEY_About + " As PainterAbout"
                + ", Painter." + Painter.KEY_Country + " As PainterCountry"
                + ", Painter." + Painter.KEY_Folder + " As PainterFolder"
                + ", Style." + Style.KEY_StyleID + " As StyleID"
                + ", Style." + Style.KEY_Name + " As StyleName"
                + ", Style." + Style.KEY_About + " As StyleAbout"
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
                Painter painter = new Painter();
                painter.setPainterID(cursor.getInt(cursor.getColumnIndex("PainterID")));
                painter.setYears(cursor.getString(cursor.getColumnIndex("PainterYears")));
                painter.setAbout(cursor.getString(cursor.getColumnIndex("PainterAbout")));
                painter.setName(cursor.getString(cursor.getColumnIndex("PainterName")));
                painter.setCountry(cursor.getString(cursor.getColumnIndex("PainterCountry")));
                painter.setFolder(cursor.getString(cursor.getColumnIndex("PainterFolder")));
                painting.setPainter(painter);

                Style style = new Style();
                style.setStyleID(cursor.getInt(cursor.getColumnIndex("StyleID")));
                style.setAbout(cursor.getString(cursor.getColumnIndex("StyleAbout")));
                style.setName(cursor.getString(cursor.getColumnIndex("StyleName")));
                painting.setStyle(style);

                painting.setName(cursor.getString(cursor.getColumnIndex(Painting.KEY_Name)));
                painting.setAbout(cursor.getString(cursor.getColumnIndex(Painting.KEY_About)));
                painting.setPicture(cursor.getString(cursor.getColumnIndex(Painting.KEY_Picture)));
                painting.setWatched((cursor.getInt(cursor.getColumnIndex(Painting.KEY_Watched))) != 0);
                painting.setPaintingID(cursor.getInt(cursor.getColumnIndex(Painting.KEY_PaintingID)));

                paintingList.add(painting);
            } while (cursor.moveToNext());
        }

        cursor.close();
        DatabaseManager.getInstance().closeDatabase();

        return paintingList;

    }

    public Painting getPainting(String paintingId){
        Painting painting = new Painting();
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        String selectQuery =  " SELECT Painting." + Painting.KEY_Name
                + ", Painting." + Painting.KEY_Year
                + ", Painting." + Painting.KEY_About
                + ", Painting." + Painting.KEY_Picture
                + ", Painting." + Painting.KEY_Watched
                + ", Painter." + Painter.KEY_PainterID + " As PainterID"
                + ", Painter." + Painter.KEY_Name + " As PainterName"
                + ", Painter." + Painter.KEY_Years + " As PainterYears"
                + ", Painter." + Painter.KEY_About + " As PainterAbout"
                + ", Painter." + Painter.KEY_Country + " As PainterCountry"
                + ", Painter." + Painter.KEY_Folder + " As PainterFolder"
                + ", Style." + Style.KEY_StyleID + " As StyleID"
                + ", Style." + Style.KEY_Name + " As StyleName"
                + ", Style." + Style.KEY_About + " As StyleAbout"
                + " FROM " + Painting.TABLE + "  As Painting "
                + " INNER JOIN " + Painter.TABLE + " Painter ON Painter." + Painter.KEY_PainterID + " =  Painting." + Painting.KEY_PainterID
                + " INNER JOIN " + Style.TABLE + " Style ON Style." + Style.KEY_StyleID + " =  Painting." + Painting.KEY_StyleID
                ;
        if(paintingId==null) selectQuery = selectQuery + " ORDER BY RANDOM() LIMIT 1";
        else selectQuery = selectQuery + " WHERE Painting." + Painting.KEY_PaintingID + " = " + paintingId;

        Log.d(TAG, selectQuery);
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Painter painter = new Painter();
                painter.setPainterID(cursor.getInt(cursor.getColumnIndex("PainterID")));
                painter.setYears(cursor.getString(cursor.getColumnIndex("PainterYears")));
                painter.setAbout(cursor.getString(cursor.getColumnIndex("PainterAbout")));
                painter.setName(cursor.getString(cursor.getColumnIndex("PainterName")));
                painter.setCountry(cursor.getString(cursor.getColumnIndex("PainterCountry")));
                painter.setFolder(cursor.getString(cursor.getColumnIndex("PainterFolder")));
                painting.setPainter(painter);

                Style style = new Style();
                style.setStyleID(cursor.getInt(cursor.getColumnIndex("StyleID")));
                style.setAbout(cursor.getString(cursor.getColumnIndex("StyleAbout")));
                style.setName(cursor.getString(cursor.getColumnIndex("StyleName")));
                painting.setStyle(style);

                painting.setName(cursor.getString(cursor.getColumnIndex(Painting.KEY_Name)));
                painting.setAbout(cursor.getString(cursor.getColumnIndex(Painting.KEY_About)));
                painting.setPicture(cursor.getString(cursor.getColumnIndex(Painting.KEY_Picture)));
                painting.setWatched((cursor.getInt(cursor.getColumnIndex(Painting.KEY_Watched))) != 0);
            }while(cursor.moveToNext());
        }
        cursor.close();


        DatabaseManager.getInstance().closeDatabase();

        return painting;

    }


    public List<Painting> getFilteredPaintings(String filterTag, int filterId) {
        Painting painting;
        List<Painting> paintingList = new ArrayList<>();

        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        String selectQuery =  " SELECT Painting." + Painting.KEY_PaintingID
                + ", Painting." + Painting.KEY_Name
                + ", Painting." + Painting.KEY_Year
                + ", Painting." + Painting.KEY_About
                + ", Painting." + Painting.KEY_Picture
                + ", Painting." + Painting.KEY_Watched
                + ", Painter." + Painter.KEY_PainterID + " As PainterID"
                + ", Painter." + Painter.KEY_Name + " As PainterName"
                + ", Painter." + Painter.KEY_Years + " As PainterYears"
                + ", Painter." + Painter.KEY_About + " As PainterAbout"
                + ", Painter." + Painter.KEY_Country + " As PainterCountry"
                + ", Painter." + Painter.KEY_Folder + " As PainterFolder"
                + ", Style." + Style.KEY_StyleID + " As StyleID"
                + ", Style." + Style.KEY_Name + " As StyleName"
                + ", Style." + Style.KEY_About + " As StyleAbout"
                + " FROM " + Painting.TABLE + "  As Painting "
                + " INNER JOIN " + Painter.TABLE + " Painter ON Painter." + Painter.KEY_PainterID + " =  Painting." + Painting.KEY_PainterID
                + " INNER JOIN " + Style.TABLE + " Style ON Style." + Style.KEY_StyleID + " =  Painting." + Painting.KEY_StyleID
                ;

        if(filterTag.equals(Style.TAG)) selectQuery = selectQuery + " WHERE StyleID = " + filterId;
        else if(filterTag.equals(Painter.TAG)) selectQuery = selectQuery + " WHERE PainterID = " + filterId;

        Log.d(TAG, selectQuery);
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                painting= new Painting();
                Painter painter = new Painter();
                painter.setPainterID(cursor.getInt(cursor.getColumnIndex("PainterID")));
                painter.setYears(cursor.getString(cursor.getColumnIndex("PainterYears")));
                painter.setAbout(cursor.getString(cursor.getColumnIndex("PainterAbout")));
                painter.setName(cursor.getString(cursor.getColumnIndex("PainterName")));
                painter.setCountry(cursor.getString(cursor.getColumnIndex("PainterCountry")));
                painter.setFolder(cursor.getString(cursor.getColumnIndex("PainterFolder")));
                painting.setPainter(painter);

                Style style = new Style();
                style.setStyleID(cursor.getInt(cursor.getColumnIndex("StyleID")));
                style.setAbout(cursor.getString(cursor.getColumnIndex("StyleAbout")));
                style.setName(cursor.getString(cursor.getColumnIndex("StyleName")));
                painting.setStyle(style);

                painting.setName(cursor.getString(cursor.getColumnIndex(Painting.KEY_Name)));
                painting.setAbout(cursor.getString(cursor.getColumnIndex(Painting.KEY_About)));
                painting.setPicture(cursor.getString(cursor.getColumnIndex(Painting.KEY_Picture)));
                painting.setWatched((cursor.getInt(cursor.getColumnIndex(Painting.KEY_Watched))) != 0);
                painting.setPaintingID(cursor.getInt(cursor.getColumnIndex(Painting.KEY_PaintingID)));

                paintingList.add(painting);
            } while (cursor.moveToNext());
        }

        cursor.close();
        DatabaseManager.getInstance().closeDatabase();

        return paintingList;
    }
}

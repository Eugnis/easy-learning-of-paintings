package com.eugnis.easylearningofpaintings.data.model;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.io.InputStream;

import static com.eugnis.easylearningofpaintings.app.App.getContext;

/**
 * Created by Eugnis on 03.12.2016.
 */

public class Painting {

    public static final String TAG = Painting.class.getSimpleName();
    public static final String TABLE = "Paintings";
    //Labels Table Columns names
    public static final String KEY_PaintingID = "ID";
    public static final String KEY_PainterID = "ID_painter";
    public static final String KEY_StyleID = "ID_style";
    public static final String KEY_Name = "Name";
    public static final String KEY_Year = "Year";
    public static final String KEY_About = "About";
    public static final String KEY_Picture = "Picture";
    public static final String KEY_Watched = "Watched";

    private int paintingID;
    private int painterID;
    private int styleID;
    private String name;
    private String year;
    private String about;
    private Bitmap picture;
    private boolean watched;

    private String styleName;
    private String painterName;
    private Painter painter;
    private Style style;

    public int getPaintingID(){
        return paintingID;
    }

    public void setPaintingID(int paintingID){
        this.paintingID = paintingID;
    }

    public int getPainterID(){
        return painterID;
    }

    public void setPainterID(int painterID){
        this.painterID = painterID;
    }

    public int getStyleID(){
        return styleID;
    }

    public void setStyleID(int styleID){
        this.styleID = styleID;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getYear(){
        return year;
    }

    public void setYear(String year){
        this.year = year;
    }

    public String getAbout(){
        return about;
    }

    public void setAbout(String about){
        this.about = about;
    }

    public Bitmap getPicture(){
        return picture;
    }

    public void setPicture(String pictureName){
        AssetManager assetManager = getContext().getAssets();
        try {
            InputStream is = assetManager.open("pictures/"+ painter.getFolder() +"/paintings/"+pictureName);
            this.picture = BitmapFactory.decodeStream(is);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public boolean getWatched(){
        return watched;
    }

    public void setWatched(boolean watched){
        this.watched = watched;
    }

    public String getStyleName(){
        return styleName;
    }

    public void setStyleName(String styleName){
        this.styleName = styleName;
    }

    public String getPainterName(){
        return painterName;
    }

    public void setPainterName(Painter painter){
        this.painterName = painterName;
    }

    public Painter getPainter(){
        return painter;
    }

    public void setPainter(Painter painter){
        this.painter = painter;
    }

    public Style getStyle(){
        return style;
    }

    public void setStyle(Style style){
        this.style = style;
    }
}

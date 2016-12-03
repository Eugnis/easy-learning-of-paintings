package com.eugnis.easylearningofpaintings.data.model;

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
    private String picture;
    private boolean watched;

    private String styleName;
    private String painterName;

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

    public String getPicture(){
        return picture;
    }

    public void setPicture(String picture){
        this.picture = picture;
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

    public void setPainterName(String painterName){
        this.painterName = painterName;
    }
}

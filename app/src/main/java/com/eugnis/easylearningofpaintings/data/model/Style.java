package com.eugnis.easylearningofpaintings.data.model;

/**
 * Created by Eugnis on 03.12.2016.
 */

public class Style {

    public static final String TAG = Style.class.getSimpleName();
    public static final String TABLE = "Styles";
    //Labels Table Columns names
    public static final String KEY_StyleID = "ID";
    public static final String KEY_Name = "Name";
    public static final String KEY_About = "About";

    private int styleID;
    private String name;
    private String about;

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

    public String getAbout(){
        return about;
    }

    public void setAbout(String about){
        this.about = about;
    }

}

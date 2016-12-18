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

public class Painter {

    public static final String TAG = Painter.class.getSimpleName();
    public static final String TABLE = "Painters";
    //Labels Table Columns names
    public static final String KEY_PainterID = "ID";
    public static final String KEY_Name = "Name";
    public static final String KEY_Years = "Years";
    public static final String KEY_About = "About";
    public static final String KEY_Country = "Country";
    public static final String KEY_Folder = "Folder_name";

    private int painterID;
    private String name;
    private String years;
    private String about;
    private String country;
    private String folder;
    private Bitmap picture;

    public int getPainterID(){
        return painterID;
    }

    public void setPainterID(int painterID){
        this.painterID = painterID;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getYears(){
        return years;
    }

    public void setYears(String years){
        this.years = years;
    }

    public String getAbout(){
        return about;
    }

    public void setAbout(String about){
        this.about = about;
    }

    public String getCountry(){
        return country;
    }

    public void setCountry(String country){
        this.country = country;
    }

    public String getFolder(){
        return folder;
    }

    public void setFolder(String folder){
        this.folder = folder;
    }

    public Bitmap getPicture(){
        return picture;
    }

    public void setPicture(){
        AssetManager assetManager = getContext().getAssets();
        try {
            InputStream is = assetManager.open("pictures/"+ this.folder +"/main.jpg");
            this.picture = BitmapFactory.decodeStream(is);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}

package org.hdm.app.sambia.data;

import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hannes on 27.05.2016.
 */
public class Data {

    public String title;
    public int id;

    public String group_activity;
    public String sub_activity;
    public Bitmap image;
    public int imageId;
    public ArrayList<RecordedData> recordedDataList;

    // Dynamic parameters
    public boolean active = false;






    public Data(String title , int imageId) {
        this.title = title;
        this.imageId = imageId;
    }



    public Data(String name) {
        title = name;
    }

    public String getTitle(){
        return title;
    }



    public void setState() {
        if(active) {
            this.active = false;
        } else {
            this.active = true;
        }
    }



    public void setState(boolean state) {
        this.active = state;
    }


    public boolean getState() {
        return active;
    }

}
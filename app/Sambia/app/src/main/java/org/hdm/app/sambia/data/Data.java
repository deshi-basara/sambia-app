package org.hdm.app.sambia.data;

import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Hannes on 27.05.2016.
 */
public class Data {

    // General Parameter
    public String title = null;
    public int id;

    public String group_activity = null;
    public String sub_activity = null;
    public boolean sub_category = false;
    public Bitmap image = null;
    public int imageId;



    // Dynamic parameters
    public boolean activeState = false;
    public String subCategoryName = "";
    public ArrayList<RecordedData> recordedDataList = null;
    public Date startTime;
    public Date endTime;


    public Data() {
        recordedDataList = new ArrayList<>();
    }


    public Data(String name) {

        title = name;
        recordedDataList = new ArrayList<>();
    }







    public void setState() {
        if(activeState) {
            this.activeState = false;
        } else {
            this.activeState = true;
        }
    }



    public boolean getState() {
        return activeState;
    }

}
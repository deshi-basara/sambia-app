package org.hdm.app.sambia.datastorage;

import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Hannes on 27.05.2016.
 */
public class ActivityObject extends Object{

    // General Parameter
    public String title = null;
    public int id;

    public String group_activity = null;
    public String sub_activity = null;
    public boolean sub_category = false;

    public String imageName = null;
    public Bitmap image = null;
    public int imageId;
    public ArrayList<TimeFrame> timeFrameList = null;



    // Dynamic parameters
    public boolean activeState = false;
    public String subCategoryName = "";
    public Date startTime;
    public Date endTime;
    public int count = 0;

    public ActivityObject() {
        timeFrameList = new ArrayList<>();
    }



    public ActivityObject(String name) {
        title = name;
        timeFrameList = new ArrayList<>();
    }





    public void saveTimeStamp() {
        this.timeFrameList.add(new TimeFrame(this.startTime, this.endTime, this.subCategoryName));
        this.startTime = null;
        this.endTime = null;
        this.subCategoryName = "";
    }


    //ToDo make this nice
    public ArrayList get(String objects) {
        return new ArrayList();
    }
}
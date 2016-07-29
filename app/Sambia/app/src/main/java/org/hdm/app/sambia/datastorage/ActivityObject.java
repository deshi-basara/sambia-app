package org.hdm.app.sambia.datastorage;

import android.graphics.Bitmap;
import android.os.Handler;
import android.util.Log;

import org.hdm.app.sambia.util.View_Holder;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Hannes on 27.05.2016.
 */

public class ActivityObject extends Object{

    private static final String TAG = "ActivityObject";

    // General Parameter
    public String title = null;
    public String _id = null;
    public String item = null;

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
    public int count = 0;
    private long countt;

    public Date startTime;
    public Date endTime;
    private String service;

    public ActivityObject() {
        timeFrameList = new ArrayList<>();
    }

    public ActivityObject(String name) {
        title = name;
        timeFrameList = new ArrayList<>();
    }


    public void saveTimeStamp() {
        this.timeFrameList.add(new TimeFrame(this.startTime, this.endTime, this.service));
        this.startTime = null;
        this.endTime = null;
        this.service = null;
    }
}
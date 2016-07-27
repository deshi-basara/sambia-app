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
    private long countt;
    private Handler handler;
    Timer timer;

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


    public void stopCount() {

        if(timer != null){
            Log.d(TAG, "stopThead " + timer.toString());
            timer.cancel();
            handler = null;
        }

    }



    public void runCount(View_Holder view_holder) {

        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {

                                      @Override
                                      public void run() {
                                              Date currentDate = Calendar.getInstance().getTime();
                                              countt = (currentDate.getTime() - startTime.getTime())/1000;
                                              int seconds = (int) countt % 60;
                                              int minutes = (int) countt / 60;
                                              int houres = minutes / 60;
                                              String stringTime = String.format("%02d:%02d:%02d", houres, minutes, seconds);
                                              Log.d(TAG, " " + stringTime);
                                      }
                                  },
                //Set how long before to start calling the TimerTask (in milliseconds)
                0,
                //Set the amount of time between each execution (in milliseconds)
                1000);
    }

}
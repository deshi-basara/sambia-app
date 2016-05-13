package com.hdm.mobileapplication.sambiaapp.activity;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Hannes on 06.05.2016.
 */
public class EventManager {

    // Instance from DataManager
    private static EventManager instance = null;


    private ActivityObjectMap mObjectMap = null;
    private ArrayList<ActivityObject> mActivityObject = null;
    private HashMap<String, Bitmap> imageMap;




    private String currentActivity = null;

    public String getCurrentActivity() {
        return currentActivity;
    }

    public void setCurrentActivity(String currentActivity) {
        this.currentActivity = currentActivity;
    }






    public void createImageMap() {
        imageMap = new HashMap<String, Bitmap>();
    }

    public HashMap<String, Bitmap> getImageMap() {
        return imageMap;
    }


    public void setImageMap(HashMap<String, Bitmap> imageMap) {
        this.imageMap = imageMap;
    }


    public Bitmap putImage(String keyName, Bitmap image) {

        if (keyName != null) {
            imageMap.put(keyName, image);
            return imageMap.get(keyName);
        }

        return null;
    }

    /************
     * create new event
     ************/


    public ArrayList<ActivityObject> getActivityObject() {
        return mActivityObject;
    }

    public void setActivityObjectArrayList(ArrayList<ActivityObject> activityObject) {
        this.mActivityObject = activityObject;
    }


    public ActivityObjectMap getObjectMap() {
        return mObjectMap;
    }


    public void setActivityObjectMap(ActivityObjectMap mObjectMap) {
        this.mObjectMap = mObjectMap;
    }



    public ActivityObject findById(int id) {
        return mActivityObject.get(id);
    }

    /***********
     * create new event
     ***********/


    /***********
     * Singelton pattern
     ***********/

    public static void init() {
        if (instance == null) {
            instance = new EventManager();

        }
    }

    public static EventManager getInstance() {
        if (instance == null) {
            instance = new EventManager();
        }
        return instance;
    }


    public void setArrayMap(ArrayList<ActivityObject> arrayList) {


    }
}

package org.hdm.app.sambia.data;

import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * Created by Hannes on 06.05.2016.
 */
public class EventManager {

    // Instance from DataManager
    private static EventManager instance = null;


    private ActivityObjectMap mObjectMap = null;
    private ArrayList<ActivityObject> mActivityObject = null;


    private HashMap<String, Bitmap> imageMap;
    private LinkedHashMap<String, Data> activityMap = new LinkedHashMap<>();

//    private LinkedHashMap<String, Data> activeAMap = new LinkedHashMap<>();






    public boolean createActivityMap() {
        if(activityMap != null) {
            activityMap = new LinkedHashMap<>();
            return true;
        }
        return false;
    }


    public boolean createActivityObject(String name, Data data) {
        if(name != null) {
            if(!activityMap.containsKey(name)) {

                if(data != null) {
                    activityMap.put(name, data);
                } else {
                    activityMap.put(name, new Data(name));
                }
                return true;
            }
        }
        return false;
    }




    public boolean setActivityObject(Data data) {

        String title = data.title;
        if (title != null && activityMap != null) {

            if(!activityMap.containsKey(title)) {
                createActivityObject(title, data);
            }
            activityMap.put(title, data);
            return true;
            }
        return false;
    }



    public Data getActivityObject(String name) {
        if(name != null && activityMap.containsKey(name)) {
            return activityMap.get(name);
        }
        return null;
    }


    public int getAcrivityCount() {
        return activityMap.size();
    }

    public LinkedHashMap getActivityMap() {
        return activityMap;
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

package org.hdm.app.sambia.data;

import android.graphics.Bitmap;
import android.util.Log;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * Created by Hannes on 06.05.2016.
 */
public class EventManager {

    private final String TAG = "EventManager";
    // Instance from DataManager
    private static EventManager instance = null;


    private ActivityObjectMap mObjectMap = null;
    private ArrayList<ActivityObject> mActivityObject = null;


    private HashMap<String, Bitmap> imageMap;

    // In this Map are all the Activity Objects stored
    // It is used as DataBase from every Screen
    private LinkedHashMap<String, Data> activityMap = new LinkedHashMap<>();

    // In this map are all the active ActivityObjects stored
    // It is used from the FragmentActivity RecycleView to display all the Activitys
    // which are current recorded
    private LinkedHashMap<String, Data> activeMap = new LinkedHashMap<>();


    // In this map is stored the activitys for Calender list
    private HashMap<Date, ArrayList<String>> calenderMap = new HashMap<>();





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















    public boolean setActiveObject(Data data) {
        if (data != null && activeMap != null) {
            activeMap.put(data.title, data);
            return true;
        }
        return false;
    }



    public boolean removeActiveObject(String name) {
        if (name != null && activeMap != null) {
            activeMap.remove(name);
            return true;
        }
        return false;
    }




    public boolean removeActiveObject(Data data) {
        if (data != null && activeMap != null) {
            activeMap.remove(data.title);
            return true;
        }
        return false;
    }

    public LinkedHashMap getActiveMap() {
        return activeMap;
    }













    public boolean setCalenderMapEntry(Date key, String activity) {

        ArrayList<String> list;

        // check if key is not null
        if (key != null) {


            if(activity != null) {


                // if paire is in map get otherwise create ney ArrayList
                if (calenderMap.containsKey(key)) {
                    list = calenderMap.get(key);
                    list.add(activity);

                } else {
                    list = new ArrayList<>();
                }
                list.add(activity);
                calenderMap.put(key, list);
            } else {
                calenderMap.put(key, new ArrayList<String>());
            }

            Log.d(TAG, " key:  " + key.toString() + " // value: " + calenderMap.get(key).toString());

            return true;
        } else {
            return false;
        }
    }




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


}

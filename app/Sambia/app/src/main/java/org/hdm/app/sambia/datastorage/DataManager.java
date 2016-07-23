package org.hdm.app.sambia.datastorage;

import android.util.Log;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.TreeMap;

import static org.hdm.app.sambia.util.Consts.DEBUGMODE;

/**
 * Created by Hannes on 06.05.2016.
 */
public class DataManager {

    private final String TAG = "eEventManager";
    // Instance from DaataManager
    private static DataManager instance = null;


    private ArrayList<ActivityObject> mActivityObject = null;

    // In this Map are all the Activity Objects stored
    // It is used as DataBase from every Screen
    private LinkedHashMap<String, ActivityObject> activityMap = new LinkedHashMap<>();

    // In this map are all the active ActivityObjects stored
    // It is used from the FragmentActivity RecycleView to display all the Activitys
    // which are current recorded
    private LinkedHashMap<String, ActivityObject> activeMap = new LinkedHashMap<>();


    // In this map is stored the activitys for Calender list
    private TreeMap<String, ArrayList<String>> calenderMap = new TreeMap<>();






    public boolean createActivityObject(String name, ActivityObject activityObject) {
        if(name != null) {
            if(!activityMap.containsKey(name)) {

                if(activityObject != null) {
                    activityMap.put(name, activityObject);
                } else {
                    activityMap.put(name, new ActivityObject(name));
                }
                return true;
            }
        }
        return false;
    }




    public boolean setActivityObject(ActivityObject activityObject) {

        String title = activityObject.title;
        if (title != null && activityMap != null) {

            if(!activityMap.containsKey(title)) {
                createActivityObject(title, activityObject);
            }
            activityMap.put(title, activityObject);
            return true;
            }
        return false;
    }



    public ActivityObject getActivityObject(String name) {
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







    /************
     * create new
     ************/


    public ArrayList<ActivityObject> getActivityObject() {
        return mActivityObject;
    }



    public void setActivityObjectArrayList(ArrayList<ActivityObject> activityObject) {
        this.mActivityObject = activityObject;
    }





    public ActivityObject findById(int id) {
        return mActivityObject.get(id);
    }

    /***********
     * create new
     ***********/















    public boolean setActiveObject(ActivityObject activityObject) {
        if (activityObject != null && activeMap != null) {
            activeMap.put(activityObject.title, activityObject);
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




    public boolean removeActiveObject(ActivityObject activityObject) {
        if (activityObject != null && activeMap != null) {
            activeMap.remove(activityObject.title);
            return true;
        }
        return false;
    }

    public LinkedHashMap getActiveMap() {
        return activeMap;
    }















    public boolean setCalenderMapEntry(String key, String activity) {

        // check if key is not null
        if (key != null) {
            ArrayList<String> list = null;

            if(activity != null && calenderMap.containsKey(key)) {
                list = calenderMap.get(key);
                // do not add enty if list contains already activitys
                if(list.contains(activity)) return true;
                list.add(activity);
            } else {
                list = new ArrayList<String>();
            }
            calenderMap.put(key, list);
            //  Log.d(TAG, "key " + key.toString() + " // value: " + calenderMap.get(key).toString());
            return true;
        } else {
            return false;
        }
    }




    public boolean deleteCalenderMapEntry(String key, String activity){

        if(key != null && activity != null) {

            if(calenderMap.containsKey(key)) {
                ArrayList<String> list = calenderMap.get(key);
                if(DEBUGMODE) Log.d(TAG, "listSize before: " + list.size() + " " + list.toString());


                if(list.remove(activity)){
                    if(DEBUGMODE) Log.d(TAG, "listSize after: " + list.size() + " " + list.toString());
                    return true;
                }
            }
        }

        return false;
    }

    public TreeMap<String, ArrayList<String>> getCalendarMap() {
        return calenderMap;
    }


    /***********
     * Singelton pattern
     ***********/

    public static void init() {
        if (instance == null) {
            instance = new DataManager();

        }
    }

    public static DataManager getInstance() {
        if (instance == null) {
            instance = new DataManager();
        }
        return instance;
    }


}

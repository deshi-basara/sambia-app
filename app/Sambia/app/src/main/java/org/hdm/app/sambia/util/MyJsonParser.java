package org.hdm.app.sambia.util;

import android.util.Log;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.hdm.app.sambia.datastorage.ActivityObject;
import org.hdm.app.sambia.datastorage.ActivityObjectMap;

import java.io.IOException;
import java.util.ArrayList;


public class MyJsonParser {
    private final String TAG = "MyJsonParser";


    public MyJsonParser() {}


    public ArrayList<ActivityObject> createOjectFromJson(String objects, String jsonString) {

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            // Json String Input
//            JsonParser jp = jsonFactory.createJsonParser(jsonString);
            ActivityObjectMap activityObjectMap = objectMapper.readValue(jsonString, ActivityObjectMap.class);
            ArrayList arrayList = activityObjectMap.get(objects);

            ActivityObject a =(ActivityObject) arrayList.get(1);
            Log.d(TAG,"object " + a.title);
            Log.d(TAG, "done");
            return arrayList;
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}

package org.hdm.app.sambia.util;

import android.util.Log;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.hdm.app.sambia.datastorage.ActivityObject;

import java.io.IOException;
import java.util.ArrayList;


public class MyJsonParser {
    private final String TAG = "MyJsonParser";


    public MyJsonParser() {}


    public void createOjectFromJson(String objects, String jsonString) {

        ObjectMapper objectMapper = new ObjectMapper();
        JsonFactory jsonFactory = new JsonFactory();

        try {
            // Json String Input
//            JsonParser jp = jsonFactory.createJsonParser(jsonString);
            ActivityObject activityObjectMap = objectMapper.readValue(jsonString, ActivityObject.class);
            ArrayList arrayList = activityObjectMap.get(objects);

            String a = arrayList.get(1).toString();
            Log.d(TAG,"object " + a.toString());


            Log.d(TAG, "done");
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

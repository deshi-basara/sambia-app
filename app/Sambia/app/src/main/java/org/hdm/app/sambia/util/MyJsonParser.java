package org.hdm.app.sambia.util;

import android.graphics.Bitmap;
import android.util.Log;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.ObjectMapper;
import org.hdm.app.sambia.data.ActivityObject;
import org.hdm.app.sambia.data.ActivityObjectMap;
import org.hdm.app.sambia.data.EventManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;


public class MyJsonParser {
    private final String TAG = "MyJsonParser";


    public MyJsonParser() {
    }


    public void createOjectFromJson(String objects, String jsonString) {

        ObjectMapper objectMapper = new ObjectMapper();
        JsonFactory jsonFactory = new JsonFactory();

        try {
            // Json String Input
            JsonParser jp = jsonFactory.createJsonParser(jsonString);
            ActivityObjectMap activityObjectMap = objectMapper.readValue(jp, ActivityObjectMap.class);
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

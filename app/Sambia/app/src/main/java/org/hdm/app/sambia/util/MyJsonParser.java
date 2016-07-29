package org.hdm.app.sambia.util;


import android.util.Log;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import org.hdm.app.sambia.datastorage.ActivityObject;
import org.hdm.app.sambia.datastorage.ActivityObjectMap;
import org.hdm.app.sambia.datastorage.DataManager;
import org.hdm.app.sambia.datastorage.TimeFrame;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


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

//            Log.d(TAG,"object " + a.title);
//            Log.d(TAG, "done");
            return arrayList;
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }




    public void createJsonFromObject() {

        ObjectMapper mapper = new ObjectMapper();

        //For testing
        User user = createDummyUser();

        try {
            //Convert object to JSON string
            String jsonInString = mapper.writeValueAsString(user);
           Log.d(TAG, " "+ jsonInString);

//            //Convert object to JSON string and pretty print
//            jsonInString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(user);
//            Log.d(TAG, " "+ jsonInString);



        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    private static User createDummyUser(){

        User user = new User();

        Calendar cal = Calendar.getInstance();
        Date cDate = cal.getTime();

        user.date = cDate.getYear() +
                ":" + cDate.getMonth() +
                ":" + cDate.getDay();


        user.user_id = "fdsjhf3738";

        Map<String, ActivityObject> map = DataManager.getInstance().getActivityMap();



        for (Map.Entry<String, ActivityObject> entry : map.entrySet()) {
            ActivityObject object = entry.getValue();
            String _id = object._id;
            ArrayList<TimeFrame> list = object.timeFrameList;


        }




        return user;

    }




    private static class User {

        public String date;
        public String user_id;
        public List<TimeFrame> logs;
    }

    private static class Logs {

        public String _id;
        public List<String[]> timestamps;
    }

    int count =  0;
}

package com.hdm.mobileapplication.sambiaapp.activity;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.ObjectMapper;
import java.io.IOException;


public class MyJsonParser {


    private ObjectMapper objectMapper = null;
    private JsonFactory jsonFactory = null;
    private JsonParser jp = null;


    public MyJsonParser() {
        objectMapper = new ObjectMapper();
        jsonFactory = new JsonFactory();
    }



    public void createOjectFromJson(String objects, String jsonString) {
        try {
            // Json String Input
            jp = jsonFactory.createJsonParser(jsonString);
            EventManager.getInstance().setObjectMap(objectMapper.readValue(jp, ActivityObjectMap.class));
            EventManager.getInstance()
                    .setActivityObject(EventManager.getInstance()
                            .getObjectMap().get(objects));
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

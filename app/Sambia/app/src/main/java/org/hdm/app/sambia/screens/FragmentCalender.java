package org.hdm.app.sambia.screens;

/**
 * Created by Hannes on 13.05.2016.
 */

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.hdm.app.sambia.R;
import org.hdm.app.sambia.data.Data;
import org.hdm.app.sambia.data.EventManager;
import org.hdm.app.sambia.data.RecordedData;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;

/**
 * A fragment representing the front of the card.
 */
public class FragmentCalender extends BaseFragemnt {


    private String TAG = "DayView";
    private View view;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_calender, container, false);
        initMenu(view);
        initCalenderContent();
        return view;
    }

    private LinkedHashMap<String, ArrayList<String>> calenderMap = null;


    private void initCalenderContent() {


        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date1 = sdf.parse("2009-12-31");
        } catch (ParseException e) {
            e.printStackTrace();
        }


        calenderMap = new LinkedHashMap<>();
        LinkedHashMap activityMap = EventManager.getInstance().getActivityMap();
        int size = activityMap.size();



        for(Object key : activityMap.keySet()) {

            Data activity = (Data) activityMap.get(key);

            String title = activity.title;
            ArrayList<RecordedData> recData = activity.getRecordedData();









        }


    }


    @Override
    public void onResume() {
        super.onResume();
        setMenuTitle(TAG);
        setMenuBackground(android.R.color.holo_blue_light);
        setMenuBtn(R.drawable.ic_back);
    }
}
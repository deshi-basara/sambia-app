package org.hdm.app.sambia.screens;

/**
 * Created by Hannes on 13.05.2016.
 */

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.hdm.app.sambia.R;
import org.hdm.app.sambia.data.EventManager;
import org.hdm.app.sambia.listener.CalendarItemOnClickListener;
import org.hdm.app.sambia.Adapter.CalendarListAdapter;
import org.hdm.app.sambia.util.View_Holder;


import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.TreeMap;


/**
 * A fragment representing the front of the card.
 */
public class FragmentCalender extends BaseFragemnt implements
        CalendarItemOnClickListener {


    private String TAG = "DayView";
    private View view;
    private RecyclerView rv_calender;
    private CalendarListAdapter adapter;
    private int rows = 1;
    private LinkedHashMap data;
    private TreeMap calendar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_calender, container, false);

        initMenu(view);
        initCalenderList();
        return view;
    }


    @Override
    public void onResume() {
        super.onResume();
        setMenuTitle(TAG);
        setMenuBackground(android.R.color.holo_blue_light);
        setMenuBtn(R.drawable.ic_back);

        // scroll in Calendarlist to current Time
        Date currentTime = Calendar.getInstance().getTime();
        int hour = currentTime.getHours();
        if(hour>1) hour = hour*4-2;
        rv_calender.scrollToPosition(hour);
    }



    private void initCalenderList() {

        data = event.getActivityMap();
        calendar = event.getCalendarMap();
        adapter = new CalendarListAdapter(getActivity(),data, calendar);
        adapter.setListener(this);
        rv_calender = (RecyclerView) view.findViewById(R.id.rv_calender);
        rv_calender.setAdapter(adapter);
        rv_calender.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv_calender.setLayoutManager(new StaggeredGridLayoutManager(
                rows,StaggeredGridLayoutManager.VERTICAL));

    }




    @Override
    public void didOnClick(int position, String s, View_Holder holder) {

    }
}
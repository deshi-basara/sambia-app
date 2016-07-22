package org.hdm.app.sambia.screens;

/**
 * Created by Hannes on 13.05.2016.
 */

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.hdm.app.sambia.R;
import org.hdm.app.sambia.datastorage.ActivityManager;
import org.hdm.app.sambia.datastorage.TimeFrame;
import org.hdm.app.sambia.listener.CalendarItemOnClickListener;
import org.hdm.app.sambia.adapter.CalendarListAdapter;
import org.hdm.app.sambia.util.View_Holder;

import static org.hdm.app.sambia.util.Consts.DEBUGMODE;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.TreeMap;


/**
 * A fragment representing the front of the card.
 */
public class FragmentCalender extends BaseFragemnt implements
        CalendarItemOnClickListener,
        View.OnClickListener
       {


    private String TAG = "DayView";
    private View view;
    private RecyclerView rv_calender;
    private CalendarListAdapter adapter;
    private int rows = 1;
    private LinkedHashMap data;
    private TreeMap calendar;
    private FloatingActionButton fab_calendar;
    private int overallXScroll;
    private int lastFirstVisiblePosition = 0;
    private ActivityManager manager = ActivityManager.getInstance();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_calender, container, false);

        initMenu(view);
        initCalenderList();
        initFloatingButton();
        return view;
    }

    private void initFloatingButton() {
        fab_calendar = (FloatingActionButton) view.findViewById(R.id.fab_calendar);
        fab_calendar.setOnClickListener(this);
    }

           @Override
           public void onPause() {
               super.onPause();
               lastFirstVisiblePosition = ((LinearLayoutManager)rv_calender.getLayoutManager()).findFirstCompletelyVisibleItemPosition();
           }



           @Override
    public void onResume() {
        super.onResume();
        setMenuTitle(TAG);
        setMenuBackground(android.R.color.holo_blue_light);
        setMenuBtn(R.drawable.ic_back);

        scrollListToCurrentTime();
//      ((LinearLayoutManager) rv_calender.getLayoutManager()).scrollToPosition(lastFirstVisiblePosition);

    }





    private void initCalenderList() {
        data = event.getActivityMap();
        calendar = event.getCalendarMap();
        adapter = new CalendarListAdapter(getActivity(),data, calendar);
        adapter.setListener(this);
        rv_calender = (RecyclerView) view.findViewById(R.id.rv_calender);
        rv_calender.setAdapter(adapter);
        rv_calender.setLayoutManager(new LinearLayoutManager(getActivity()));

//        rv_calender.getLayoutManager().scrollToPosition(lastFirstVisiblePosition);
    }





    @Override
    public void didOnClick(String time, String s, View_Holder holder) {
        if(manager.editable){
            if(DEBUGMODE) Log.d(TAG, "time " + time + " // String " + s );



            // Delete Entry in CalendarMap
            event.deleteCalenderMapEntry(time, s);


            // Delete Entry in ActivityObject TimeFrame
            // ToDo Discuss if the TimeFrameList is realy helpful - better way CalendarMap?
            ArrayList<TimeFrame> list = event.getActivityObject(s).timeFrameList;
            if(DEBUGMODE) Log.d(TAG, "activity " + list.size());

        }
    }


    // FloatingActionButton Listener
    @Override
    public void onClick(View v) {
        lastFirstVisiblePosition = ((LinearLayoutManager)rv_calender.getLayoutManager()).findFirstVisibleItemPosition();

        if(manager.editable) {
            manager.editable = false;
            fab_calendar.setImageResource(android.R.drawable.ic_menu_edit);

        } else  {
            manager.editable = true;
            fab_calendar.setImageResource(android.R.drawable.ic_menu_close_clear_cancel);
        }
        // Invalidate new
       adapter.notifyDataSetChanged();
    }




    // scroll in Calendarlist to current Time
    private void scrollListToCurrentTime() {
        Date currentTime = Calendar.getInstance().getTime();
        int hour = currentTime.getHours();
        if(hour>=7) hour = hour*3-8;
        rv_calender.scrollToPosition(hour);


//        lastFirstVisiblePosition = ((LinearLayoutManager)rv_calender.getLayoutManager()).findFirstVisibleItemPosition();
//        rv_calender.getLayoutManager().scrollToPosition(lastFirstVisiblePosition);
    }

}
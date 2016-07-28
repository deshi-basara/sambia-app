package org.hdm.app.sambia.screens;

/**
 * Created by Hannes on 13.05.2016.
 */

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.hdm.app.sambia.R;
import org.hdm.app.sambia.datastorage.ActivityObject;
import org.hdm.app.sambia.datastorage.DataManager;
import org.hdm.app.sambia.listener.ActiveActivityListOnClickListener;
import org.hdm.app.sambia.listener.ActivityListOnClickListener;
import org.hdm.app.sambia.adapter.ActivityListAdapter;
import org.hdm.app.sambia.adapter.ActiveActivityListAdapter;
import org.hdm.app.sambia.util.View_Holder;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import static org.hdm.app.sambia.util.Consts.*;


/**
 * This fragment representing the front of the card.
 */
public class FragmentActivity extends BaseFragemnt implements
        ActivityListOnClickListener,
        ActiveActivityListOnClickListener {


    private final String TAG = "FragmentActivity";


    private View view;
    private RecyclerView recyclerView;
    private RecyclerView recyclerView_activeData;

    private ActivityListAdapter adapter;
    private ActiveActivityListAdapter activeAdapter;

    private List<ActivityObject> activityObject;
    private Date startDate;
    private Timer timer;
    private long countt;


    Handler handler = new Handler();



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_activitys, container, false);
        initMenu(view);
        initActiveActivityList();
        initActivityList();
        return view;
    }


    @Override
    public void onResume() {
        super.onResume();
        editableMode();
    }

    @Override
    public void onPause() {
        super.onPause();

        LinkedHashMap<String, ActivityObject> a = (LinkedHashMap) dataManager.getActiveMap();

        if (!a.isEmpty()){

            for (LinkedHashMap.Entry<String, ActivityObject> entry : a.entrySet()) {
                String b = entry.getKey();
                a.get(b).stopCount();
                Log.d(TAG, "onPause " + a.get(b).title);
            }
        }
    }

    /*******************
     * Life Cycle Ende
     ***********************/


    private void initActiveActivityList() {

        activityObject = new ArrayList<>(dataManager.getActiveMap().values());
        activeAdapter = new ActiveActivityListAdapter(activityObject);
        activeAdapter.setListener(this);
        recyclerView_activeData = (RecyclerView) view.findViewById(R.id.rv_active);
        recyclerView_activeData.setAdapter(activeAdapter);
        recyclerView_activeData.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView_activeData.setLayoutManager(new StaggeredGridLayoutManager(
                var.activeListRow, StaggeredGridLayoutManager.HORIZONTAL));
    }

    private void initActivityList() {

        activityObject = new ArrayList<>(dataManager.getActivityMap().values());
        adapter = new ActivityListAdapter(activityObject);
        adapter.setListener(this);
        recyclerView = (RecyclerView) view.findViewById(R.id.rv_list);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(
                var.activityListRows, StaggeredGridLayoutManager.VERTICAL));
    }


    /*******************
     * Init Ende
     ***********************/


    // Listener from the ActiveActivityObjectList
    @Override
    public void didOnClickActivityList(String title, View_Holder holder) {
        didClickOnActivityListItem(title, null);
    }



    // Listener from the ActivityObjectList
    @Override
    public void didClickOnActivityListItem(String title, View_Holder holder) {

        // Get the DataObject which was clicked
        // there are all information stored about the activity object
        // state, names image ect.
        ActivityObject activityObject = dataManager.getActivityObject(title);

        // If editable Mode - than add activity to selectedTime in CalendearList
        if (var.editable) {
            dataManager.setCalenderMapEntry(var.selectedTime, activityObject.title);
            listener.flip();
        } else {

            // when Activity is not active
            if (!activityObject.activeState) {

                // ToDo Show DialogFragment
//            if(list.get(position).sub_category && list.get(position).activeState) {
//                DFragment dFragment = new DFragment(dsata);
//                FragmentManager fm = fr.getFragmentManager();
//                dFragment.show(fm, "Dialog Fragment");
//            }

                // Set State to active
                activityObject.activeState = true;

                // set temporary start time
                activityObject.startTime = Calendar.getInstance().getTime();

                if(holder != null) activityObject.runCount(holder);


                // Count how many activity are active
                var.activeCount++;

                // add the active ActivityObject to the activeList
                dataManager.setActiveObject(activityObject);


            } else {

//                stopCount();

                // Deactivate Activity
                activityObject.activeState = false;
                activityObject.count = 0;

                // set temporary end time
                activityObject.endTime = Calendar.getInstance().getTime();

                //Count how many activitys are active
                var.activeCount--;

                // Remove the active ActivityObject from the activeList
                dataManager.removeActiveObject(activityObject);

                // add ActivityObject to CalendarContentList
                addActivtyObjectForCalenderContent(activityObject);

                // Save Time and subCategory in Dsata
                activityObject.saveTimeStamp();

                activityObject.stopCount();
            }
        }

        // Store edited ActivityObject back in DataManager
        dataManager.setActivityObject(activityObject);



        // Set Background if pressed from AdapterList
        if (holder != null) {
            holder.count = activityObject.count;
            holder.setBackground(activityObject.activeState);
        }



        // Update both RecycleViewAdapters
        updateAdpterList();
        updateActiveAdaperList();


        // get activeMap look into and for every entry add to
        if (DEBUGMODE) {
            Log.d(TAG, "activeCount " + var.activeCount);
            Log.d(TAG, "activeCount " + var.activeCount);
        }
    }








    private void addActivtyObjectForCalenderContent(ActivityObject activityObject) {

        int startHour = activityObject.startTime.getHours();
        int startMin = activityObject.startTime.getMinutes();


        Calendar cal = Calendar.getInstance();
        Date currentDate = cal.getTime();
        int currentMin = 0;


        // find current TimeSlot
        if (startMin < 15) {
            currentMin = 0;
        } else if (startMin < 30) {
            currentMin = 15;
        } else if (startMin < 45) {
            currentMin = 30;
        } else {
            currentMin = 45;
        }

        // Set Current TimeSlot
        currentDate.setSeconds(0);
        currentDate.setHours(startHour);
        currentDate.setMinutes(currentMin);
        cal.setTime(currentDate);
        currentDate = cal.getTime();

        if (DEBUGMODE) Log.d(TAG, "currentDate " + currentDate);

        // save once current activityTitle in map
        dataManager.setCalenderMapEntry(currentDate.toString(), activityObject.title);

        // Debugging
        if (DEBUGMODE) {
            ArrayList<String> list = dataManager.getCalendarMap().get(currentDate.toString());
            if(DEBUGMODE) {
                Log.d(TAG, "List entrys " + list.toString());
                Log.d(TAG, "List size " + list.size());
            }
        }


        // check if endTime is outside currentTime (15 min slot)
        // if true than safe activityTitle to all correspond time slots
        while (currentDate.after(activityObject.endTime)) {
            cal.setTime(currentDate);
            cal.add(Calendar.MINUTE, 15);
            currentDate = cal.getTime();
            dataManager.setCalenderMapEntry(currentDate.toString(), activityObject.title);

            if (DEBUGMODE) {
                Log.d(TAG, "currentTime "
                        + currentDate.toString()
                        + "Title "
                        + activityObject.title);
            }
        }

        if (DEBUGMODE) {
            Log.d(TAG, " " + activityObject.startTime.toString()
                    + " " + activityObject.startTime.getHours()
                    + " " + activityObject.startTime.getMinutes());
        }
    }



    // In this mode the user only sees a list of activitys
    // when he selects one than screens flip back to calendar screen
    private void editableMode() {

        if (var.editable) {
            menuView.setVisibility(View.GONE);
            recyclerView_activeData.setVisibility(View.GONE);
        } else {
            menuView.setVisibility(View.VISIBLE);
            recyclerView_activeData.setVisibility(View.VISIBLE);

            setMenuTitle("Activity");
            setMenuBackground(android.R.color.holo_green_dark);
            setMenuBtn(R.drawable.ic_forward);
        }
    }



    // load edited List and update ActivityObjectListAdapter
    private void updateAdpterList() {
        adapter.list = new ArrayList<>(dataManager.getActivityMap().values());
        adapter.notifyDataSetChanged();
    }

    // load edited List and update activeActivityObjectListAdapter
    private void updateActiveAdaperList() {
        activeAdapter.list = new ArrayList<>(dataManager.getActiveMap().values());
        activeAdapter.notifyDataSetChanged();
    }




    public void stopCount() {

        if(timer != null){
            Log.d(TAG, "stopThead " + timer.toString());
            timer.cancel();
        }

    }



    public void runCount(final View_Holder holder) {

        ActivityObject object = DataManager.getInstance().getActivityObject(holder.title.getText().toString());
        startDate = object.startTime;

        if(timer != null) {
            stopCount();
            Log.d(TAG, "StartCount " +holder.title);
        }

        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {

                                      @Override
                                      public void run() {
                                          Date currentDate = Calendar.getInstance().getTime();
                                          countt = (currentDate.getTime() - startDate.getTime())/1000;

                                          Log.d(TAG, timer.toString()+  " "  + countt);

                                          handler.post(new Runnable() {
                                              public void run() {
                                                  int seconds = (int) countt % 60;
                                                  int minutes = (int) countt / 60;
                                                  int houres = minutes / 60;
                                                  String stringTime = String.format("%02d:%02d:%02d", houres, minutes, seconds);
                                                  holder.time.setText(stringTime);
                                                  //count++;
                                              }
                                          });

                                      }
                                  },
                //Set how long before to start calling the TimerTask (in milliseconds)
                0,
                //Set the amount of time between each execution (in milliseconds)
                1000);
    }

}
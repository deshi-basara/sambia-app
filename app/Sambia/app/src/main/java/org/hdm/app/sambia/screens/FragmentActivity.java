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
import org.hdm.app.sambia.data.Data;
import org.hdm.app.sambia.data.EventManager;
import org.hdm.app.sambia.data.RecordedData;
import org.hdm.app.sambia.listener.ActiveRecycleViewItemOnClickListener;
import org.hdm.app.sambia.listener.ListRecycleViewItemOnClickListener;
import org.hdm.app.sambia.util.ListRecyclerViewAdapter;
import org.hdm.app.sambia.util.ActiveRecycleViewAdapter;
import org.hdm.app.sambia.util.View_Holder;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;


/**
 * A fragment representing the front of the card.
 */
public class FragmentActivity extends BaseFragemnt implements
        ListRecycleViewItemOnClickListener,
        ActiveRecycleViewItemOnClickListener {


    private static final boolean DEBUGMODE = true;


    private final String TAG = "FragmentActivity";


    private View view;
    private RecyclerView recyclerView;
    private ListRecyclerViewAdapter adapter;



    private int rows =2;
    private List<Data> data;
    private List<Data> activeData;
    private int activeCount = 0;
    private RecyclerView recyclerView_activeData;
    private ActiveRecycleViewAdapter activeAdapter;


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
        if(DEBUGMODE) Log.d(TAG, "onResume");
        super.onResume();
            setMenuTitle("Activity");
            setMenuBackground(android.R.color.holo_green_dark);
            setMenuBtn(R.drawable.ic_forward);
    }






    private void initActiveActivityList() {

        activeData = new ArrayList<>(EventManager.getInstance().getActiveMap().values());
        activeAdapter = new ActiveRecycleViewAdapter(this, activeData);
        activeAdapter.setListener(this);
        recyclerView_activeData = (RecyclerView) view.findViewById(R.id.rv_active);
        recyclerView_activeData.setAdapter(activeAdapter);
        recyclerView_activeData.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView_activeData.setLayoutManager(new StaggeredGridLayoutManager(
                1,StaggeredGridLayoutManager.HORIZONTAL));
    }




    private void initActivityList() {

        data = new ArrayList<>(EventManager.getInstance().getActivityMap().values());
        adapter = new ListRecyclerViewAdapter(this, data);
        adapter.setListener(this);
        recyclerView = (RecyclerView) view.findViewById(R.id.rv_list);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(
                rows,StaggeredGridLayoutManager.VERTICAL));

    }


















    @Override
    public void didOnClick(int position, String title, View_Holder holder) {


        Log.d(TAG, "title " + title);
        // Get the DataObject which was clicked
        // there are all information stored about the activity object
        // state, names image ect.

        Data data = EventManager.getInstance().getActivityObject(title);

        Log.d(TAG, "Data " + data);

        if(!data.activeState){

            // Show DialogFragment
//            if(list.get(position).sub_category && list.get(position).activeState) {
//                DFragment dFragment = new DFragment(data);
//                FragmentManager fm = fr.getFragmentManager();
//                dFragment.show(fm, "Dialog Fragment");
//            }

            // Set State to active
            data.activeState = true;
            // set temporary start time

            data.startTime = Calendar.getInstance().getTime();

            // Count how many activity are active
            activeCount++;

            EventManager.getInstance().setActiveObject(data);
        } else  {
            // Deactivate Activity
            data.activeState = false;

            // set temporary end time
            data.endTime = Calendar.getInstance().getTime();

            //Count how many activitys are active
            activeCount--;


            // Remove the active Data from the active dataList
            EventManager.getInstance().removeActiveObject(data);


            // Calculation for Time messurement
//            long difference = data.startTime.getTime() - data.endTime.getTime();
//            int days = (int) (difference / (1000*60*60*24));
//            int hours = (int) ((difference - (1000*60*60*24*days)) / (1000*60*60));
//            int min = (int) (difference - (1000*60*60*24*days) - (1000*60*60*hours)) / (1000*60);
//            hours = (hours < 0 ? -hours : hours);
//            min = (min < 0 ? -min : min);
//            days = (days < 0 ? -days : days);
//
//            Log.d(TAG, "======= Hours "+ hours);
//            Log.d(TAG, "======= min "+ min);
//            Log.d(TAG, "======= days "+ days);


//            ToDo: Test Activity TimeStamp

            // Save Time and subCategory in Data
            data.saveTimeStamp();

            saveActivtyForCalender(data);
        }



        // Store edited Data back in EventManager
        EventManager.getInstance().setActivityObject(data);


        // Set Background
        if(holder!= null){
        holder.setBackground(data.activeState);
        } else {
            adapter.list = new ArrayList<>(EventManager.getInstance().getActivityMap().values());
            adapter.notifyDataSetChanged();
         }



        activeAdapter.list = new ArrayList<>(EventManager.getInstance().getActiveMap().values());
        activeAdapter.notifyDataSetChanged();


        // get activeMap look into and for every entry add to

        if(DEBUGMODE) {
            Log.d(TAG, "position " + position);
            Log.d(TAG, "activeCount " + activeCount);
            Log.d(TAG, "activeCount " + activeCount);
        }
    }






    @Override
    public void didOnClickActivityList(int position, String s, View_Holder holder) {
        Log.d(TAG, "didOnClickActivityList " + position);
        didOnClick(position, s, null);
    }







//    private List<Data> fillWithData() {
//
//        LinkedHashMap<String, Data> activityMap =  EventManager.getInstance().getActivityMap();
//        List<Data> data = new ArrayList<>(activityMap.values());
//
//
//
////        int size = activityMap.size();
////
////        for(int i = 0; i<size; i++) {
////            Data value = (new ArrayList<Data>(activityMap.values())).get(i);
////            String name = value.getTitle();
////            data.add(new Data(name, R.drawable.onfarmwork_bagging));
////            Log.d(TAG, ""+i);
////        }
//        return data;
//    }


    private void saveActivtyForCalender(Data data) {



            // Iterate trough the RecordArray from the activity
            for (RecordedData timeStamp : data.getRecordedData()) {

                Date startTime = timeStamp.startTime;
                Date endTime = timeStamp.endTime;
                long sume = endTime.getTime() - startTime.getTime();


                long activeMinutes = TimeUnit.MILLISECONDS.toMinutes(sume);
                long activeSec = TimeUnit.MILLISECONDS.toSeconds(sume);


                // If Activity is recorded less than one Minute
                // than do not list it in calender as recorded activity

                Date currentDate = Calendar.getInstance().getTime();

                Log.d(TAG, "startTime " + startTime.toString());
                Log.d(TAG, "currentTime " + currentDate.toString());
                Log.d(TAG, "currentTime " + currentDate.getTime());
                Log.d(TAG, "currentTime " + currentDate.getDate());
                Log.d(TAG, "currentTime " + currentDate.getDay());
                Log.d(TAG, "currentTime " + currentDate.getMinutes());


                if(activeMinutes < 1 ) return;



                DateFormat formatter = new SimpleDateFormat("MM/dd/yy/");
                Date date;

                try {
                    date = formatter.parse("01/29/02");
                } catch (ParseException e) {
                    e.printStackTrace();
                }


                int startMin = startTime.getMinutes();
                int startHour = startTime.getHours();

                int endMin = endTime.getMinutes();
                int endHour = endTime.getHours();







                if(startMin<15) {

                    // save activity in calenderMap hour.0
                } else if(startMin<30) {
                    // save activity in calenderMap hour.15
                } else if(startMin<45) {
                    // save activity in calenderMap hour.30
                } else {
                    // save activity in calenderMap hour.45
                }

                Log.d(TAG, "" + startTime.toString() + " " + startTime.getHours() + " " + startTime.getMinutes());
            }

        }

}
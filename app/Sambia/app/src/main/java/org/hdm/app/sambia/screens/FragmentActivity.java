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
import org.hdm.app.sambia.listener.ActiveActivityListOnClickListener;
import org.hdm.app.sambia.listener.ActivityListOnClickListener;
import org.hdm.app.sambia.Adapter.ActivityListAdapter;
import org.hdm.app.sambia.Adapter.ActiveActivityListAdapter;
import org.hdm.app.sambia.util.SimpleDividerItemDecoration;
import org.hdm.app.sambia.util.View_Holder;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import static org.hdm.app.sambia.util.Consts.*;



/**
 * A fragment representing the front of the card.
 */
public class FragmentActivity extends BaseFragemnt implements
        ActivityListOnClickListener,
        ActiveActivityListOnClickListener {


    private final String TAG = "FragmentActivity";


    public View view;
    public RecyclerView recyclerView;
    private ActivityListAdapter adapter;



    private int rows =2;
    private List<Data> data;
    private List<Data> activeData;
    private int activeCount = 0;
    private RecyclerView recyclerView_activeData;
    private ActiveActivityListAdapter activeAdapter;




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

        activeData = new ArrayList<>(event.getActiveMap().values());
        activeAdapter = new ActiveActivityListAdapter(this, activeData);
        activeAdapter.setListener(this);
        recyclerView_activeData = (RecyclerView) view.findViewById(R.id.rv_active);
        recyclerView_activeData.setAdapter(activeAdapter);
        recyclerView_activeData.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView_activeData.setLayoutManager(new StaggeredGridLayoutManager(
                1,StaggeredGridLayoutManager.HORIZONTAL));
    }




    private void initActivityList() {

        data = new ArrayList<>(event.getActivityMap().values());
        adapter = new ActivityListAdapter(this, data);
        adapter.setListener(this);

        SimpleDividerItemDecoration divider =  new SimpleDividerItemDecoration(
                getActivity().getApplication());

        recyclerView = (RecyclerView) view.findViewById(R.id.rv_list);
//        recyclerView.addItemDecoration(divider);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(
                rows,StaggeredGridLayoutManager.VERTICAL));
    }




    // Listener from the ActivitysList
    @Override
    public void didClickOnActivityListItem(String title, View_Holder holder) {


        // Get the DataObject which was clicked
        // there are all information stored about the activity object
        // state, names image ect.
        Data data = event.getActivityObject(title);


        if(DEBUGMODE) {
            Log.d(TAG, "title " + title);
            Log.d(TAG, "Data " + data);
        }



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

            event.setActiveObject(data);
        } else  {
            // Deactivate Activity
            data.activeState = false;

            // set temporary end time
            data.endTime = Calendar.getInstance().getTime();

            //Count how many activitys are active
            activeCount--;


            // Remove the active Data from the active dataList
            event.removeActiveObject(data);


            saveActivtyForCalenderContent(data);

            // Save Time and subCategory in Data
            data.saveTimeStamp();
        }



        // Store edited Data back in EventManager
        event.setActivityObject(data);


        // Set Background
        if(holder!= null){
        holder.setBackground(data.activeState);
        } else {
            adapter.list = new ArrayList<>(event.getActivityMap().values());
            adapter.notifyDataSetChanged();
         }



        activeAdapter.list = new ArrayList<>(event.getActiveMap().values());
        activeAdapter.notifyDataSetChanged();


        // get activeMap look into and for every entry add to

        if(DEBUGMODE) {
            Log.d(TAG, "activeCount " + activeCount);
            Log.d(TAG, "activeCount " + activeCount);
        }
    }







    @Override
    public void didOnClickActivityList(String title, View_Holder holder) {
        didClickOnActivityListItem(title, null);
    }





    private void saveActivtyForCalenderContent(Data data) {



                int startHour   = data.startTime.getHours();
                int startMin    = data.startTime.getMinutes();


                Calendar cal    = Calendar.getInstance();
                Date currentDate = cal.getTime();
                int currentMin  = 0;


                // find current TimeSlot
                if(startMin<15) {
                    currentMin  = 0;
                } else if(startMin<30) {
                    currentMin  = 15;
                } else if(startMin<45) {
                    currentMin  = 30;
                } else {
                    currentMin = 45;
                }

                // Set Current TimeSlot
                currentDate.setSeconds(0);
                currentDate.setHours(startHour);
                currentDate.setMinutes(currentMin);
                cal.setTime(currentDate);
                currentDate = cal.getTime();

                // save once current activityTitle in map
                event.setCalenderMapEntry(currentDate, data.title);

                // Debugging
                if(DEBUGMODE) {
                    ArrayList<String> list = event.getCalendarMap().get(currentDate.toString());
                    Log.d(TAG, "List entrys " + list.toString());
                    Log.d(TAG, "List size " + list.size());
                }


                // check if endTime is outside currentTime (15 min slot)
                // if true than safe activityTitle to all correspond time slots
                while(currentDate.after(data.endTime)) {
                    cal.setTime(currentDate);
                    cal.add(Calendar.MINUTE, 15);
                    currentDate = cal.getTime();
                    event.setCalenderMapEntry(currentDate, data.title);
                    if(DEBUGMODE) {
                        Log.d(TAG, "currentTime " + currentDate.toString() + "Title " + data.title);
                    }
                }


                if(DEBUGMODE) {
                    Log.d(TAG,  " " + data.startTime.toString()
                                + " " + data.startTime.getHours()
                                + " " + data.startTime.getMinutes());
                }
            }
}
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
import org.hdm.app.sambia.datastorage.ActivityObject;
import org.hdm.app.sambia.listener.ActiveActivityListOnClickListener;
import org.hdm.app.sambia.listener.ActivityListOnClickListener;
import org.hdm.app.sambia.adapter.ActivityListAdapter;
import org.hdm.app.sambia.adapter.ActiveActivityListAdapter;
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
    private List<ActivityObject> activityObject;
    private List<ActivityObject> activeActivityObject;
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
        super.onResume();
        editableMode();
    }

    @Override
    public void onPause() {
        super.onPause();
    }


    /******************* Life Cycle Ende ***********************/





    private void initActiveActivityList() {

        activeActivityObject = new ArrayList<>(event.getActiveMap().values());
        activeAdapter = new ActiveActivityListAdapter(this, activeActivityObject);
        activeAdapter.setListener(this);
        recyclerView_activeData = (RecyclerView) view.findViewById(R.id.rv_active);
        recyclerView_activeData.setAdapter(activeAdapter);
        recyclerView_activeData.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView_activeData.setLayoutManager(new StaggeredGridLayoutManager(
                1,StaggeredGridLayoutManager.HORIZONTAL));
    }


    private void initActivityList() {

        activityObject = new ArrayList<>(event.getActivityMap().values());
        adapter = new ActivityListAdapter(this, activityObject);
        adapter.setListener(this);
        recyclerView = (RecyclerView) view.findViewById(R.id.rv_list);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(
                rows,StaggeredGridLayoutManager.VERTICAL));
    }


    /******************* Init Ende ***********************/




    // Listener from the ActivityList
    @Override
    public void didClickOnActivityListItem(String title, View_Holder holder) {


        // Get the DataObject which was clicked
        // there are all information stored about the activity object
        // state, names image ect.
        ActivityObject activityObject = event.getActivityObject(title);


        if(DEBUGMODE) Log.d(TAG, "title " + title +  "Daata " + activityObject);


        if(event.editable){
            event.setCalenderMapEntry(event.selectedTime, activityObject.title);
            listener.flip();
        } else {




        if(!activityObject.activeState){

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

            // Count how many activity are active
            activeCount++;

            event.setActiveObject(activityObject);
        } else  {


            // Deactivate Activity
            activityObject.activeState = false;
            activityObject.count = 0;

            // set temporary end time
            activityObject.endTime = Calendar.getInstance().getTime();

            //Count how many activitys are active
            activeCount--;


            // Remove the active Daata from the active dataList
            event.removeActiveObject(activityObject);


            saveActivtyForCalenderContent(activityObject);

            // Save Time and subCategory in Dsata
            activityObject.saveTimeStamp();

        }
        }



        // Store edited Data back in DataManager
        event.setActivityObject(activityObject);


        // Set Background
        if(holder!= null){
            holder.count = activityObject.count;
            holder.setBackground(activityObject.activeState);
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
//       displayActiveActivityList();
    }







    @Override
    public void didOnClickActivityList(String title, View_Holder holder) {
        didClickOnActivityListItem(title, null);
    }





    private void saveActivtyForCalenderContent(ActivityObject activityObject) {


                if(event.editable) {
                    event.setCalenderMapEntry(event.selectedTime, activityObject.title);
                } else {




                int startHour   = activityObject.startTime.getHours();
                int startMin    = activityObject.startTime.getMinutes();


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

                if(DEBUGMODE) Log.d(TAG, "currentDate " + currentDate);

                // save once current activityTitle in map
                event.setCalenderMapEntry(currentDate.toString(), activityObject.title);

                // Debugging
                if(DEBUGMODE) {
                    ArrayList<String> list = event.getCalendarMap().get(currentDate.toString());
                    Log.d(TAG, "List entrys " + list.toString());
                    Log.d(TAG, "List size " + list.size());
                }


                // check if endTime is outside currentTime (15 min slot)
                // if true than safe activityTitle to all correspond time slots
                while(currentDate.after(activityObject.endTime)) {
                    cal.setTime(currentDate);
                    cal.add(Calendar.MINUTE, 15);
                    currentDate = cal.getTime();
                    event.setCalenderMapEntry(currentDate.toString(), activityObject.title);
                    if(DEBUGMODE) {
                        Log.d(TAG, "currentTime " + currentDate.toString() + "Title " + activityObject.title);
                    }
                }


                if(DEBUGMODE) {
                    Log.d(TAG,  " " + activityObject.startTime.toString()
                                + " " + activityObject.startTime.getHours()
                                + " " + activityObject.startTime.getMinutes());
                }
                }
            }



    // In this mode the user only sees a list of activitys
    // when he selects one than screens flip back to calendar screen
    private void editableMode() {

//        displayActiveActivityList();
        displayMenuView();
    }







    private void displayActiveActivityList() {
        if(event.editable || activeCount<1) {
            recyclerView_activeData.animate()
                    .translationY(-200)
                    .setDuration(3000);
            recyclerView_activeData.setVisibility(View.GONE);

        } else {

            recyclerView_activeData.setVisibility(View.VISIBLE);
            recyclerView_activeData.animate()
                    .translationY(0)
                    .setDuration(300);

        }
    }


    private void displayMenuView() {

        if(event.editable) {
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

}
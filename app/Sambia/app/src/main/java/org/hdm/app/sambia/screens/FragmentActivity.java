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
import android.widget.ListView;

import org.hdm.app.sambia.R;
import org.hdm.app.sambia.data.Data;
import org.hdm.app.sambia.data.EventManager;
import org.hdm.app.sambia.data.RecordedData;
import org.hdm.app.sambia.listener.AdapterListener;
import org.hdm.app.sambia.util.MyCustomListAdapter;
import org.hdm.app.sambia.util.Recycler_View_Adapter;
import org.hdm.app.sambia.util.Recycler_View_Adapter_Active;
import org.hdm.app.sambia.util.View_Holder;
import org.hdm.app.sambia.views.DisplayActivityView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;


/**
 * A fragment representing the front of the card.
 */
public class FragmentActivity extends BaseFragemnt implements
        AdapterListener {


    private static final boolean DEBUGMODE = true;


    private final String TAG = "FragmentActivity";


    private View view;
    private MyCustomListAdapter mAdapter;
    private ListView activity_frag_lv;
    private DisplayActivityView mDisplayActivityView;
    private RecyclerView recyclerView;
    private Recycler_View_Adapter adapter;



    private int rows =2;
    private List<Data> data;
    private List<Data> activeData;
    private int activeCount = 0;
    private RecyclerView recyclerView_activeData;
    private Recycler_View_Adapter_Active activeAdapter;


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
            setMenuTitle("Activity");
            setMenuBackground(android.R.color.holo_green_dark);
            setMenuBtn(R.drawable.ic_forward);
    }






    private void initActiveActivityList() {

        activeData = new ArrayList<>();
        activeAdapter = new Recycler_View_Adapter_Active(this, activeData);
        activeAdapter.setListener(this);
        recyclerView_activeData = (RecyclerView) view.findViewById(R.id.rv_active);
        recyclerView_activeData.setAdapter(activeAdapter);
        recyclerView_activeData.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView_activeData.setLayoutManager(new StaggeredGridLayoutManager(
                1,StaggeredGridLayoutManager.HORIZONTAL));
    }




    private void initActivityList() {

        data = fill_with_data();
        adapter = new Recycler_View_Adapter(this, data);
        adapter.setListener(this);
        recyclerView = (RecyclerView) view.findViewById(R.id.rv_list);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(
                rows,StaggeredGridLayoutManager.VERTICAL));

    }






















    private List<Data> fill_with_data() {


        LinkedHashMap<String, Data> activityMap =  EventManager.getInstance().getActivityMap();
        List<Data> data = new ArrayList<>(activityMap.values());

//        int size = activityMap.size();
//
//        for(int i = 0; i<size; i++) {
//            Data value = (new ArrayList<Data>(activityMap.values())).get(i);
//            String name = value.getTitle();
//            data.add(new Data(name, R.drawable.onfarmwork_bagging));
//            Log.d(TAG, ""+i);
//        }

        return data;
    }





    @Override
    public void didOnClick(int position, View_Holder holder) {

        Log.d(TAG, "position " + position);



        // Get the DataObject which was selected
        // here are all information stored about the activity object
        // state, names image ect.
        String name = data.get(position).title;
        Data data = EventManager.getInstance().getActivityObject(name);


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

            activeData.add(data);
        } else  {
            // Deactivate Activity
            data.activeState = false;

            // set temporary end time
            data.endTime = Calendar.getInstance().getTime();

            //Count how many activitys are active
            activeCount--;


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


            // Save Time and subCategory in Data
            data.recordedDataList.add(new RecordedData(data.startTime, data.endTime, data.subCategoryName));
            data.startTime = null;
            data.endTime = null;
            data.subCategoryName = "";
        }

        holder.setBackground(data.activeState);

        // Store edited Data back in EventManager
        EventManager.getInstance().setActivityObject(data);



        activeAdapter.notifyDataSetChanged();


        if(DEBUGMODE) {
            Log.d(TAG, "activeCount " + activeCount);
        }


    }
}
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
import android.widget.AdapterView;
import android.widget.ListView;

import org.hdm.app.sambia.R;
import org.hdm.app.sambia.data.Data;
import org.hdm.app.sambia.listener.ViewHolderListener;
import org.hdm.app.sambia.util.MyCustomListAdapter;
import org.hdm.app.sambia.util.Recycler_View_Adapter;
import org.hdm.app.sambia.views.DisplayActivityView;

import java.util.ArrayList;
import java.util.List;

/**
 * A fragment representing the front of the card.
 */
public class FragmentActivity extends BaseFragemnt implements
        AdapterView.OnItemClickListener {


    private final String TAG = "Activity";


    private View view;
    private MyCustomListAdapter mAdapter;
    private ListView activity_frag_lv;
    private DisplayActivityView mDisplayActivityView;
    private RecyclerView recyclerView;
    private Recycler_View_Adapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_activitys, container, false);

        initMenu(view);
        initActivityList();
        initSliderListener();
        return view;

    }




    @Override
    public void onResume() {
        super.onResume();
            setMenuTitle("");
            setMenuBackground(android.R.color.holo_green_dark);
            setMenuBtn(R.drawable.ic_forward);
    }






    private void initActivityList() {

//        activity_frag_lv = (ListView) view.findViewById(R.id.activity_frag_lv);
//        mAdapter = new MyCustomListAdapter(getActivity());
//
//        ArrayList<ActivityObject> arrayList = EventManager.getInstance().getActivityObject();
////        ArrayList<ActivityObject> arrayList = new ArrayList<>();
//
//        int size = arrayList.size();
//        Log.d(TAG, "" + arrayList);
////        size = 50;
//
//
//        for (int i = 0; i < size; i++) {
//            ActivityObject mActivityObject = arrayList.get(i);
//            String name = mActivityObject.activity;
//            String image = mActivityObject.image;
//            int id = mActivityObject.id;
////           Seperator add
////              if (i % 4 == 0) {
////                mAdapter.addSeparatorItem("separator " + i);
////            } else {
//            mAdapter.addItem(name, image, id);
////            mAdapter.addItem("Placeholder", "ic_action_info", i);
//        }


//        activity_frag_lv.setAdapter(mAdapter);
//        // Change to "CHOICE_MODE_MULTI" need a logic for max possible checked items and delete options
//        activity_frag_lv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
//        activity_frag_lv.setOnItemClickListener(this);






        // get Data
        List<Data> data = fill_with_data();
        adapter = new Recycler_View_Adapter(data,getActivity().getApplication());
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));

        Log.d(TAG, " " + adapter.getItemCount());


    }





    private void initSliderListener() {

        mDisplayActivityView = (DisplayActivityView) view.findViewById(R.id.displayActivityView);
        // setup our swipe view
//        mDisplayActivityView.setSliderListener(this);
        mDisplayActivityView.setListener(this);
    }



    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.d(TAG, "paren " + parent +  " view " + view + " position " + position + " id " +id);
        Log.d(TAG, "" + view.getBackground());
    }









    private List<Data> fill_with_data() {
        List<Data> data = new ArrayList<>();

        for(int i = 0; i<50; i++) {

        data.add(new Data("Alice", R.drawable.onfarmwork_bagging));
        data.add(new Data("Wonderland", R.drawable.onfarmwork_bagging));
        data.add(new Data("Alice in ", R.drawable.onfarmwork_bagging));
        data.add(new Data("in Wonderland", R.drawable.onfarmwork_bagging));
        data.add(new Data("Alice in Wonderland", R.drawable.onfarmwork_bagging));
        }

        return data;
    }

}
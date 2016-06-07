package org.hdm.app.sambia.screens;

/**
 * Created by Hannes on 13.05.2016.
 */

import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import org.hdm.app.sambia.R;
import org.hdm.app.sambia.data.Data;
import org.hdm.app.sambia.listener.ClickListener;
import org.hdm.app.sambia.listener.RecyclerTouchListener;
import org.hdm.app.sambia.util.MyCustomListAdapter;
import org.hdm.app.sambia.util.Recycler_View_Adapter;
import org.hdm.app.sambia.views.DisplayActivityView;
import org.hdm.app.sambia.views.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;


/**
 * A fragment representing the front of the card.
 */
public class FragmentActivity extends BaseFragemnt implements
        ClickListener {


    private final String TAG = "FragmentActivity";


    private View view;
    private MyCustomListAdapter mAdapter;
    private ListView activity_frag_lv;
    private DisplayActivityView mDisplayActivityView;
    private RecyclerView recyclerView;
    private Recycler_View_Adapter adapter;



    private int rows =2;
    private List<Data> data;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_activitys, container, false);

        initMenu(view);
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






    private void initActivityList() {



        // fill List
        data = fill_with_data();
        adapter = new Recycler_View_Adapter(this, data);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(
                rows,StaggeredGridLayoutManager.VERTICAL));

//        recyclerView.addOnItemTouchListener(
//                new RecyclerTouchListener(getActivity().getApplicationContext(),
//                        recyclerView, this));

    }
















    private List<Data> fill_with_data() {


        List<Data> data = new ArrayList<>();

        for(int i = 0; i<20; i++) {
            String name = ""+i;
            data.add(new Data(name, R.drawable.onfarmwork_bagging));
            Log.d(TAG, ""+i);
        }

        return data;
    }










    public void onClick(View view, int position) {
        Data movie = data.get(position);
        Log.d(TAG,"Titleeeee "+ movie.getTitle() + " " +position);
    }


    public void onLongClick(View view, int position) {
        Data movie = data.get(position);
        Log.d(TAG,"Title long "+ movie.getTitle());
    }

}
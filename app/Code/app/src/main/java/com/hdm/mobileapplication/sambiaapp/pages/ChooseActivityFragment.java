package com.hdm.mobileapplication.sambiaapp.pages;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.hdm.mobileapplication.sambiaapp.R;
import com.hdm.mobileapplication.sambiaapp.activity.ActivityObject;
import com.hdm.mobileapplication.sambiaapp.activity.EventManager;
import com.hdm.mobileapplication.sambiaapp.listener.MainActivityListener;

import java.util.ArrayList;

/**
 * Created by Hannes on 05.05.2016.
 */
public class ChooseActivityFragment extends ListFragment{
    private final String TAG = "ChooseActivityFragment";

    // Store instance variables
    private MyCustomListAdapter mAdapter;
    MainActivityListener listener;


    // newInstance constructor for creating fragment with arguments
    public static ChooseActivityFragment newInstance(int page, String title) {
        ChooseActivityFragment fragmentFirst = new ChooseActivityFragment();
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        args.putString("someTitle", title);
        fragmentFirst.setArguments(args);
        return fragmentFirst;
    }



    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mAdapter = new MyCustomListAdapter(getActivity());
        listener = (MainActivityListener) getActivity();

        ArrayList<ActivityObject> arrayList = EventManager.getInstance().getActivityObject();
        int size = arrayList.size();

        Log.d(TAG, "" + arrayList);

        for (int i = 0; i < size; i++) {
           ActivityObject mActivityObject = arrayList.get(i);
           String name = mActivityObject.activity;
           String image = mActivityObject.image;
            int id = mActivityObject.id;
//            if (i % 4 == 0) {
//                mAdapter.addSeparatorItem("separator " + i);
//            } else {
            mAdapter.addItem(name, image, id);
        }

        setListAdapter(mAdapter);
        // Change to "CHOICE_MODE_MULTI" need a logic for max possible checked items and delete options
        getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
    }





    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        // TODO implement some logic
        Log.d("item", "getSelectedItemPosition " + getListView().getCheckedItemCount());
        l.setItemChecked(position, true);
        Log.d(TAG, "position " + l.getAdapter().getItem(position).toString());
        listener.setCurrentActivity(l.getAdapter().getItem(position).toString());
    }




    private void initLayout() {


    }






}

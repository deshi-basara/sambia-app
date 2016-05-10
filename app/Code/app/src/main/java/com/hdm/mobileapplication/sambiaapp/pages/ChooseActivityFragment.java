package com.hdm.mobileapplication.sambiaapp.pages;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

/**
 * Created by Hannes on 05.05.2016.
 */
public class ChooseActivityFragment extends ListFragment{

    // Store instance variables
    private String title;
    private int page;
    private ListView listview;
    private View view;
    private MyCustomListAdapter mAdapter;

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

        for (int i = 1; i < 50; i++) {
            mAdapter.addItem("item " + i);
            if (i % 4 == 0) {
                mAdapter.addSeparatorItem("separator " + i);
            }
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
    }




    private void initLayout() {


    }






}
